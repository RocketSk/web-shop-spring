package com.training.DAO.impl;

import com.training.DAO.UserDAO;
import com.training.configuration.RootConfig;
import com.training.model.User;
import com.training.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * It's DAO layer for {@link User}
 *
 * @author Vlad Skrebunov
 */

@Component
public class UserDaoImpl implements UserDAO {

    private final static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(RootConfig.class);

    private final Logger LOGGER = LogManager.getLogger(UserDAO.class.getName());

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public User getUserById(Integer id) throws SQLException {
        return getUser(id);
    }

    public User getUserByUsername(String username) {
        return getUser(username);
    }

    @Override
    public void addUser(User user) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.error(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    private User getUser(String username) {
        try (Session session = sessionFactory.openSession()) {
            final User user = session.createQuery("select p from User p where p.username = :USERNAME", User.class)
                    .setParameter("USERNAME", username)
                    .uniqueResult();
            return user;
        } catch (HibernateException e) {
            LOGGER.error(e);
        }
        return new User();
    }

    private User getUser(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            final User user = session.createQuery("select p from User p where p.id = :ID", User.class)
                    .setParameter("ID", id)
                    .uniqueResult();
            return user;
        } catch (HibernateException e) {
            LOGGER.error(e);
        }
        return new User();
    }
}
