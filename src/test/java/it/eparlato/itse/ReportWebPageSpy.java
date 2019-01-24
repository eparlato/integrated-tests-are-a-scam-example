package it.eparlato.itse;


import java.util.List;

public class ReportWebPageSpy implements ReportWebPage {
    private boolean showNoCustomerFoundPageHasBeenCalled = false;
    private boolean showCustomerDetailPageHasBeenCalled = false;
    private boolean showCustomersListHasBeenCalled = false;

    public boolean showNoCustomerFoundPageHasBeenCalled() {
        return showNoCustomerFoundPageHasBeenCalled;
    }

    public void showNoCustomerFoundPage() {
        showNoCustomerFoundPageHasBeenCalled = true;
    }

    public void showCustomerDetailPage(Customer customer) {
        this.showCustomerDetailPageHasBeenCalled = true;
    }

    public void showCustomersList(List<Customer> totalRegisteredCustomers) {
        showCustomersListHasBeenCalled = true;
    }

    public boolean showCustomerDetailPageHasBeenCalled() {
        return showCustomerDetailPageHasBeenCalled;
    }

    public boolean showCustomersListHasBeenCalled() {
        return showCustomersListHasBeenCalled;
    }
}
