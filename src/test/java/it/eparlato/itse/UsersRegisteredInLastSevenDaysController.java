package it.eparlato.itse;

import java.util.Date;

public class UsersRegisteredInLastSevenDaysController {

    private final UsersRepository usersRepository;
    private final ReportWebPage webPage;

    public UsersRegisteredInLastSevenDaysController(UsersRepository usersRepository, ReportWebPage webPage) {
        this.usersRepository = usersRepository;
        this.webPage = webPage;
    }

    public void showRegisteredUserSevenDaysBackFrom(Date date) {
        webPage.showNoCustomerFoundPage();
    }
}
