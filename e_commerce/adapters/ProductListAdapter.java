package tops.com.e_commerce.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tops.com.e_commerce.R;
import tops.com.e_commerce.model.Product;
import tops.com.e_commerce.model.SubCategory;
import tops.com.e_commerce.other.ProductListAdapterCallBack;
import tops.com.e_commerce.other.SubCategoryAdapterCallBack;

/**
 * Created by Admin on 12/11/2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private ArrayList<Product> products;
    private ProductListAdapterCallBack adapterCallBack;
    public ProductListAdapter(ArrayList<Product> products, ProductListAdapterCallBack adapterCallBack) {
        this.products=products;
        this.adapterCallBack=adapterCallBack;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row_item,parent,false);
        ProductViewHolder viewHolder=new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Product product=products.get(position);
        holder.tvTitle.setText(product.getName());
        holder.productImageView.setImageBitmap(product.getBitmap());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterCallBack.changeFragment(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView productImageView;
        View view;
        public ProductViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvTitle=itemView.findViewById(R.id.tvTitle);
            productImageView=itemView.findViewById(R.id.productImageView);
        }
    }
}
