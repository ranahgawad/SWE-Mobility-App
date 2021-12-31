package Application.Core;
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
    public void addPublicHoliday(publicHolidays publicHolidays){
        SQLImplementation connection = SQLImplementation.getInstance();
        connection.insert(publicHolidays);
    }
}
