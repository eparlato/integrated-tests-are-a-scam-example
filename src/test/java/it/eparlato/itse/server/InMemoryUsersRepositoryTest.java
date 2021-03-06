package it.eparlato.itse.server;

import it.eparlato.itse.Customer;
import it.eparlato.itse.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InMemoryUsersRepositoryTest {
    InMemoryUsersRepository usersRepository;

    @Before
    public void init() {
        usersRepository = new InMemoryUsersRepository();
    }

    @Test
    public void shouldReturnNoCustomersIfRegisteredListIsEmpty() throws Exception {
        // ACTION
        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(date("20/01/2019"));
        // ASSERTION
        assertThat(customers.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnNoCustomersIfThereAreCustomersButNoneRegisteredSinceDate() throws Exception {
        usersRepository.registerCustomer(new Customer(date("09/01/2019")));
        usersRepository.registerCustomer(new Customer(date("05/01/2019")));
        usersRepository.registerCustomer(new Customer(date("04/01/2019")));

        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(date("10/01/2019"));

        assertThat(customers.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnOneCustomerIfThereIsOnlyOneCustomerRegisteredSinceDate() throws Exception {
        usersRepository.registerCustomer(new Customer(date("08/01/2019")));
        usersRepository.registerCustomer(new Customer(date("10/01/2019")));
        usersRepository.registerCustomer(new Customer(date("02/01/2019")));

        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(date("10/01/2019"));

        assertThat(customers.size(), is(1));
    }

    @Test
    public void shouldReturnAListOfFewRegisteredCustomers() throws Exception {
        usersRepository.registerCustomer(new Customer(date("05/01/2019")));
        usersRepository.registerCustomer(new Customer(date("08/01/2019")));
        usersRepository.registerCustomer(new Customer(date("09/01/2019")));
        usersRepository.registerCustomer(new Customer(date("10/01/2019")));
        usersRepository.registerCustomer(new Customer(date("11/01/2019")));
        usersRepository.registerCustomer(new Customer(date("12/01/2019")));


        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(date("06/01/2019"));

        assertThat(customers.size(), is(5));
    }

    // TODO: complete contract tests...

    private Calendar date(String dateAsString) throws ParseException {
        return DateUtils.calendarFromString(dateAsString);
    }
}