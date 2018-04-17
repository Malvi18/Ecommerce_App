package tops.com.e_commerce.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import tops.com.e_commerce.other.CategoryAdapterCallBack;
import tops.com.e_commerce.model.Category;
import tops.com.e_commerce.R;

/**
 * Created by Admin on 12/6/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private ArrayList<Category> categories;
    private CategoryAdapterCallBack callBack;

    public CategoryAdapter(ArrayList<Category> categories, CategoryAdapterCallBack callBack) {
        this.categories=categories;
        this.callBack=callBack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row_item, parent, false);
        MyViewHolder viewHolder=new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Category category=categories.get(position);
        holder.tvTitle.setText(category.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.changeFragment(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tvTitle=itemView.findViewById(R.id.tvTitle);
        }
    }
}
