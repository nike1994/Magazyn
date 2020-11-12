package pl.edu.wszib.magazyn;

import io.jsondb.JsonDBTemplate;
import pl.edu.wszib.magazyn.database.DBConfiguration;
import pl.edu.wszib.magazyn.database.DataBase;
import pl.edu.wszib.magazyn.database.ProductInstance;

import java.security.GeneralSecurityException;

public class App {
    public static void main(String[] args) throws GeneralSecurityException {

        DataBase db = new DataBase();
        db.add("3039532524879", 20);
    }
}
