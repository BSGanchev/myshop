package shop.springbootapp.model.events;

import org.springframework.context.ApplicationEvent;

public class RegistrationEvent extends ApplicationEvent {
    private String userEmail;

    public RegistrationEvent(Object source, String userEmail) {
        super(source);
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
