package tops.com.e_commerce.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import tops.com.e_commerce.R;
import tops.com.e_commerce.other.MyAsyncCallBack;
import tops.com.e_commerce.other.MyAsyncTask;
import tops.com.e_commerce.other.UtilityHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, MyAsyncCallBack {

    EditText etUsername, etPassword, etEmail, etMobile;
    Button btnSubmit;
    Context context;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        etUsername=view.findViewById(R.id.etUsername);
        etPassword=view.findViewById(R.id.etPassword);
        etEmail=view.findViewById(R.id.etEmail);
        etMobile=view.findViewById(R.id.etMobile);
        btnSubmit=view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        context=getActivity();

        return view;
    }

    @Override
    public void onClick(View view) {
        String uname = etUsername.getText().toString();
        String passwd = etPassword.getText().toString();
        String email= etEmail.getText().toString();
        String mobile = etMobile.getText().toString();
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username",uname);
        hashMap.put("password",passwd);
        hashMap.put("email",email);
        hashMap.put("mobile",mobile);

        String url = "webservice.php";
                /*+ "username=" + uname + "&password=" + passwd+
                "&email=" + email+ "&mobile=" + mobile;*/


        MyAsyncTask asyncTask = new MyAsyncTask(this,100);
        asyncTask.setHashMap(hashMap);
        asyncTask.execute(url);
    }

    @Override
    public void asyncCallBack(String result,int flag) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }
}
