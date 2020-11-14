package pl.edu.wszib.magazyn.database;

import pl.edu.wszib.magazyn.model.ProductInstance;

import java.util.List;

public interface IDataBase {
    boolean add(String EAN, int quantity);
    boolean remove(String EAN, int quantity);
    boolean createProduct(String EAN, String name, int quantity);
    List<ProductInstance> getAll();
}
