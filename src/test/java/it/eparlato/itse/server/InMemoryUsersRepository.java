package it.eparlato.itse.server;

import it.eparlato.itse.Customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class InMemoryUsersRepository implements UsersRepository {

    private List<Customer> registeredCustomers = new ArrayList<Customer>();


    public List<Customer> findAllCustomersWhoHaveSignedUpSince(Calendar date) {
        List<Customer> customers = new ArrayList<Customer>();

        for (Customer customer : registeredCustomers) {
            if (customer.hasRegisteredDuringOrAfter(date)) {
                customers.add(customer);
            }
        }

        return customers;
    }

    public void registerCustomer(Customer customer) {
        registeredCustomers.add(customer);
    }
}
