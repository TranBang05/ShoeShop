package Model;

import java.util.ArrayList;

public class Products {

    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String title;
    private int cateId;
    private String cateName;

    public Products() {
    }

    public Products(int id, String name, String description,double price, String image , String title) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public Products(int id, String name, String description, double price, String image, int cateId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.cateId = cateId;
    }

    public Products(int id, String name, String description, double price, String image, int cateId, String cateName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.cateId = cateId;
        this.cateName = cateName;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "Products{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", image=" + image + ", title=" + title + ", cateId=" + cateId + ", cateName=" + cateName + '}';
    }



}
