package it.eparlato.itse.collaboration;

import it.eparlato.itse.*;
import it.eparlato.itse.server.UsersRepository;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CollaborationUsersRegisteredInLastSevenDaysControllerTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    ReportWebPageSpy webPage;
    UsersRepository usersRepository = context.mock(UsersRepository.class);
    UsersRegisteredInLastSevenDaysController controller;

    @Before
    public void init() {
        webPage = new ReportWebPageSpy();
        controller = new UsersRegisteredInLastSevenDaysController(usersRepository, webPage);
    }

    @Test
    public void shouldShowNoCustomerFoundPageIfRegisteredListIsEmpty() throws Exception {

        context.checking(new Expectations(){{
            // Expectation
            oneOf(usersRepository).findAllCustomersWhoHaveSignedUpSince(date("20/01/2019"));
            // Stub
            will(returnValue(new ArrayList<Customer>()));
        }});

        controller.showRegisteredUserSevenDaysBackFrom(date("27/01/2019"));

        assertThat(webPage.showNoCustomerFoundPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldShowCustomerDetailPageIfThereIsOnlyOneCustomerRegisteredSevenDaysBefore() throws Exception {
        final List<Customer> expectedCustomersList = Arrays.asList(newCustomer("10/01/2019"));

        context.checking(new Expectations(){{
            oneOf(usersRepository).findAllCustomersWhoHaveSignedUpSince(date("10/01/2019"));
            will(returnValue(expectedCustomersList));
        }});

        controller.showRegisteredUserSevenDaysBackFrom(date("17/01/2019"));

        assertThat(webPage.showCustomerDetailPageHasBeenCalled(), is(true));
    }

    @Test
    public void shouldShowCustomersListIfThereAreFewCustomersRegisteredSevenDaysBefore() throws Exception {
        final List<Customer> expectedCustomersList = new ArrayList<Customer>();
        expectedCustomersList.add(newCustomer("08/01/2019"));
        expectedCustomersList.add(newCustomer("09/01/2019"));
        expectedCustomersList.add(newCustomer("10/01/2019"));
        expectedCustomersList.add(newCustomer("11/01/2019"));
        expectedCustomersList.add(newCustomer("12/01/2019"));


        context.checking(new Expectations(){{
            oneOf(usersRepository).findAllCustomersWhoHaveSignedUpSince(date("06/01/2019"));
            will(returnValue(expectedCustomersList));
        }});


        controller.showRegisteredUserSevenDaysBackFrom(date("13/01/2019"));

        assertThat(webPage.showCustomersListHasBeenCalled(), is(true));
    }

    // TODO: continue with other collaboration tests

    private Customer newCustomer(String registrationDate) throws Exception {
        return new Customer (DateUtils.calendarFromString(registrationDate));
    }

    private Calendar date(String dateAsString) throws ParseException {
        return DateUtils.calendarFromString(dateAsString);
    }
}
