package tops.com.e_commerce.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import tops.com.e_commerce.R;
import tops.com.e_commerce.model.User;
import tops.com.e_commerce.other.MyAsyncCallBack;
import tops.com.e_commerce.other.MyAsyncTask;
import tops.com.e_commerce.other.UtilityHelper;

public class LoginFragment extends Fragment
        implements View.OnClickListener, MyAsyncCallBack {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        context = getActivity();
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnRegister = view.findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                String uname = etUsername.getText().toString();
                String passwd = etPassword.getText().toString();
                String url = "loginverification.php?" +
                        "email=" + uname + "&password=" + passwd;
                MyAsyncTask asyncTask = new MyAsyncTask(this,100);
                asyncTask.execute(url);
                break;
            case R.id.btnRegister:
                Fragment fragment = new RegisterFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, fragment)
                        .commit();
                break;
        }
        /*UtilityHelper helper=new UtilityHelper();
        helper.writeUser(context,uname);

        Toast.makeText(context, "Login Success!!!!", Toast.LENGTH_SHORT).show();*/

    }


    @Override
    public void asyncCallBack(String result,int flag) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            JSONObject userJsonObject = jsonArray.getJSONObject(0);
            /*User user=new User();
            user.setId(Integer.parseInt(userJsonObject.getString("uid")));
            user.setEmail(userJsonObject.getString("email"));
            user.setPassword(userJsonObject.getString("pass"));
            user.setStatus(userJsonObject.getString("status"));*/
            UtilityHelper helper = new UtilityHelper();
            helper.writeUser(context, userJsonObject.toString());
            Fragment fragment = new CategoryFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
