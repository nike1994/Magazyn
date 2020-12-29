package pl.edu.wszib.magazyn.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.dao.IProductDAO;
import pl.edu.wszib.magazyn.model.ProductInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements IProductDAO {

    @Autowired
    Connection connection;

    private ProductInstance createProductInstance( ResultSet result){
        try {
            return new ProductInstance(result.getInt("id"),
                    result.getString("EAN"),
                    result.getString("name"),
                    result.getInt("quantity"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateQuantity(int quantity, int id){
        String sql = "UPDATE product SET quantity= ? WHERE id = ?";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setObject(1,quantity);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean updateProduct(ProductInstance product) {
        String sql = "UPDATE product SET EAN= ?, name =? , quantity = ? WHERE id = ?";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getEAN());
            preparedStatement.setString(2,product.getName());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setInt(4,product.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeProduct(int id) {
        String sql = "DELETE FROM product WHERE id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }


    @Override
    public boolean insertProduct(ProductInstance product){
        String sql = "INSERT INTO product (EAN,name,quantity) VALUES(?,?,?);";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getEAN());
            preparedStatement.setString(2,product.getName());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    @Override
    public List<ProductInstance> getAll() {
        List<ProductInstance> ProductInstanceCollection = new ArrayList<>();
        try{
            String sql = "SELECT * FROM product";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductInstanceCollection.add(createProductInstance(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ProductInstanceCollection;
    }

    @Override
    public ProductInstance getProductByID(int id) {
        String sql= "SELECT * FROM product WHERE id = ?;";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return createProductInstance(resultSet);
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
