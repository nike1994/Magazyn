package pl.edu.wszib.magazyn.database;

import pl.edu.wszib.magazyn.model.ProductInstance;

import java.util.List;

public interface IDataBase {
    boolean addQuantity(String EAN, int quantity);
    boolean removeQuantity(String EAN, int quantity);
    boolean createProduct(String EAN, String name, int quantity);
    boolean removeProduct(String EAN);
    List<ProductInstance> getAll();
}
