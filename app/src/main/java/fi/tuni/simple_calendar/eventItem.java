package fi.tuni.simple_calendar;

public class eventItem {
    private int eventId;
    private String eventName;
    private String eventText;
    private String date;

    public eventItem(int eventId, String eventName, String eventText, String date) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventText = eventText;
        this.date = date;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getMakerName() {
        return eventName;
    }

    public void setMakerName(String makerName) {
        this.eventName = makerName;
    }

    public String getEventText() {
        return eventText;
    }

    public void setEventText(String eventText) {
        this.eventText = eventText;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "eventItem{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventText='" + eventText + '\'' +
                ", date='" + this.date + '\'' +
                '}';
    }
}
