package it.eparlato.itse;

import java.util.List;

public interface UsersRepository {
    List<Customer> findAllCustomersWhoHaveSignedUpSince(String date);
}
