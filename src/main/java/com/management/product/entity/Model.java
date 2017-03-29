package com.management.product.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Abstract class, parent  for all entities
 * @author Вадим
 */

@MappedSuperclass
public abstract class Model implements Serializable{

    /**
     * The version number for a class
     */
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for each model.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Getter for id of model
     * @return id of model
     */
    public int getId() {
        return id;
    }

    /**
     *  Setter for id of model
     * @param id The unique identifier for each model.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param object The reference object with which to compare.
     * @return Returns true if this object is the same as the obj
     * argument, otherwise returns false.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Model model = (Model) object;

        return id == model.id;

    }

    /**
     * Abstract method returns a hash code value for the model.
     *
     * @return a hash code value for this model.
     */
    @Override
    public abstract int hashCode();


    /**
     * Returns a string representation of the model.
     *
     * @return A string representation of the model.
     */
    @Override
    public String toString() {
        return "Model{id=" + id + "}";
    }

}
