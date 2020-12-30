package pl.edu.wszib.magazyn.configuration;

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
    public Connection connection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            String DBname = "Magazyn";

            if(!checkDatabaseExist(DBname, connection)){
                createDB(DBname,connection);
                connection.setCatalog(DBname);
                DBfill(connection);
            };
            connection.setCatalog(DBname);


            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public void DBfill(Connection connection){
        SqlFile sqlFile = null;
        try {
            sqlFile = new SqlFile(new File(System.getProperty("user.dir")+"/src/main/resources/DBfill.sql"));
            sqlFile.setConnection(connection);
            sqlFile.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SqlToolError sqlToolError) {
            sqlToolError.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createDB(String name, Connection connection){
        try{
            String sql = "CREATE DATABASE IF NOT EXISTS "+name+" ;";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException throwables) {
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
