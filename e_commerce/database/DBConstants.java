package tops.com.e_commerce.database;

/**
 * Created by Admin on 12/27/2017.
 */

public interface DBConstants {
    String DBNAME="ecommerce";
    int VERSION=1;
    String TABLE_PRODUCT="product";
    String COL_ID="id";
    String COL_NAME="name";
    String COL_DESC="description";
    String COL_IMG="img";

    String CREATE_PRODUCT="create table "+TABLE_PRODUCT
            +" ("+COL_ID+" integer primary key autoincrement, "
            +COL_NAME+" text, "
            +COL_DESC+" text, "+COL_IMG+" text)";
}
