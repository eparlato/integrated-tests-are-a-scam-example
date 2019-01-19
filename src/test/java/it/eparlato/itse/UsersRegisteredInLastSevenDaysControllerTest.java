package it.eparlato.itse;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UsersRegisteredInLastSevenDaysControllerTest {
    ReportWebPageSpy webPage;

    @Before
    public void init() {
        webPage = new ReportWebPageSpy();
    }

    @Test
    public void shouldShowNoCustomerFoundPageIfNoCustomersHasRegistered() throws Exception {
        InMemoryUsersRepository usersRepository = new InMemoryUsersRepository();
        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUserSevenDaysBackFrom("10/01/2019");

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    @Test
    @Ignore
    public void shouldShowCustomerDetailPageIfOneCustomerIsFound() throws Exception{
        InMemoryUsersRepository usersRepository = new InMemoryUsersRepository();
        usersRepository.registerCustomer(new Customer("04/01/2019"));

        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUserSevenDaysBackFrom("10/01/2019");

        assertThat(webPage.showCustomerDetailPageHasBeenCalled(), is(true));
    }

}
