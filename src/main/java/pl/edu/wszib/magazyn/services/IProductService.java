package pl.edu.wszib.magazyn.services;

import pl.edu.wszib.magazyn.model.ProductInstance;

import java.util.List;

public interface IProductService {
    ProductInstance getProductById(int id);
    List<ProductInstance> getAllProducts();
    void updateProduct(ProductInstance product);
    void increaseQuantity(ProductInstance product);
    void reduceQuantity(ProductInstance product);
    void insertProduct(ProductInstance product);
    void removeProduct(int id);


}
