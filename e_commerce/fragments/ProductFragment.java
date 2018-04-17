package tops.com.e_commerce.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import tops.com.e_commerce.R;
import tops.com.e_commerce.database.ProductOperation;
import tops.com.e_commerce.model.Product;
import tops.com.e_commerce.other.UtilityHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {


    TextView tvName,tvDescription;
    ImageView imgProduct;
    Button btnCart;
    Context context;
    Product product;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product, container, false);;
        context=getActivity();
        // Inflate the layout for this fragment
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        btnCart=view.findViewById(R.id.btnCart);
        tvName=view.findViewById(R.id.tvName);
        tvDescription=view.findViewById(R.id.tvDescription);
        imgProduct=view.findViewById(R.id.imgProduct);
        btnCart=view.findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUserLogin();
            }
        });

        Bundle bundle = getArguments();
         product= (Product) bundle.getSerializable("PRODUCT");
        tvName.setText(product.getName());
        tvDescription.setText(product.getDescription());
        imgProduct.setImageBitmap(product.getBitmap());

        return view;
    }

    private void checkUserLogin() {
        // Shared Preference Reading
        UtilityHelper helper=new UtilityHelper();
        if(helper.checkUser(context))
        {
            Fragment fragment=new LoginFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout,fragment)
                    .addToBackStack(ProductFragment.class.getName())
                    .commit();
        }else{
            Toast.makeText(context, "Already Login", Toast.LENGTH_SHORT).show();
            ProductOperation productOperation = new ProductOperation(context);
            product.setImgName(product.getName());
            long id = productOperation.saveProduct(product);
            if( id >=1 ){
                Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Item Not Added", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
