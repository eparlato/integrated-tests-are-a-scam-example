package it.eparlato.itse.collaboration;

import it.eparlato.itse.Customer;
import it.eparlato.itse.DateUtils;
import it.eparlato.itse.ReportWebPageSpy;
import it.eparlato.itse.UsersRegisteredInLastSevenDaysController;
import it.eparlato.itse.server.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

public class CollaborationUsersRegisteredInLastSevenDaysControllerTest {
    ReportWebPageSpy webPage;
    UsersRepository usersRepository = Mockito.mock(UsersRepository.class);
    UsersRegisteredInLastSevenDaysController controller;

    @Before
    public void init() {
        webPage = new ReportWebPageSpy();
        controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);
    }

    @Test
    public void shouldShowNoCustomerFoundPageIfRegisteredListIsEmpty() throws Exception {
        List<Customer> emptyCustomersList = new ArrayList<Customer>();

        // EXPECTATION
        when(usersRepository.findAllCustomersWhoHaveSignedUpSince(date("20/01/2019")))
        // STUB
        .thenReturn(emptyCustomersList);

        controller.showRegisteredUsersSevenDaysBackFrom(date("27/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldShowCustomerDetailPageIfThereIsOnlyOneCustomerRegisteredSevenDaysBefore() throws Exception {
        List<Customer> expectedCustomersList = Arrays.asList(new Customer(date("10/01/2019")));

        when(usersRepository.findAllCustomersWhoHaveSignedUpSince(date("10/01/2019")))
                .thenReturn(expectedCustomersList);

        controller.showRegisteredUsersSevenDaysBackFrom(date("17/01/2019"));

        assertThat(webPage.showCustomerDetailPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldShowCustomersListIfThereAreFewCustomersRegisteredSevenDaysBefore() throws Exception {
        final List<Customer> expectedCustomersList = new ArrayList<Customer>();
        expectedCustomersList.add(new Customer(date("08/01/2019")));
        expectedCustomersList.add(new Customer(date("09/01/2019")));
        expectedCustomersList.add(new Customer(date("10/01/2019")));
        expectedCustomersList.add(new Customer(date("11/01/2019")));
        expectedCustomersList.add(new Customer(date("12/01/2019")));

        when(usersRepository.findAllCustomersWhoHaveSignedUpSince(date("06/01/2019")))
                .thenReturn(expectedCustomersList);

        controller.showRegisteredUsersSevenDaysBackFrom(date("13/01/2019"));

        assertThat(webPage.showCustomersListPageHasBeenCalled(), is(true));
    }

    // TODO: continue with other collaboration tests

    private Calendar date(String dateAsString) throws ParseException {
        return DateUtils.calendarFromString(dateAsString);
    }
}
