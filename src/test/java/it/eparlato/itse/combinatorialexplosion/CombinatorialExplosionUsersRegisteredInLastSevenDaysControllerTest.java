package it.eparlato.itse.combinatorialexplosion;

import it.eparlato.itse.*;
import it.eparlato.itse.server.InMemoryUsersRepository;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CombinatorialExplosionUsersRegisteredInLastSevenDaysControllerTest {
    ReportWebPageSpy webPage;
    InMemoryUsersRepository usersRepository;

    @Before
    public void init() {
        webPage = new ReportWebPageSpy();
        usersRepository = new InMemoryUsersRepository();
    }

    // Combinatorial explosion: each controller test should consider all the available behaviours
    // (tests) of the usersRepository

    @Test
    public void shouldShowNoCustomerFoundPageIfRegisteredListIsEmpty() throws Exception {
        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUsersSevenDaysBackFrom(date("27/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldShowNoCustomerFoundPageIfThereAreCustomersButNoneRegisteredSevenDaysBefore() throws Exception {
        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);
        usersRepository.registerCustomer(new Customer(date("09/01/2019")));
        usersRepository.registerCustomer(new Customer(date("05/01/2019")));
        usersRepository.registerCustomer(new Customer(date("04/01/2019")));

        controller.showRegisteredUsersSevenDaysBackFrom(date("17/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldNotShowNoCustomerFoundPageIfThereIsOnlyOneCustomerRegisteredSevenDaysBefore() throws Exception {
        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);
        usersRepository.registerCustomer(new Customer(date("08/01/2019")));
        usersRepository.registerCustomer(new Customer(date("10/01/2019")));
        usersRepository.registerCustomer(new Customer(date("02/01/2019")));

        controller.showRegisteredUsersSevenDaysBackFrom(date("17/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(false));
    }

    @Test
    public void shouldShowCustomerDetailPageIfThereIsOnlyOneCustomerRegisteredSevenDaysBefore() throws Exception {
        usersRepository.registerCustomer(new Customer(date("01/01/2019")));
        usersRepository.registerCustomer(new Customer(date("10/01/2019")));
        usersRepository.registerCustomer(new Customer(date("02/01/2019")));

        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUsersSevenDaysBackFrom(date("10/01/2019"));

        assertThat(webPage.showCustomerDetailPageHasBeenCalled(), is(true));
    }

    // TODO: complete collaboration tests...

    private Calendar date(String dateAsString) throws ParseException {
        return DateUtils.calendarFromString(dateAsString);
    }

}
