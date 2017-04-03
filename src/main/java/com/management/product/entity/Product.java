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
    private String description;

    /**
     * The product cost
     */
    @Column(name = "cost")
    private int cost;

    /**
     * Default constructor
     */
    public Product() {
        this.title = "";
        this.manufacturer = "";
        this.description = "";
    }

    /**
     * Constructor
     *
     * @param title a name of the new product
     * @param cost  a cost of the new product
     */
    public Product(String title, int cost) {
        this();
        setTitle(title);
        setCost(cost);
    }

    /**
     * Constructor
     *
     * @param title        a name of the new product
     * @param manufacturer a manufacturers name of the new product
     * @param cost         a cost of the new product
     */
    public Product(String title, String manufacturer, int cost) {
        this(title, cost);
        setManufacturer(manufacturer);
    }

    /**
     * Constructor
     *
     * @param title        a name of the new product
     * @param manufacturer a manufacturers name of the new product
     * @param description  a product description
     * @param cost         a cost of the new product
     */
    public Product(String title, String manufacturer, String description, int cost) {
        this(title, manufacturer, cost);
        setDescription(description);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Product{" + super.toString() +
                ", title='" + this.title + '\'' +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", description='" + this.description + '\'' +
                ", cost=" + this.cost +
                "} ";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return true if this object is the same as the obj argument
     */
    @Override
    public boolean equals(Object object) {
        boolean res = super.equals(object);
        if (res) {
            final Product product = (Product) object;
            res = (this.cost == product.cost) &&
                    (this.title.equals(product.title)) &&
                    (this.manufacturer.equals(product.manufacturer)) &&
                    (this.description.equals(product.description));
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
        int result = this.title.hashCode();
        result = 31 * result + this.manufacturer.hashCode();
        result = 31 * result + this.description.hashCode();
        result = 31 * result + this.cost;
        return result;
    }

    /**
     * Getter for name of the product
     *
     * @return a name of the  product
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for name product
     * Method that sets a name of the new product,
     * if parameter is null or is whitespace method sets
     * empty string as name of new product
     *
     * @param title a name of the new product
     */
    public void setTitle(String title) {
        this.title = isNotBlank(title) ? title : "";
    }

    /**
     * Getter for  manufacturer`s name of the product
     *
     * @return a manufacturer`s name of the product
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * Setter for manufacturer`s name of the new product
     * Method that sets a manufacturer`s name of the new product,
     * if parameter is null or is whitespace method sets empty string
     * as manufacturer`s name of new product
     *
     * @param manufacturer a manufacturer`s name of the new product
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = isNotBlank(manufacturer) ? manufacturer : "";
    }

    /**
     * Getter for description of product
     *
     * @return a description of the product
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for description of the new product
     * Method that sets a description of the new product,
     * if parameter is null or is whitespace method sets
     * empty string as description of new product
     *
     * @param description a description of the new product
     */
    public void setDescription(String description) {
        this.description = isNotBlank(description) ? description : "";
    }

    /**
     * Getter for product`s cost
     *
     * @return a product`s cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Setter for cost of the new product
     * Method that sets a cost of the new product,
     * if parameter is negative method sets 0 as product`s cost
     *
     * @param cost a product`s cost
     */
    public void setCost(int cost) {
        this.cost = cost > 0 ? cost : 0;
    }
}
