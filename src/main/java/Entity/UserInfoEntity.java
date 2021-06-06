package Entity;

import javax.persistence.*;

@Table(name = "userinfo", schema = "public", catalog = "App")
@Entity
@NamedQueries(
        {
                @NamedQuery(name = "ActiveUpdate", query = "update UserInfoEntity set login = :log where idUser = 1"),
                @NamedQuery(name="User.findAll", query="select e from UserInfoEntity e")
        }
)
public class UserInfoEntity {
    private int idUser;
    private String login;

    @Id
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfoEntity that = (UserInfoEntity) o;

        if (idUser != that.idUser) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
