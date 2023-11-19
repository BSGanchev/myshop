package shop.springbootapp.service.impl;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import shop.springbootapp.model.events.RegistrationEvent;
import shop.springbootapp.service.UserActivationService;
@Service
public class UserActivationServiceImpl implements UserActivationService {
    @Override
    @EventListener(RegistrationEvent.class)
    public void userRegister(RegistrationEvent event) {

        System.out.println(event.getUserEmail());

    }
}
