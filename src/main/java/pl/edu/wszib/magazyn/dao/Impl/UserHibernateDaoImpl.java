package pl.edu.wszib.magazyn.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.dao.IUserDAO;
import pl.edu.wszib.magazyn.model.User;

import javax.persistence.NoResultException;

@Repository
public class UserHibernateDaoImpl implements IUserDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.User WHERE login = :login");
        query.setParameter("login",login);
        User result = null;
        try {
            result = query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("nie znaleziono usera");
        }
        session.close();
        return result;
    }
}
