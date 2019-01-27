package it.eparlato.itse.server;

import it.eparlato.itse.Customer;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public interface UsersRepository {
    List<Customer> findAllCustomersWhoHaveSignedUpSince(Calendar date) throws ParseException;
}
