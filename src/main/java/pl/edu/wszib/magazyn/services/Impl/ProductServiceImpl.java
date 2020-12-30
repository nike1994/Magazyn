package pl.edu.wszib.magazyn.services.Impl;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.formatter.BarcodeFormatter;
import net.sourceforge.barbecue.output.OutputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.magazyn.dao.IProductDAO;
import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.IProductService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
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
        ProductInstance productFromDB = this.productDAO.getProductByID(id);
        this.productDAO.updateQuantity(productFromDB.getQuantity()+quantity,id);
    }

    @Override
    public void reduceQuantity(int id, int quantity) {
        ProductInstance productFromDB = this.productDAO.getProductByID(id);
        this.productDAO.updateQuantity(productFromDB.getQuantity()-quantity,id);
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
