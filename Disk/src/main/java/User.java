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
    @Column(name = "age")
    private int age;
    @Column(name = "firstname",length = 20)
    private String firstname;
    @Column(name = "lastname",length = 20)
    private String lastname;

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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return getId()+" - "+getFirstname()+" - "+getLastname()+" - "+getAge();
    }
}
