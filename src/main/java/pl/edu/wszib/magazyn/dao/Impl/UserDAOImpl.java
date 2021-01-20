package pl.edu.wszib.magazyn.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.dao.IUserDAO;
import pl.edu.wszib.magazyn.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl implements IUserDAO {

    @Autowired
    Connection connection;

    @Override
    public User getUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE login = ?";
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPass(resultSet.getString("pass"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
