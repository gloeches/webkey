package local.loeches.webkey.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Keypass {
    @Id     @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="keypass_generator")
    private Long id;
    private String username;
    private String password;
    @ManyToOne(fetch=FetchType.EAGER, optional = false)
    @JoinColumn(name="enterprise_id" , nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private Enterprise enterprise;


    public Keypass(String user, String password) {
        this.username = user;
        this.password = password;
    }

    public Keypass() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
