package tops.com.e_commerce.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tops.com.e_commerce.R;
import tops.com.e_commerce.model.SubCategory;
import tops.com.e_commerce.other.SubCategoryAdapterCallBack;

/**
 * Created by Admin on 12/11/2017.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {

    private ArrayList<SubCategory> subCategories;
    private SubCategoryAdapterCallBack adapterCallBack;
    public SubCategoryAdapter(ArrayList<SubCategory> subCategories, SubCategoryAdapterCallBack adapterCallBack) {
        this.subCategories=subCategories;
        this.adapterCallBack=adapterCallBack;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_row_item,parent,false);
        SubCategoryViewHolder viewHolder=new SubCategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SubCategoryViewHolder holder, int position) {
        final SubCategory subCategory=subCategories.get(position);
        holder.tvTitle.setText(subCategory.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterCallBack.changeFragment(subCategory);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        View view;
        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvTitle=itemView.findViewById(R.id.tvTitle);
        }
    }
}
