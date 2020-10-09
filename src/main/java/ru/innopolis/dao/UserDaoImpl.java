package ru.innopolis.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.innopolis.dto.UserDto;
import ru.innopolis.pojo.UsersEntity;
import ru.innopolis.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser(UsersEntity user) {
        Transaction transaction = null;
        try (Session session = ru.innopolis.util.HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the user object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        List<UsersEntity> usersEntityList;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            usersEntityList = session.createQuery("from UsersEntity ", UsersEntity.class).list();
            for (UsersEntity user : usersEntityList)
                users.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getCityId(), user.getEmail(), user.getPassword(), user.getPhone()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }
}
