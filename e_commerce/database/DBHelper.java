package tops.com.e_commerce.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static tops.com.e_commerce.database.DBConstants.CREATE_PRODUCT;
import static tops.com.e_commerce.database.DBConstants.DBNAME;
import static tops.com.e_commerce.database.DBConstants.VERSION;

/**
 * Created by Admin on 12/27/2017.
 */

public class DBHelper extends SQLiteOpenHelper{


    /*public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, factory, VERSION);
    }*/
    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
