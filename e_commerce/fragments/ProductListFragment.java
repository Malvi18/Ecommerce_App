package tops.com.e_commerce.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import tops.com.e_commerce.adapters.ProductListAdapter;
import tops.com.e_commerce.model.Product;
import tops.com.e_commerce.other.ProductListAdapterCallBack;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment implements ProductListAdapterCallBack{


    ArrayList<Product> products;
    RecyclerView productsView;
    private Context context;
    public ProductListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_product_list, container, false);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        productsView=view.findViewById(R.id.productsView);

        // Inflate the layout for this fragment
        Bundle bundle=getArguments();
        String name=bundle.getString("SUBCATEGORY");
        int id=bundle.getInt("ID",-1);
        getActivity().setTitle(name);
        context=getActivity();
        prepareData(id);
        productsView.setLayoutManager(new GridLayoutManager(context,2));
        ProductListAdapter adapter=new ProductListAdapter(products,ProductListFragment.this);
        productsView.setAdapter(adapter);
        return view;
    }

    private void prepareData(int id) {
        products=new ArrayList<>();
        switch (id){
            case 1://Fan
                Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.splash);
                products.add(new Product(1,"Fan1","Fan1 description",bitmap));
                products.add(new Product(2,"Fan2","Fan2 description",bitmap));
                products.add(new Product(3,"Fan3","Fan3 description",bitmap));
                products.add(new Product(4,"Fan4","Fan4 description",bitmap));
                products.add(new Product(5,"Fan5","Fan5 description",bitmap));
                break;
        }
    }

    @Override
    public void changeFragment(Product product) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("PRODUCT",product);

        Fragment fragment=new ProductFragment();
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack(ProductListFragment.class.getName())
                .commit();
    }
}
