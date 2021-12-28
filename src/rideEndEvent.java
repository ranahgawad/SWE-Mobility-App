import java.util.Date;

public class rideEndEvent implements Event{
    rideEventsTypes eventName;
    Date eventTime;
    String driverName;
    String passengerName;

    rideEndEvent(Date eventTime, String driverName, String userName){
        this.eventName = rideEventsTypes.RIDE_END;
        this.eventTime = eventTime;
        this.driverName = driverName;
        this.passengerName = userName;
    }

    @Override
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    @Override
    public Date getEventTime() {
        return eventTime;
    }

    @Override
    public rideEventsTypes getEventName() {
        return eventName;
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public String toString() {
        return "rideEndEvent{" +
                "eventName=" + eventName +
                ", eventTime=" + eventTime +
                ", driverName='" + driverName + '\'' +
                ", passengerName='" + passengerName + '\'' +
                '}';
    }
}
