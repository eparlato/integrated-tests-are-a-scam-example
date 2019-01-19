package it.eparlato.itse;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UsersRegisteredInLastSevenDaysControllerTest {

    @Test
    public void shouldShowNoCustomerFoundPageIfNoCustomersHasRegistered() throws Exception {

        ReportWebPageSpy webPage = new ReportWebPageSpy();

        InMemoryUsersRepository usersRepository = new InMemoryUsersRepository();
        UsersRegisteredInLastSevenDaysController controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);

        controller.showRegisteredUserSevenDaysBackFrom(dateFromString("10/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    private Date dateFromString(String dateAsString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateAsString);
    }
}
