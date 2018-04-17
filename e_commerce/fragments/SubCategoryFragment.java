package tops.com.e_commerce.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tops.com.e_commerce.R;
import tops.com.e_commerce.adapters.SubCategoryAdapter;
import tops.com.e_commerce.model.SubCategory;
import tops.com.e_commerce.other.SubCategoryAdapterCallBack;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment implements SubCategoryAdapterCallBack{

    Context context;
    ArrayList<SubCategory> subCategories;
    RecyclerView subCategoriesView;

    public SubCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sub_category, container, false);
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        context=getActivity();
        //tvCategory=view.findViewById(R.id.tvCategory);
        subCategoriesView=view.findViewById(R.id.subCategoriesView);
        subCategoriesView.setLayoutManager(new GridLayoutManager(context,2));

        Bundle bundle=getArguments();
        String category=bundle.getString("CATEGORY");
        getActivity().setTitle(category);
        int id=bundle.getInt("ID",-1);
        // Inflate the layout for this fragment
        prepareData(id);
        SubCategoryAdapter adapter=new SubCategoryAdapter(subCategories,SubCategoryFragment.this);
        subCategoriesView.setAdapter(adapter);
        return view;
    }

    private void prepareData(int id) {
        subCategories=new ArrayList<>();
        switch (id){
            case 1://Electronics
                subCategories.add(new SubCategory(1,"Fan"));
                subCategories.add(new SubCategory(2,"Refrigerator"));
                subCategories.add(new SubCategory(3,"TV"));
                subCategories.add(new SubCategory(4,"WahingMachine"));
                subCategories.add(new SubCategory(5,"Mobile"));
                break;
            case 2://Cloths
                subCategories.add(new SubCategory(1,"Shirts"));
                subCategories.add(new SubCategory(2,"Jeans"));
                subCategories.add(new SubCategory(3,"T-shirts"));
                subCategories.add(new SubCategory(4,"Nightwear"));
                subCategories.add(new SubCategory(5,"Tops"));
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    @Override
    public void changeFragment(SubCategory subCategory) {
        Fragment fragment=new ProductListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("SUBCATEGORY",subCategory.getName());
        bundle.putInt("ID",subCategory.getId());
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack(SubCategoryFragment.class.getName())
                .commit();
    }
}
