package shop.springbootapp.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import shop.springbootapp.service.UserActivationService;
import shop.springbootapp.service.UserService;

public class ActivationLinkCleanUp {
    private final UserActivationService userActivationService;
    private final UserService userService;

    public ActivationLinkCleanUp(UserActivationService userActivationService, UserService userService) {
        this.userActivationService = userActivationService;
        this.userService = userService;
    }

    @Scheduled(cron = "0 10 * * * *")
    public void cleanUp() {
        this.userActivationService.deleteObsoleteActivationToken();
        this.userService.deleteUnusedRegistration();
    }
}
