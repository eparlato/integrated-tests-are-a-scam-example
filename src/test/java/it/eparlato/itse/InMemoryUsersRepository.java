package it.eparlato.itse;

import javax.management.AttributeList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryUsersRepository implements UsersRepository {

    private int totalRegisteredCustomers = 0;
    private List<Customer> registeredCustomers = new ArrayList<Customer>();

    public InMemoryUsersRepository withTotalRegisteredCustomersOf(int totalRegisteredCustomers) {
        this.totalRegisteredCustomers = totalRegisteredCustomers;
        return this;
    }

    public List<Customer> findAllCustomersWhoHaveSignedUpSince(String date) {
        List<Customer> customers = new ArrayList<Customer>();


        return customers;
    }

    public void registerCustomer(Customer customer) {
        registeredCustomers.add(customer);
    }
}
