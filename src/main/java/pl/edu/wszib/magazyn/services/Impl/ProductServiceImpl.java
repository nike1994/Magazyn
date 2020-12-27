package pl.edu.wszib.magazyn.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.magazyn.dao.IProductDAO;
import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductDAO productDAO;

    @Override
    public ProductInstance getProductById(int id) {
        return this.productDAO.getProductByID(id);
    }

    @Override
    public List<ProductInstance> getAllProducts() {
        return this.productDAO.getAll();
    }

    @Override
    public void updateProduct(ProductInstance product) {
        ProductInstance productFromDB = this.productDAO.getProductByID(product.getId());
        // TODO: 27.12.2020 czy wartości są poprawne
        productFromDB.setEAN(product.getEAN());
        productFromDB.setName(product.getName());
        productFromDB.setQuantity(product.getQuantity());
        this.productDAO.updateProduct(product);
    }

    @Override
    public void increaseQuantity(ProductInstance product) {
        // TODO: 27.12.2020 czy ilość nie jest ujemna
        ProductInstance productFromDB = this.productDAO.getProductByID(product.getId());
        this.productDAO.updateQuantity(productFromDB.getQuantity()+product.getQuantity(),product.getId());
    }

    @Override
    public void reduceQuantity(ProductInstance product) {
        // TODO: 27.12.2020 czy ilość jest mniejsza od starej i czy nie jest ujemna
        ProductInstance productFromDB = this.productDAO.getProductByID(product.getId());
        this.productDAO.updateQuantity(productFromDB.getQuantity()-product.getQuantity(),product.getId());
    }

    @Override
    public void insertProduct(ProductInstance product) {
        // TODO: 27.12.2020 sprawdzenie poprawności danych
        this.productDAO.insertProduct(product.getEAN(),product.getName(),product.getQuantity());
    }

    @Override
    public void removeProduct(int id) {
        // TODO: 27.12.2020 sprawdzenie czy istnieje produkt o takim id
        this.productDAO.removeProduct(id);
    }
}
