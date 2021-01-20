package pl.edu.wszib.magazyn.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Entity(name="products")
public class ProductInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String EAN;
    private String name;
    private int quantity;

    public ProductInstance(){}

    public ProductInstance(int id, String ean, String name, int quantity) {
        this.id = id;
        this.EAN = ean;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEANBase64(){
        try {
            Barcode barcode = BarcodeFactory.createEAN13(this.EAN);
            barcode.setBarHeight(100);
            barcode.setBarWidth(2);
            barcode.setDrawingText(false);


            BufferedImage img = BarcodeImageHandler.getImage(barcode);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img,"png",baos);
            byte[] imgArrayByte = baos.toByteArray();

            String imgBase64 = Base64.getEncoder().encodeToString(imgArrayByte);

            return imgBase64;
        } catch (BarcodeException | OutputException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

