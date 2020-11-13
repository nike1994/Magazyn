package pl.edu.wszib.magazyn.database;

import io.jsondb.JsonDBTemplate;
import io.jsondb.query.Update;
import org.springframework.stereotype.Component;
import pl.edu.wszib.magazyn.model.ProductInstance;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class DataBase implements IDataBase{
    public static JsonDBTemplate jsonDB;

    public DataBase(){
        try {

            DBConfiguration dbConf = new DBConfiguration();
            jsonDB = dbConf.initialization();

            Set<String> collections = jsonDB.getCollectionNames();

            if(!collections.contains("products")){
                jsonDB.createCollection(ProductInstance.class);
                DBFilling();
            }

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

    private void createProduct(String EAN, String name, int quantity, String key){
        ProductInstance product = new ProductInstance();
        product.setEAN(EAN);
        product.setName(name);
        product.setQuantity(quantity);
        product.setPrivateKey(key);

        jsonDB.insert(product);

    }


    private void DBFilling(){
        List<String> productsNames = Arrays.asList("klawiatura","myszka","procesor","monitor");

        for (String name : productsNames ){

            long ean = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
            int quantity = (int) Math.floor(Math.random()*100);
            String key = UUID.randomUUID().toString();

            createProduct(String.valueOf(ean),name,quantity,key);
        }

    }


    private void updateProduct(String key, Object value, String EAN){
        Update up = new Update();
        up.set(key, value);

        //wielość liter ma znaczenie
        String jxQuery = String.format("/.[EAN='%s']",EAN);

        jsonDB.findAndModify(jxQuery,up,ProductInstance.class);
    }


    @Override
    public boolean add(String EAN, int quantity) {
        int oldQuantity = jsonDB.findById(EAN,ProductInstance.class).getQuantity();
        updateProduct("quantity", oldQuantity+quantity, EAN);

        System.out.println("add");
        return false;
    }


    @Override
    public boolean remove(String EAN, int quantity) {
        int oldQuantity = jsonDB.findById(EAN,ProductInstance.class).getQuantity();
        updateProduct("quantity", oldQuantity-quantity, EAN);
        return false;
    }


    @Override
    public List<ProductInstance> selectAll() {
        return jsonDB.findAll(ProductInstance.class);
    }
}
