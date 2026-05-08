package com.booking.steps;

import com.booking.client.BookingClient;
import com.booking.model.BookingRequest;
import com.booking.support.ScenarioContext;
import com.booking.support.TestDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BookingSteps {

    private final BookingClient bookingClient = new BookingClient();
    private final ScenarioContext context = new ScenarioContext();
    private BookingRequest bookingRequest;

    @When("I create a valid booking")
    @Given("I have created a valid booking")
    public void iCreateAValidBooking() {
        createBookingSuccessfully();
    }

    @Given("I am authenticated as an admin user")
    public void iAmAuthenticatedAsAnAdminUser() {
        var response = bookingClient.login("admin", "password");

        assertThat(response.statusCode(), is(200));

        context.setToken(response.jsonPath().getString("token"));
        assertThat(context.getToken(), notNullValue());
    }

    @When("I retrieve the booking by id")
    public void iRetrieveTheBookingById() {
        context.setResponse(bookingClient.getBookingById(context.getBookingId(), context.getToken()));
    }

    @Then("the booking response status code should be {int}")
    public void theBookingResponseStatusCodeShouldBe(int statusCode) {
        assertThat(context.getResponse().statusCode(), is(statusCode));
    }

    @Then("the booking response should contain a booking id")
    public void theBookingResponseShouldContainABookingId() {
        assertThat(context.getBookingId(), greaterThan(0));
    }

    @Then("the booking response should contain the created booking details")
    public void theBookingResponseShouldContainTheCreatedBookingDetails() {
        assertThat(context.getResponse().jsonPath().getString("firstname"), is(bookingRequest.getFirstname()));
        assertThat(context.getResponse().jsonPath().getString("lastname"), is(bookingRequest.getLastname()));
        assertThat(context.getResponse().jsonPath().getInt("roomid"), is(bookingRequest.getRoomid()));
        assertThat(context.getResponse().jsonPath().getBoolean("depositpaid"), is(bookingRequest.isDepositpaid()));
        assertThat(context.getResponse().jsonPath().getString("bookingdates.checkin"), is(bookingRequest.getBookingdates().getCheckin()));
        assertThat(context.getResponse().jsonPath().getString("bookingdates.checkout"), is(bookingRequest.getBookingdates().getCheckout()));
    }

    @Then("the retrieved booking should match the created booking")
    public void theRetrievedBookingShouldMatchTheCreatedBooking() {
        assertThat(context.getResponse().jsonPath().getString("firstname"), is(bookingRequest.getFirstname()));
        assertThat(context.getResponse().jsonPath().getString("lastname"), is(bookingRequest.getLastname()));
        assertThat(context.getResponse().jsonPath().getInt("roomid"), is(bookingRequest.getRoomid()));
        assertThat(context.getResponse().jsonPath().getBoolean("depositpaid"), is(bookingRequest.isDepositpaid()));
        assertThat(context.getResponse().jsonPath().getString("bookingdates.checkin"), is(bookingRequest.getBookingdates().getCheckin()));
        assertThat(context.getResponse().jsonPath().getString("bookingdates.checkout"), is(bookingRequest.getBookingdates().getCheckout()));
    }

    private void createBookingSuccessfully() {
        for (int attempt = 1; attempt <= 10; attempt++) {
            bookingRequest = TestDataFactory.validBookingRequest(attempt);
            context.setResponse(bookingClient.createBooking(bookingRequest));

            if (context.getResponse().statusCode() == 201) {
                context.setBookingId(context.getResponse().jsonPath().getInt("bookingid"));
                return;
            }
        }

        throw new AssertionError("Unable to create booking after multiple attempts. Last status code: "
                + context.getResponse().statusCode());
    }
}