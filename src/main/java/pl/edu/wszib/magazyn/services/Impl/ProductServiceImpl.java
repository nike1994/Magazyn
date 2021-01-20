package pl.edu.wszib.magazyn.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.magazyn.dao.IProductDAO;
import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.IProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductDAO productDAO;



    @Override
    public ProductInstance getProductById(int id) {
        ProductInstance product = this.productDAO.getProductByID(id);

        return product;
    }
    @Override
    public ProductInstance getProductByEAN(String EAN) {
        ProductInstance product = this.productDAO.getProductByEAN(EAN);

        return product;
    }

    @Override
    public List<ProductInstance> getAllProducts() {
        List<ProductInstance> products = this.productDAO.getAll();
        return products;
    }


    @Override
    public void increaseQuantity(int id, int quantity) {
        this.productDAO.increaseQuantity( id,  quantity);
    }

    @Override
    public void reduceQuantity(int id, int quantity) {
        this.productDAO.reduceQuantity( id,  quantity);
    }

    @Override
    public void insertProduct(ProductInstance product) {
        this.productDAO.insertProduct(product);
    }

    @Override
    public void removeProduct(int id) {
        this.productDAO.removeProduct(id);
    }

}
