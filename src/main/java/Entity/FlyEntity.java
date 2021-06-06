package Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Table(name = "fly", schema = "public", catalog = "App")
@Entity
@NamedQueries(
        {
                @NamedQuery(name="Fly.findAll", query="select e from FlyEntity e")
        }
)
public class FlyEntity {

    private int idFly;
    private String country;
    private String city;
    private double cost;
    private boolean available;

    @Id
    @Column(name = "id_fly")
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    public int getIdFly() {
        return idFly;
    }

    public void setIdFly(int idFly) {
        this.idFly = idFly;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "available")
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlyEntity flyEntity = (FlyEntity) o;

        if (idFly != flyEntity.idFly) return false;
        if (Double.compare(flyEntity.cost, cost) != 0) return false;
        if (available != flyEntity.available) return false;
        if (country != null ? !country.equals(flyEntity.country) : flyEntity.country != null) return false;
        if (city != null ? !city.equals(flyEntity.city) : flyEntity.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idFly;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (available ? 1 : 0);
        return result;
    }


    private DateEntity dateEntity;
    @OneToOne
    @JoinColumn(name="id_date")
    public DateEntity getDateEntity() {
        return dateEntity;
    }

    public void setDateEntity(DateEntity dateEntity) {
        this.dateEntity = dateEntity;
    }

    List<ReservationEntity> reservationEntity;

    @OneToMany(mappedBy="flyEntity")

    public List<ReservationEntity> getReservationEntity() {
        return reservationEntity;
    }

    public void setReservationEntity(List<ReservationEntity> reservationEntity) {
        this.reservationEntity = reservationEntity;
    }
}
