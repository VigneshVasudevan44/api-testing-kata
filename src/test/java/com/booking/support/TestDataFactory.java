package com.booking.support;
import com.booking.model.BookingRequest;
import com.booking.model.Dates;

public class TestDataFactory {

    public static BookingRequest validBookingRequest() {
        return new BookingRequest(
                1,
                "Jim",
                "Brown",
                true,
                new Dates(
                        "2026-06-01",
                        "2026-06-05"
                ),
                "jim.brown@example.com",
                "01234567890"
        );
    }
}