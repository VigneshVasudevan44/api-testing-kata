package com.booking.model;

public class Dates {
    private String checkin;
    private String checkout;
    public Dates() {
    }

    public Dates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }
}