package tops.com.e_commerce.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tops.com.e_commerce.model.Product;

import static tops.com.e_commerce.database.DBConstants.COL_DESC;
import static tops.com.e_commerce.database.DBConstants.COL_ID;
import static tops.com.e_commerce.database.DBConstants.COL_IMG;
import static tops.com.e_commerce.database.DBConstants.COL_NAME;
import static tops.com.e_commerce.database.DBConstants.TABLE_PRODUCT;

/**
 * Created by Admin on 12/27/2017.
 */

public class ProductOperation {

    private SQLiteDatabase db;
    public ProductOperation(Context context){
        DBHelper helper=new DBHelper(context);
        db=helper.getWritableDatabase();
    }

    public long saveProduct(Product product){
        ContentValues values=new ContentValues();
        values.put(COL_NAME,product.getName());
        values.put(COL_DESC,product.getDescription());
        values.put(COL_IMG,product.getImgName());
        long id=db.insert(TABLE_PRODUCT,null,values);
        return id;
    }

    public int removeProduct(int id){
        String selection=COL_ID+" = ?";
        String[] selectionsArgs={String.valueOf(id)};
        int count=db.delete(TABLE_PRODUCT,selection,selectionsArgs);
        return count;
    }

    public int updateProduct(Product product){
        ContentValues values=new ContentValues();
        values.put(COL_NAME,product.getName());
        values.put(COL_DESC,product.getDescription());
        values.put(COL_IMG,product.getImgName());
        String selection=COL_ID+" = ?";
        String[] selectionArgs={String.valueOf(product.getId())};
        int count=db.update(TABLE_PRODUCT,values, selection, selectionArgs);
        return count;
    }


    public ArrayList<Product> cartProducts()
    {
        String[] columns={COL_ID, COL_NAME, COL_DESC,COL_IMG};
        String selection=null;
        String[] selectionsArgs=null;
        String groupBy=null;
        String having=null;
        String orderBy=null;
        Cursor cursor = db.query(DBConstants.TABLE_PRODUCT, columns, selection, selectionsArgs, groupBy, having, orderBy);
        ArrayList<Product> products=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Product product=new Product();
                product.setId(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                product.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(COL_DESC)));
                product.setImgName(cursor.getString(cursor.getColumnIndex(COL_IMG)));
                products.add(product);
            }while (cursor.moveToNext());
        }
        return products;
    }
}
