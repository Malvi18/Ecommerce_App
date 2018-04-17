package tops.com.e_commerce.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import tops.com.e_commerce.R;
import tops.com.e_commerce.database.ProductOperation;
import tops.com.e_commerce.fragments.CartFragment;
import tops.com.e_commerce.fragments.CategoryFragment;
import tops.com.e_commerce.fragments.LoginFragment;
import tops.com.e_commerce.fragments.ProductFragment;
import tops.com.e_commerce.fragments.SplashScreenFragment;
import tops.com.e_commerce.model.Product;
import tops.com.e_commerce.other.UtilityHelper;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        /*sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        if (sharedPreferences.contains("UserName")) {
            Fragment fragment = new SplashScreenFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frame_layout, fragment);
            transaction.commit();
        } else {
            Fragment fragment = new LoginFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frame_layout, fragment);
            transaction.commit();
        }*/


        final Fragment fragment=new SplashScreenFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout,fragment)
                .commit();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UtilityHelper helper=new UtilityHelper();
                Fragment fragment1;
                if(helper.checkUser(context))
                {
                    fragment1=new LoginFragment();
                }else{
                    fragment1=new CategoryFragment();
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,fragment1)
                        .commit();
            }
        },2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);
        ProductOperation operation=new ProductOperation(this);
        ArrayList<Product> products=operation.cartProducts();
        MenuItem item=menu.findItem(R.id.action_cart);
        item.setTitle("Cart("+products.size()+")");
        UtilityHelper helper=new UtilityHelper();
        if(helper.checkUser(this))
        {
            menu.removeItem(R.id.action_logout);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                UtilityHelper helper=new UtilityHelper();
                helper.removeUser(this);
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cart:
                //Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
                showCartDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCartDialog() {
        ProductOperation operation=new ProductOperation(this);
        ArrayList<Product> products=operation.cartProducts();
        String msg="Your Have Selected "+products.size()+" Items.";
        new AlertDialog.Builder(this)
                .setTitle("Cart Items")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(msg)
                .setPositiveButton("Place Order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("View Items", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                        Fragment fragment1=new CartFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout,fragment1)
                                .addToBackStack(MainActivity.class.getName())
                                .commit();
                    }
                })
                .show();
    }

}
