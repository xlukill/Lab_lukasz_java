package Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Table(name = "date", schema = "public", catalog = "App")
@Entity
@NamedQueries(
        {
                @NamedQuery(name="Date.FindAll", query="select e from DateEntity e")
        }
)
public class DateEntity {

    private int idDate;
    private Date date;

    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name = "id_date")
    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateEntity that = (DateEntity) o;

        if (idDate != that.idDate) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDate;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }


    private FlyEntity flyEntity;

    @OneToOne(mappedBy = "dateEntity")
    public FlyEntity getFlyEntity() {
        return flyEntity;
    }

    public void setFlyEntity(FlyEntity flyEntity) {
        this.flyEntity = flyEntity;
    }
}
