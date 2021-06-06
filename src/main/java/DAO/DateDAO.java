package DAO;

import Entity.DateEntity;
import Entity.UsersEntity;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

public class DateDAO {
    public boolean addDateToSys(Date date){
        try {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                DateEntity dateEntity = new DateEntity();

                dateEntity.setDate(date);

                session.save(dateEntity);
                session.getTransaction().commit();
                return true;
            }
        }
            catch(HibernateException e){
                e.printStackTrace();
            }
        return false;
    }

    public List<DateEntity> dateList(){ // zwraca wszystkich uzytkownikow systemu
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery
                    ("Date.FindAll", DateEntity.class).getResultList();
        }
    }
}
