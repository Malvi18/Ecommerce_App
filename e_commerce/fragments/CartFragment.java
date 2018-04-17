package tops.com.e_commerce.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tops.com.e_commerce.R;
import tops.com.e_commerce.adapters.CartAdapter;
import tops.com.e_commerce.database.ProductOperation;
import tops.com.e_commerce.model.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {


    public CartFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        // Inflate the layout for this fragment
        Context context=getActivity();
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)context).setSupportActionBar(toolbar);
        ProductOperation operation=new ProductOperation(context);
        ArrayList<Product> products=operation.cartProducts();
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        CartAdapter adapter=new CartAdapter(products, context);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
