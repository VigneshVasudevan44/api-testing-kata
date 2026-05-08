package com.booking.support;
import com.booking.model.BookingRequest;
import com.booking.model.Dates;
import java.time.LocalDate;

public class TestDataFactory {
    public static BookingRequest validBookingRequest(int attempt) {
        LocalDate checkin = LocalDate.now()
                .plusYears(3)
                .plusDays(attempt * 10L);

        LocalDate checkout = checkin.plusDays(2);

        return new BookingRequest(
                7,
                "Jack",
                "Sparrow",
                true,
                new Dates(checkin.toString(), checkout.toString()),
                "jack.sparrow@example.com",
                "01234567890"
        );
    }
}