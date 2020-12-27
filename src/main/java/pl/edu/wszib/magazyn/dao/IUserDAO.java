package pl.edu.wszib.magazyn.dao;

import pl.edu.wszib.magazyn.model.User;

public interface IUserDAO {
    User getUserByLogin(String login);
}
