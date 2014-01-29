package com.github.springui5.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Simple domain object. Uses JSR-303 validation annotations. Converted to/from Json automatically.
 *
 * @author gushakov
 */
public class Fruit implements Serializable {

    private static long offset = 0L;

    private long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(1)
    private int quantity;

    /**
     * Returns a new value for {@code id} attribute. Uses timestamp adjusted with the static offset. Used only for
     * illustration.
     */
    public static long newId() {
        return System.currentTimeMillis() + offset++;
    }

    public Fruit() {
        // default constructor
    }

    public Fruit(String name, int quantity) {
        this.id = Fruit.newId();
        this.name = name;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Fruit && ((Fruit) obj).getId() == id;
    }

    @Override
    public String toString() {
        return "Fruit [id: " +
                id +
                ", name: " +
                name +
                ", quantity: " +
                quantity +
                "]";
    }
}
