package pl.edu.wszib.magazyn.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.magazyn.dao.IUserDAO;
import pl.edu.wszib.magazyn.model.User;
import pl.edu.wszib.magazyn.services.IUserService;
import pl.edu.wszib.magazyn.session.SessionObj;

import javax.annotation.Resource;

public class UserServiceImpl implements IUserService {


    @Resource
    SessionObj sessionObject;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void authenticate(User user) {
        User userFromDatabase = this.userDAO.getUserByLogin(user.getLogin());
        if(userFromDatabase == null) {
            return;
        }

        if(user.getPass().equals(userFromDatabase.getPass())) {
            this.sessionObject.setLoggedUser(userFromDatabase);
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }
}
