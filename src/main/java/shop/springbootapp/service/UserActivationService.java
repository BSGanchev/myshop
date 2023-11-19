package shop.springbootapp.service;

import shop.springbootapp.model.events.RegistrationEvent;

public interface UserActivationService {

    void userRegister(RegistrationEvent event);
}
