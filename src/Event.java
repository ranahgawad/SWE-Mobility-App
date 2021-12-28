import java.util.Date;

 interface Event {
    rideEventsTypes eventName = null;
    Date eventTime = null;

    public rideEventsTypes getEventName();
    public Date getEventTime();
    public void setEventTime(Date eventTime);

}
