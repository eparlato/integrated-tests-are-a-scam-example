package it.eparlato.itse;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class InMemoryUsersRepositoryTest {
    UsersRepository usersRepository;

    @Before
    public void init() {
        usersRepository = new InMemoryUsersRepository();
    }

    @Test
    public void shouldReturnNoCustomersIfRegisteredListIsEmpty() throws Exception {
        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(DateUtils.calendarFromString("20/01/2019"));

        assertThat(customers.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnNoCustomersIfThereAreCustomersButNoneRegisteredSinceDate() throws Exception {
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("09/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("05/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("04/01/2019")));

        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(DateUtils.calendarFromString("10/01/2019"));

        assertThat(customers.isEmpty(), is(true));
    }

    @Test
    public void shouldReturnOneCustomerIfThereIsOnlyOneCustomerRegisteredSinceDate() throws Exception {
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("08/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("10/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("02/01/2019")));

        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(DateUtils.calendarFromString("10/01/2019"));

        assertThat(customers.size(), is(1));
    }

    @Test
    public void shouldReturnAListOfFewRegisteredCustomers() throws Exception {
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("05/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("08/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("09/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("10/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("11/01/2019")));
        usersRepository.registerCustomer(new Customer(DateUtils.calendarFromString("12/01/2019")));


        List<Customer> customers = usersRepository.findAllCustomersWhoHaveSignedUpSince(DateUtils.calendarFromString("06/01/2019"));

        assertThat(customers.size(), is(5));
    }

    // TODO: complete contract tests...
}