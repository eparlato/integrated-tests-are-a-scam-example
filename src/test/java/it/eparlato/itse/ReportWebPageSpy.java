package it.eparlato.itse;


public class ReportWebPageSpy implements ReportWebPage {
    private boolean showNoCustomerFoundPageHasBeenCalled = false;
    private boolean showCustomerDetailPageHasBeenCalled = false;

    public boolean showNoCustomerFoundPageHasBeenCalled() {
        return showNoCustomerFoundPageHasBeenCalled;
    }

    public void showNoCustomerFoundPage() {
        showNoCustomerFoundPageHasBeenCalled = true;
    }

    public void showCustomerDetailPage(Customer customer) {
        this.showCustomerDetailPageHasBeenCalled = true;
    }

    public boolean showCustomerDetailPageHasBeenCalled() {
        return showCustomerDetailPageHasBeenCalled;
    }
}
