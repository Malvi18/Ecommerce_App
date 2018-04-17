package tops.com.e_commerce.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Admin on 12/11/2017.
 */

public class Product implements Serializable{
    private int id;
    private String name, description;
    private Bitmap bitmap;
    private String imgName;
    private int price, qty;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public Product(int id, String name, String description, Bitmap bitmap) {
        setId(id);
        setName(name);
        setDescription(description);
        setBitmap(bitmap);
    }
    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
