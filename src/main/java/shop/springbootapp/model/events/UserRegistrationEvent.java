package shop.springbootapp.model.events;

import org.springframework.context.ApplicationEvent;

public class UserRegistrationEvent extends ApplicationEvent {
    private final String email;
    private final String username;
    public UserRegistrationEvent(Object source, String email, String username) {
        super(source);
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
