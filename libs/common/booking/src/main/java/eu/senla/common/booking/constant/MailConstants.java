package eu.senla.common.booking.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MailConstants {
    public static final String BOOKING_REQUEST_CREATED_AWAIT_APROVE_MAIL_MESSAGE = "Your booking request created and request for approve was send to your team leader.";
    public static final String BOOKED_SUCCESSFULLY_MAIL_MESSAGE = "You have successfully booked";
    public static final String PLAHCTOH_BOOKING_MAIL_SUBJECT = "PLAHCTOH-BOOKING";
    public static final String BOOKING_REQUEST_WAS_REJECTED_MAIL_MESSAGE_TEMPLATE = "Hi, your booking request on room %s was %s.";
    public static final String BOOKING_APPROVE_REQUEST_MAIL_SUBJECT = "Booking Approve Request";
    public static final String BOOKING_REQUEST_MAIL_MESSAGE_TEMPATE = """
                %s %s, wants to book a meeting room № %s <br>
                Date: %s <br>
                Please choose one of the following options: <br>
                <a href='%s' style='display: inline-block; padding: 12px 24px; color: white; background-color: #a8d5ba; text-decoration: none; border-radius: 5px; margin-right: 10px; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);'>Approve</a>
                <a href='%s' style='display: inline-block; padding: 12px 24px; color: white; background-color: #f7a8a8; text-decoration: none; border-radius: 5px; box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);'>Decline</a>
            """;
}
