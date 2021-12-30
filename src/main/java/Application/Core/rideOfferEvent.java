package Application.Core;

import java.util.Date;

public class rideOfferEvent implements Event{

    rideEventsTypes eventName;
    Date eventTime;
    String driverName;
    double price;

    rideOfferEvent(Date eventTime, String driverName,double price){
        this.eventName = rideEventsTypes.RIDE_OFFER;
        this.eventTime = eventTime;
        this.driverName = driverName;
        this.price = price;
    }


    @Override
    public rideEventsTypes getEventName() {
        return this.eventName;
    }

    @Override
    public Date getEventTime() {
        return this.eventTime;
    }

    @Override
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public String toString() {
        return "Application.Core.rideOfferEvent{" +
                "eventName=" + eventName +
                ", eventTime=" + eventTime +
                ", driverName='" + driverName + '\'' +
                ", price=" + price +
                '}';
    }
}
