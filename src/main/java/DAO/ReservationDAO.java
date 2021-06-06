package DAO;

import Entity.FlyEntity;
import Entity.PromotionEntity;
import Entity.ReservationEntity;
import Entity.UsersEntity;
import Utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ReservationDAO {

    public boolean addReservation(UsersEntity usersEntity, FlyEntity flyEntity, PromotionEntity promotionEntity, double cost){

        try {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                ReservationEntity reservationEntity = new ReservationEntity();

                reservationEntity.setUsersEntity(usersEntity);
                reservationEntity.setFlyEntity(flyEntity);
                reservationEntity.setPromotionEntity(promotionEntity);
                reservationEntity.setFullcost(cost);

                session.save(usersEntity);
                session.save(reservationEntity);

                session.save(flyEntity);
                session.save(promotionEntity);

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
