package Application.Core.Event;

import java.util.Date;

 public interface Event {
    rideEventsTypes eventName = null;
    Date eventTime = null;

    public rideEventsTypes getEventName();
    public Date getEventTime();
    public void setEventTime(Date eventTime);

}
