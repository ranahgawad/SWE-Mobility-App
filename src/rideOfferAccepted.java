import java.util.Date;

public class rideOfferAccepted implements Event{
    rideEventsTypes eventName;
    Date eventTime;
    String passengerName;

    public rideOfferAccepted(Date eventTime, String userName){
        this.eventName = rideEventsTypes.RIDE_OFFER_ACCEPTED;
        this.eventTime = eventTime;
        this.passengerName = userName;
    }

    @Override
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public rideEventsTypes getEventName() {
        return this.eventName;
    }

    @Override
    public Date getEventTime() {
        return eventTime;
    }

    public String getPassengerName() {
        return passengerName;
    }

    @Override
    public String toString() {
        return "rideOfferAccepted{" +
                "eventName=" + eventName +
                ", eventTime=" + eventTime +
                ", passengerName='" + passengerName + '\'' +
                '}';
    }
}
