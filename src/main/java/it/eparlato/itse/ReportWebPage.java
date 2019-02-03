package it.eparlato.itse;

import java.util.List;

public interface ReportWebPage {
    void showNoCustomerFoundPage();

    void showCustomerDetailPage(Customer customer);

    void showCustomersListPage(List<Customer> totalRegisteredCustomers);
}
