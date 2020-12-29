package pl.edu.wszib.magazyn.dao;

import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.Impl.ProductServiceImpl;

import java.util.List;

public interface IProductDAO {
    boolean insertProduct(ProductInstance productInstance);
    boolean removeProduct(int id);
    boolean updateQuantity(int id, int quantity);
    boolean updateProduct(ProductInstance productInstance);
    List<ProductInstance> getAll();
    ProductInstance getProductByID(int id);
}
