package shop.springbootapp.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import shop.springbootapp.service.UserActivationService;

public class ActivationLinkCleanUp {
    private final UserActivationService userActivationService;

    public ActivationLinkCleanUp(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(cron = "0 51 14 * * *")
    public void cleanUp(){
        this.userActivationService.deleteObsoleteActivationToken();
    }
}
