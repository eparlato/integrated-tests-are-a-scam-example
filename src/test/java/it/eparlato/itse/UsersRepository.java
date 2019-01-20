package it.eparlato.itse;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public interface UsersRepository {
    List<Customer> findAllCustomersWhoHaveSignedUpSince(Calendar date) throws ParseException;

    void registerCustomer(Customer customer);

}
