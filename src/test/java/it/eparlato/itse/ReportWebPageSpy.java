package it.eparlato.itse;


public class ReportWebPageSpy implements ReportWebPage {
    boolean showNoCustomerFoundPageHasBeenCalled = false;

    public boolean showNoCustomerFoundPageHasBeenCalled() {
        return showNoCustomerFoundPageHasBeenCalled;
    }

    public void showNoCustomerFoundPage() {
        showNoCustomerFoundPageHasBeenCalled = true;
    }
}
