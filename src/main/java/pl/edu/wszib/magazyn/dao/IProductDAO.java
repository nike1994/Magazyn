package pl.edu.wszib.magazyn.dao;

import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.Impl.ProductServiceImpl;

import java.util.List;

public interface IProductDAO {
    void insertProduct(ProductInstance productInstance);
    void removeProduct(int id);
    //boolean updateQuantity(int id, int quantity);
    void reduceQuantity(int id, int quantity);
    void increaseQuantity(int id, int quantity);
    List<ProductInstance> getAll();
    ProductInstance getProductByID(int id);
    ProductInstance getProductByEAN(String EAN);
}
