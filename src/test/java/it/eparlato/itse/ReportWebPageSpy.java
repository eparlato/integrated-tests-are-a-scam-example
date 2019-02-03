package it.eparlato.itse;


import java.util.List;

public class ReportWebPageSpy implements ReportWebPage {
    private boolean showNoCustomerFoundPageHasBeenCalled = false;
    private boolean showCustomerDetailPageHasBeenCalled = false;
    private boolean showCustomersListPageHasBeenCalled = false;

    public boolean showNoCustomerFoundPageHasBeenCalled() {
        return showNoCustomerFoundPageHasBeenCalled;
    }

    public void showNoCustomerFoundPage() {
        showNoCustomerFoundPageHasBeenCalled = true;
    }

    public void showCustomerDetailPage(Customer customer) {
        this.showCustomerDetailPageHasBeenCalled = true;
    }

    public void showCustomersListPage(List<Customer> totalRegisteredCustomers) {
        showCustomersListPageHasBeenCalled = true;
    }

    public boolean showCustomerDetailPageHasBeenCalled() {
        return showCustomerDetailPageHasBeenCalled;
    }

    public boolean showCustomersListPageHasBeenCalled() {
        return showCustomersListPageHasBeenCalled;
    }
}
