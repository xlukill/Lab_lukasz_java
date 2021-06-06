package DAO;

import Entity.UserInfoEntity;
import Entity.UsersEntity;
import Utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;


public class UsersInfoDAO {
    public void updateActiveUser(String log){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
           session.createQuery("update UserInfoEntity set login = :log where idUser = 1").setParameter("log",log).executeUpdate();
        }
    }

    public List<UserInfoEntity> getUser(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery
                    ("User.findAll", UserInfoEntity.class).getResultList();
        }
    }
}
