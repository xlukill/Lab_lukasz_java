package DAO;

import Entity.PromotionEntity;
import Entity.UsersEntity;
import Utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PromotionDAO {
    public List<PromotionEntity> getAllPromotion(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createNamedQuery
                    ("Promotion.findAll", PromotionEntity.class).getResultList();
        }
    }
}
