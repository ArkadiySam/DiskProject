import javax.persistence.*;

/**
 * Created by Самойлов Аркадий on 02.02.2018.
 */
@Entity
@Table(name = "disk")
public class Disk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String Title;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return getId()+" - "+getTitle()+" - "+getUser().getId();
    }
}
