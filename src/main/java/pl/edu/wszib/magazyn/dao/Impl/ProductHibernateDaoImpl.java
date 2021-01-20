package pl.edu.wszib.magazyn.dao.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.magazyn.dao.IProductDAO;
import pl.edu.wszib.magazyn.model.ProductInstance;
import pl.edu.wszib.magazyn.services.IProductService;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ProductHibernateDaoImpl implements IProductDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ProductInstance getProductByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<ProductInstance> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.ProductInstance WHERE id = :id")
                                        .setParameter("id", id);
        ProductInstance product = null;
        try{
            product= query.getSingleResult();
            System.out.println(product.getId());
        }catch (NoResultException e){
            System.out.println("nie znaleziono produktu");
        }finally {
            session.close();
        }
        return product;
    }

    @Override
    public ProductInstance getProductByEAN(String EAN) {
        System.out.println("EAN = " + EAN);
        Session session = this.sessionFactory.openSession();
        Query<ProductInstance> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.ProductInstance WHERE EAN LIKE :ean")
                                        .setParameter("ean", EAN);
        ProductInstance product = null;
        try{
            product= query.getSingleResult();
            System.out.println(product.getEAN());
        }catch (NoResultException e){
            System.out.println("nie znaleziono produktu");
        }finally {
            session.close();
        }
        return product;
    }

    @Override
    public List<ProductInstance> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<ProductInstance> query = session.createQuery("FROM pl.edu.wszib.magazyn.model.ProductInstance");
        List<ProductInstance> products = query.getResultList();
        session.close();
        return products;
    }

    @Override
    public void increaseQuantity(int id, int quantity) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hqlUpdate = "UPDATE pl.edu.wszib.magazyn.model.ProductInstance SET quantity = quantity + :increaseVal WHERE id = :id";
            session.createQuery(hqlUpdate)
                    .setParameter("increaseVal", quantity)
                    .setParameter("id",id)
                    .executeUpdate();
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }

    }

    @Override
    public void reduceQuantity(int id, int quantity) {
        System.out.println("id = " + id + ", quantity = " + quantity);
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hqlUpdate = "UPDATE pl.edu.wszib.magazyn.model.ProductInstance SET quantity = quantity - :increaseVal WHERE id = :id";
            session.createQuery(hqlUpdate)
                    .setParameter("increaseVal", quantity)
                    .setParameter("id",id)
                    .executeUpdate();
            System.out.println("query");
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void insertProduct(ProductInstance product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx=session.beginTransaction();
            session.save(product);
            tx.commit();
        }catch (Exception e){
            if(tx != null){
                tx.rollback();
            }
        }finally {
            session.close();
        }
    }

    @Override
    public void removeProduct(int id) {
        System.out.println("delete");
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        ProductInstance product = session.load(ProductInstance.class,id);

        if(product != null){
            try{
                tx = session.beginTransaction();
                session.delete(product);
                tx.commit();
            }catch (Exception e){
                if(tx != null){
                    tx.rollback();
                }
            }finally {
                session.close();
            }

        }
        session.close();
    }

}
