package it.eparlato.itse;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class Customer {
    private final Calendar registrationDate;

    public Customer(Calendar registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean hasRegisteredDuringOrAfter(Calendar startDate) {

        if (registrationDate.compareTo(startDate) >= 0) {
            return true;
        }

        return false;
    }

}
