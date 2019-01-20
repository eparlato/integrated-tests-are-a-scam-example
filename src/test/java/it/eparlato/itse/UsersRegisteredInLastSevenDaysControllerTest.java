package it.eparlato.itse;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UsersRegisteredInLastSevenDaysControllerTest {
    ReportWebPageSpy webPage;
    InMemoryUsersRepository usersRepository;

    @Before
    public void init() {
        webPage = new ReportWebPageSpy();
        usersRepository = new InMemoryUsersRepository();
    }

    @Test
    public void shouldShowNoCustomerFoundPageIfNoCustomersHasRegistered() throws Exception {
        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUserSevenDaysBackFrom(DateUtils.calendarFromString("10/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldShowCustomerDetailPageIfOneCustomerIsFound() throws Exception {
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("04/01/2019")));

        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUserSevenDaysBackFrom(DateUtils.calendarFromString("10/01/2019"));

        assertThat(webPage.showCustomerDetailPageHasBeenCalled(), is(true));
    }

}
