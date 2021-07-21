package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public User getUserByCar(String model, int series) {
        String HQL = "SELECT DISTINCT u FROM User u LEFT OUTER JOIN Car c ON u.car = c WHERE c.model = :model AND c.series = :series";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(HQL);
        query.setParameter("model", model);
        query.setParameter("series", series);
        User user = query.uniqueResult();
        return user;
    }
}
