package DAO;

import Entity.FlyEntity;
import Utils.HibernateUtil;
import Entity.UsersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class UsersDAO {
    public List<UsersEntity> getAllUsers(){ // zwraca wszystkich uzytkownikow systemu
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery
                    ("Users.findAll", UsersEntity.class).getResultList();
        }
    }

    public boolean registerUser(String user,String password){
        try {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                UsersEntity usersEntity = new UsersEntity();

                usersEntity.setLogin(user.trim());
                usersEntity.setPassword(password.trim());
                usersEntity.setActive(true);

                session.save(usersEntity);

                session.getTransaction().commit();
                return true;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
        }
        return true;
    }
}

