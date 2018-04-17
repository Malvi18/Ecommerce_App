package tops.com.e_commerce.other;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Admin on 12/25/2017.
 */

public class UtilityHelper {

    public static final String BASE_URL="http://10.0.2.2:8080/ecommerce/";
    private static final String FILE_NAME = "E_COMMERCE_PREFERENCE";
    private static final String USER_NAME = "username";

    public boolean checkUser(Context context){
        SharedPreferences mSharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        String username=mSharedPreferences.getString(USER_NAME,null);
        return username==null;
    }

    public void writeUser(Context context, String username) {
        SharedPreferences mSharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mSharedPreferences.edit();
        editor.putString(USER_NAME,username);
        editor.commit();
    }

    public void removeUser(Context context) {
        SharedPreferences mSharedPreferences=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mSharedPreferences.edit();
        editor.remove(USER_NAME);
        editor.commit();
    }
}
