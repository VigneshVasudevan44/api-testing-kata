package com.booking.client;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class BookingClient {

    public Response createBooking(Object bookingRequest) {
        return given()
                .body(bookingRequest)
                .when()
                .post("/booking");
    }

    public Response getBookingById(int bookingId, String token) {
        return given()
                .cookie("token", token)
                .when()
                .get("/booking/" + bookingId);
    }

    public Response login(String username, String password) {
        return given()
                .body("""
                        {
                          "username": "%s",
                          "password": "%s"
                        }
                        """.formatted(username, password))
                .when()
                .post("/auth/login");
    }
}
