import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Самойлов Аркадий on 02.02.2018.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "login")
    private String login;

    @OneToMany(mappedBy = "user")
    private Set<Disk>disks;

    public Set<Disk> getDisks() {
        return disks;
    }

    public void setDisks(Set<Disk> disks) {
        this.disks = disks;
    }

    public User(long id){
        this.id=id;
    }
    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return getId()+" - "+getLogin()+"\n";
    }
}
