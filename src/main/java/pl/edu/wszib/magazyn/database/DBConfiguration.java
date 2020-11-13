package pl.edu.wszib.magazyn.database;

import io.jsondb.JsonDBTemplate;
import io.jsondb.crypto.Default1Cipher;
import io.jsondb.crypto.ICipher;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.util.logging.Logger;

public class DBConfiguration {
    //Actual location on disk for database files, process should have read-write permissions to this folder
    private  String dbFilesLocation = "src/main/resources/dbFiles";

    //Java package name where POJO's are present
    private  String baseScanPackage = "pl.edu.wszib.magazyn";

    //Optionally a Cipher object if you need Encryption
    private ICipher cipher = new Default1Cipher("1r8+24pibarAWgS85/Heeg==");;


    public DBConfiguration() throws GeneralSecurityException {

    }


    public JsonDBTemplate  initialization() {
        return new JsonDBTemplate(dbFilesLocation, baseScanPackage, cipher);
    }
}
