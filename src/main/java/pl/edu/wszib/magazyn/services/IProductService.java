package pl.edu.wszib.magazyn.services;

import pl.edu.wszib.magazyn.model.ProductInstance;

import java.util.List;

public interface IProductService {
    ProductInstance getProductById(int id);
    List<ProductInstance> getAllProducts();
    void updateProduct(ProductInstance product);
    void increaseQuantity(int id, int quantity);
    void reduceQuantity(int id, int quantity);
    void insertProduct(ProductInstance product);
    void removeProduct(int id);


}
