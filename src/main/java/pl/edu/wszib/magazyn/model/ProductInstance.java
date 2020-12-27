package pl.edu.wszib.magazyn.model;

import org.springframework.stereotype.Component;

@Component
public class ProductInstance {

    private  int id;
    private String EAN;
    private String name;
    private int quantity;

    public ProductInstance(int id, String ean, String name, int quantity) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
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
}

