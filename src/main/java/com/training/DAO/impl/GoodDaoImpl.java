package com.training.DAO.impl;

import com.training.DAO.GoodDAO;
import com.training.configuration.RootConfig;
import com.training.model.Good;
import com.training.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * It's DAO layer for {@link Good}
 *
 * @author Vlad Skrebunov
 */

@Component
public class GoodDaoImpl implements GoodDAO {

    private final static AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(RootConfig.class);
    private final Logger LOGGER = LogManager.getLogger(GoodDAO.class.getName());

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Good> getAll() throws SQLException {
        System.out.println("\n\nЧтение записей : HQL");
        try (Session session = sessionFactory.openSession()) {
            String sql = "From " + Good.class.getSimpleName();
            System.out.println("sql = " + sql);

            return session.createQuery(sql).list();
        } catch (HibernateException e) {
            LOGGER.error(e);
        }
        return new ArrayList<Good>();
    }

    public Good getById(Integer id) throws SQLException {
        return getGood(id);
    }

    private Good getGood(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            final Good good = session.createQuery("select g from Good g where g.id = :ID", Good.class)
                    .setParameter("ID", id)
                    .uniqueResult();
            return good;
        } catch (HibernateException e) {
            LOGGER.error(e);
        }
        return new Good();
    }
}
