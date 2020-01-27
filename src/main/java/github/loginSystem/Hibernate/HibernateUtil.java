package github.loginSystem.Hibernate;

import github.loginSystem.User.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class HibernateUtil {

    private static SessionFactory factory;

    public static void addAllConfigs()
    {
        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(User.class);
        config.addResource("User.hbm.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        factory = config.buildSessionFactory(serviceRegistry);
    }

    public User login(String loginUsername, String loginPassword){
        List<User> userList = getUserList();
        for (User user: userList)
            if (user.getUsername().equals(loginUsername) && user.getPassword().equals(loginPassword))
                return user;
            return null;
    }

    public boolean checkUsername(String loginUsername){
        List<User> userList = getUserList();
        for (User user: userList)
            if (user.getUsername().equals(loginUsername))
                return false;
        return true;
    }

    public static List<User> getUserList() {
        Session session = factory.openSession();

        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);

        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return userList;
    }

    public void deleteUser(User user){
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public void createUser(String username, String password, String email)
    {
        Session session = factory.openSession();
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            User user = new User(username, password, email);

            session.save(user);
            transaction.commit();
        }
        catch (Exception ex)
        {
            if(transaction != null)
                transaction.rollback();
        }
        finally { session.close(); }
    }
}
