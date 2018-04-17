package tops.com.e_commerce.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import tops.com.e_commerce.other.CategoryAdapterCallBack;
import tops.com.e_commerce.model.Category;
import tops.com.e_commerce.R;
import tops.com.e_commerce.adapters.CategoryAdapter;
import tops.com.e_commerce.other.MyAsyncCallBack;
import tops.com.e_commerce.other.MyAsyncTask;
import tops.com.e_commerce.other.UtilityHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryAdapterCallBack, MyAsyncCallBack {

    private static final int SELECT_CATEGORIES = 1;
    private static final int INSERT_CATEGORY = 2;

    private RecyclerView recyclerView;
    private ArrayList<Category> categories;
    private Context context;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_product_category, container, false);
        getActivity().setTitle("");

        String[] items={"Item1","Item2","Item3"};

        // Inflate the layout for this fragment
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //Spinner spinnerItems=toolbar.findViewById(R.id.tvTitle);

        /*ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,items);
        spinnerItems.setAdapter(adapter1);
        spinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
        
        
        context=getActivity();
        ImageButton btnUpdate=view.findViewById(R.id.btnCart)
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        prepareData();

        //recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.cat_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_add:
                showInputAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInputAlertDialog() {
        View customView=LayoutInflater.from(context)
                .inflate(R.layout.new_category_dialog,null);
        final EditText etCategory=customView.findViewById(R.id.etCategory);
        new AlertDialog.Builder(context)
                .setTitle("Enter New Category")
                .setView(customView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            String category=etCategory.getText().toString();
                        //Toast.makeText(context, category, Toast.LENGTH_SHORT).show();
                        saveCategoryOnServer(category);
                    }
                })
                .show();
    }

    private void saveCategoryOnServer(String category) {
        String url="category.php";
        HashMap<String,String> hm=new HashMap<>();
        hm.put("flag","1");
        hm.put("category",category);
        MyAsyncTask asyncTask=new MyAsyncTask(this,INSERT_CATEGORY);
        asyncTask.setHashMap(hm);
        asyncTask.execute(url);
    }

    private void prepareData() {

        String url="category.php";
        HashMap<String,String> hm=new HashMap<>();
        hm.put("flag","4");
        MyAsyncTask asyncTask=new MyAsyncTask(this, SELECT_CATEGORIES);
        asyncTask.setHashMap(hm);
        asyncTask.execute(url);
        /*categories.add(new Category(1,"Electronics"));
        categories.add(new Category(2,"Cloths"));
        categories.add(new Category(3,"Appliances"));
        categories.add(new Category(4,"Jwellery"));
        categories.add(new Category(5,"Sports"));*/
    }



    @Override
    public void changeFragment(Category category) {
        Fragment fragment=new SubCategoryFragment();
        Bundle bundle=new Bundle();
        bundle.putString("CATEGORY",category.getName());
        bundle.putInt("ID",category.getId());
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .addToBackStack(CategoryFragment.class.getName())
                .commit();
    }

    @Override
    public void asyncCallBack(String result,int flag) {
        switch (flag)
        {
            case SELECT_CATEGORIES:
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    JSONArray jsonArray=jsonObject.getJSONArray("result");
                    categories=new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject categoryObject=jsonArray.getJSONObject(i);
                        int id=Integer.parseInt(categoryObject.getString("cat_id"));
                        String name=categoryObject.getString("cat_name");
                        categories.add(new Category(id,name));
                    }
                    CategoryAdapter adapter=new CategoryAdapter(categories,CategoryFragment.this);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case INSERT_CATEGORY:
                Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
