package com.management.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * The class implements a set of standard methods for working
 * with entity of the Product.
 *
 * @author Вадим
 */
@Entity
@Table(name = "products")
public class Product extends Model {

    /**
     * The product name
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * The manufacturer`s name of  the product
     */
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    /**
     * The product description
     */
    @Column(name = "description", nullable = false)
    private  String description;

    /**
     * The product cost
     */
    @Column(name = "cost")
    private int cost;

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * Constructor
     *
     * @param title a name of the new product
     * @param cost a cost of the new product
     */
    public Product(String title, int cost) {
        this.setTitle(title);
        this.setCost(cost);
    }

    /**
     * Constructor
     *
     * @param title a name of the new product
     * @param manufacturer a manufacturers name of the new product
     * @param cost a cost of the new product
     */
    public Product(String title, String manufacturer, int cost) {
        this(title, cost);
        this.setManufacturer(manufacturer);
    }

    /**
     * Constructor
     *
     * @param title a name of the new product
     * @param manufacturer a manufacturers name of the new product
     * @param description a product description
     * @param cost a cost of the new product
     */
    public Product(String title, String manufacturer, String description, int cost) {
        this(title, manufacturer, cost);
        this.setDescription(description);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Product{" + super.toString()+
                ", title='" + title + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                "} ";
    }

    @Override
    public boolean equals(Object object) {

        boolean res = super.equals(object);
        if(res) {
            final Product product = (Product) object;
            res =  (cost == product.cost)&&
                    (title != null ? title.equals(product.title) : product.title == null)&&
                    (manufacturer != null ? manufacturer.equals(product.manufacturer) : product.manufacturer == null)&&
                    (description != null ? description.equals(product.description) : product.description == null);
        }
        return res;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    /**
     * Getters and setters methods for all fields of product
     **/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = isNotBlank(title)?title:"";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = isNotBlank(manufacturer)? manufacturer:"";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = isNotBlank(description)?description:"";
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost>0?cost:0;
    }
}
