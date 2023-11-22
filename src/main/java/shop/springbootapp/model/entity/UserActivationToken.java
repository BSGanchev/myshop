package shop.springbootapp.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Entity
@Table(name = "user_activation_tokens")
public class UserActivationToken extends BaseEntity {
    private String activationToken;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Instant created;
    @ManyToOne()
    private AppUser user;

    public UserActivationToken() {
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
