package it.eparlato.itse;

import it.eparlato.itse.server.UsersRepository;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class UsersRegisteredInLastSevenDaysController {

    private final UsersRepository usersRepository;
    private final ReportWebPage webPage;

    public UsersRegisteredInLastSevenDaysController(UsersRepository usersRepository, ReportWebPage webPage) {
        this.usersRepository = usersRepository;
        this.webPage = webPage;
    }

    public void showRegisteredUsersSevenDaysBackFrom(Calendar date) throws ParseException {
        Calendar sevenDaysAgo = sevenDaysAgoFrom(date);
        List<Customer> totalRegisteredCustomers = usersRepository.findAllCustomersWhoHaveSignedUpSince(sevenDaysAgo);

        if (totalRegisteredCustomers.size() == 0) {
            webPage.showNoCustomerFoundPage();
        } else if (totalRegisteredCustomers.size() == 1){
            webPage.showCustomerDetailPage(totalRegisteredCustomers.get(0));
        } else {
            webPage.showCustomersListPage(totalRegisteredCustomers);
        }
    }

    private Calendar sevenDaysAgoFrom(Calendar date) {
        date.add(Calendar.DAY_OF_YEAR, -7);

        return date;
    }
}
