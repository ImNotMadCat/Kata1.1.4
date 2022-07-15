package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;
    private String sql;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        sql = "CREATE TABLE if not exists user (" +
                "id INT PRIMARY KEY auto_increment, " +
                "name varchar(100)," +
                "lastname varchar(100)," +
                "age smallint(3))";
        try (Session session = Util.getSessionFactory().openSession()
        ) {
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            session.beginTransaction();
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Исключение create");
        }

    }

    @Override
    public void dropUsersTable() {

        sql = "Drop TABLE if exists user";
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Исключение drop");

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Исключение removeUserById");
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            users = session.createQuery("from User ").list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "delete from User";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
