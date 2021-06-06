package Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "reservation", schema = "public", catalog = "App")
public class ReservationEntity {
    private int idReservation;
    private double fullcost;

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "id_reservation")
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Basic
    @Column(name = "fullcost")
    public double getFullcost() {
        return fullcost;
    }

    public void setFullcost(double fullcost) {
        this.fullcost = fullcost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationEntity that = (ReservationEntity) o;

        if (idReservation != that.idReservation) return false;
        if (Double.compare(that.fullcost, fullcost) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idReservation;
        temp = Double.doubleToLongBits(fullcost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    private UsersEntity usersEntity;
    @ManyToOne
    @JoinColumn(name="id_user")

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    private FlyEntity flyEntity;
    @ManyToOne
    @JoinColumn(name="id_fly")

    public FlyEntity getFlyEntity() {
        return flyEntity;
    }

    public void setFlyEntity(FlyEntity flyEntity) {
        this.flyEntity = flyEntity;
    }

    private PromotionEntity promotionEntity;
    @ManyToOne
    @JoinColumn(name="id_promotion")

    public PromotionEntity getPromotionEntity() {
        return promotionEntity;
    }

    public void setPromotionEntity(PromotionEntity promotionEntity) {
        this.promotionEntity = promotionEntity;
    }
}
