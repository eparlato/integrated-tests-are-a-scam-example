package it.eparlato.itse;

import java.util.List;

public class UsersRegisteredInLastSevenDaysController {

    private final UsersRepository usersRepository;
    private final ReportWebPage webPage;

    public UsersRegisteredInLastSevenDaysController(UsersRepository usersRepository, ReportWebPage webPage) {
        this.usersRepository = usersRepository;
        this.webPage = webPage;
    }

    public void showRegisteredUserSevenDaysBackFrom(String date) {
        List<Customer> totalRegisteredCustomers = usersRepository.findAllCustomersWhoHaveSignedUpSince(date);

        if (totalRegisteredCustomers.size() == 0) {
            webPage.showNoCustomerFoundPage();
        } else {
            webPage.showCustomerDetailPage(totalRegisteredCustomers.get(0));
        }
    }
}
