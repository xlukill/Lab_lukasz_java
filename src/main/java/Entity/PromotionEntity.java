package Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "promotion", schema = "public", catalog = "App")
@NamedQueries(
        {
                @NamedQuery(name="Promotion.findAll", query="select e from PromotionEntity e")
        }
)
public class PromotionEntity {
    private int idPromotion;
    private double promotion;

    @Id
    @Column(name = "id_promotion")
    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
    }

    @Basic
    @Column(name = "promotion")
    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionEntity that = (PromotionEntity) o;

        if (idPromotion != that.idPromotion) return false;
        if (Double.compare(that.promotion, promotion) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idPromotion;
        temp = Double.doubleToLongBits(promotion);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    List<ReservationEntity> reservationEntity;

    @OneToMany(mappedBy="promotionEntity")

    public List<ReservationEntity> getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(List<ReservationEntity> reservationEntity) {
        this.reservationEntity = reservationEntity;
    }
}
