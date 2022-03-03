package Application.Core.Ride;

import Application.Core.Storage.SQLImplementation;

public class publicHolidays {
    public String publicHolidayDate;
    public String publicHolidayName;

    public publicHolidays(String publicHolidayName,String publicHolidayDate) {
        this.publicHolidayDate = publicHolidayDate;
        this.publicHolidayName=publicHolidayName;
    }

    public String getPublicHolidayDate() {
        return publicHolidayDate;
    }

    public void setPublicHolidayDate(String publicHolidayDate) {
        this.publicHolidayDate = publicHolidayDate;
    }

    public String getPublicHolidayName() {
        return publicHolidayName;
    }

    public void setPublicHolidayName(String publicHolidayName) {
        this.publicHolidayName = publicHolidayName;
    }
    public boolean addPublicHoliday(publicHolidays publicHolidays){
        SQLImplementation connection = SQLImplementation.getInstance();
        return connection.insert(publicHolidays);
    }
}
