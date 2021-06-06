package DAO;

import Entity.DateEntity;
import Entity.FlyEntity;
import Entity.UsersEntity;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class FlyDAO {
    public boolean setFly(String country,String city, double cost, boolean available, DateEntity date){

        try {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                FlyEntity flyEntity = new FlyEntity();

                flyEntity.setCountry(country);
                flyEntity.setCity(city);
                flyEntity.setCost(cost);
                flyEntity.setAvailable(available);
                flyEntity.setDateEntity(date);

                session.save(flyEntity);

                session.getTransaction().commit();
                return true;
            }
        }
        catch(HibernateException e){
            e.printStackTrace();
        }

        return true;
    }

    public List<FlyEntity> getAllFlies(){ // zwraca wszystkich uzytkownikow systemu
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery
                    ("Fly.findAll", FlyEntity.class).getResultList();
        }
    }
}
