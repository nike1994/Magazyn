package pl.edu.wszib.magazyn.dao;

import pl.edu.wszib.magazyn.model.ProductInstance;

import java.util.List;

public interface IProductDAO {
    boolean insertProduct(String EAN, String name, int quantity);
    boolean removeProduct(int id);
    boolean updateQuantity(int id, int quantity);
    boolean updateProduct(ProductInstance productInstance);
    List<ProductInstance> getAll();
    ProductInstance getProductByID(int id);
}
