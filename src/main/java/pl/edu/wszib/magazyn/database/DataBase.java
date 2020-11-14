package pl.edu.wszib.magazyn.database;

import io.jsondb.JsonDBTemplate;
import io.jsondb.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
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

    private void createProductInstance(String EAN, String name, int quantity, String key){
        ProductInstance newproduct = new ProductInstance();

        newproduct.setEAN(EAN);
        newproduct.setName(name);
        newproduct.setQuantity(quantity);
        newproduct.setPrivateKey(key);

        jsonDB.insert(newproduct);
    }


    private void DBFilling(){
        List<String> productsNames = Arrays.asList("klawiatura","myszka","procesor","monitor");

        for (String name : productsNames ){

            long ean = (long) Math.floor(Math.random() * 9000000000000L) + 1000000000000L;
            int quantity = (int) Math.floor(Math.random()*100);
            String key = UUID.randomUUID().toString();

            createProductInstance(String.valueOf(ean),name,quantity,key);
        }

    }


    private void updateProduct(String key, Object value, String EAN){
        Update up = new Update();
        up.set(key, value);

        //wielość liter ma znaczenie
        String jxQuery = String.format("/.[EAN='%s']",EAN);

        jsonDB.findAndModify(jxQuery,up,ProductInstance.class);
    }

    private int getOldQuantity(String EAN){
        ProductInstance product = jsonDB.findById(EAN,ProductInstance.class);
        if(product != null){
            return product.getQuantity();
        }else{
            return -1;
        }
    }

    @Override
    public boolean removeProduct(String EAN) {
        ProductInstance product = jsonDB.findById(EAN,ProductInstance.class);

        if(product!=null){
            String jxQuery = String.format("/.[EAN='%s']",EAN);
            jsonDB.findAndRemove(jxQuery, ProductInstance.class);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean createProduct(String EAN, String name, int quantity){
        ProductInstance product = jsonDB.findById(EAN,ProductInstance.class);
        String key = UUID.randomUUID().toString();

        if(
           product == null && EAN.matches("[0-9]{13}") &&
           name != null && !name.isEmpty() &&
           quantity>=0
        ){
            createProductInstance(EAN,name,quantity,key);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean addQuantity(String EAN, int quantity) {
        int oldQuantity = getOldQuantity(EAN);

        if(oldQuantity>=0 && quantity>=0){
            updateProduct("quantity", oldQuantity+quantity, EAN);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public boolean removeQuantity(String EAN, int quantity) {
        int oldQuantity = getOldQuantity(EAN);

        if(oldQuantity>=0 && (quantity>=0 && quantity<oldQuantity)){
            updateProduct("quantity", oldQuantity-quantity, EAN);
            return true;
        }else {
            return false;
        }
    }


    @Override
    public List<ProductInstance> getAll() {
        return jsonDB.findAll(ProductInstance.class);
    }
}
