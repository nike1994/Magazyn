package pl.edu.wszib.magazyn.configuration;

import org.hibernate.SessionFactory;
import org.hsqldb.cmdline.SqlFile;
import org.hsqldb.cmdline.SqlToolError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.sql.*;

@Configuration
@ComponentScan("pl.edu.wszib.magazyn")

public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory(){
        createDBifExist();
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }


    public void createDBifExist(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost", "root", "");
            String DBname = "Magazyn";
            if(!(checkDatabaseExist(DBname,connection))){
                String sql = "CREATE DATABASE IF NOT EXISTS "+DBname+" ;";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }


        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public boolean checkDatabaseExist(String name, Connection connection){
        Statement statement =null;

        try {

            statement = connection.createStatement();

            DatabaseMetaData meta = connection.getMetaData();
            ResultSet resultSet = meta.getCatalogs();

            while(resultSet.next()){
                String DBName = resultSet.getString(1).toLowerCase();
                if(DBName.equals(name.toLowerCase())){
                    System.out.println(true);
                    return true;
                }
            }

        } catch ( SQLException e) {
            e.printStackTrace();
        }

        System.out.println(false);
        return false;
    }
}
