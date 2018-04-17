package tops.com.e_commerce.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tops.com.e_commerce.R;
import tops.com.e_commerce.database.ProductOperation;
import tops.com.e_commerce.model.Product;

/**
 * Created by Admin on 1/1/2018.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private ArrayList<Product> products;
    private Context context;
    public CartAdapter(ArrayList<Product> products, Context context) {
        this.products=products;
        this.context=context;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_product_row_item,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final Product product=products.get(position);
        holder.tvName.setText(product.getName());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmDialog(product);
            }
        });
    }

    private void showConfirmDialog(final Product product) {
        new AlertDialog.Builder(context)
                .setTitle("Confirm Your Decision")
                .setMessage("Are you sure want to delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ProductOperation operation=new ProductOperation(context);
                        int count=operation.removeProduct(product.getId());
                        if(count>0){
                            products.remove(product);
                            notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(context, "Operation Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageButton btnEdit, btnDelete;
        public CartViewHolder(View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }
}
