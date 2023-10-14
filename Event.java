import java.lang.Comparable;

public class Event implements Comparable<Event> {
    // Event = 1 to 1 "events" which justify deleting an entire event when one (1)
    // contact is removed from event
    private String title, date, location, time, contactName;

    // Constructor
    public Event(String title, String date, String location, String time, String contactName) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.time = time;
        this.contactName = contactName;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getContactName() {
        return contactName;
    }

    // ToString method for printing events
    public String toString() {
        String result = "Title: " + this.title + "\n";
        result += "Date: " + this.date + "\n";
        result += "Time: " + this.time + "\n";
        result += "Location: " + this.location + "\n";
        result += "Contact Name: " + this.contactName + "\n";
        return result;
    }

    // CompareTo method for sorting events
    public int compareTo(Event otherEvent) {
        return this.title.compareTo(otherEvent.title);
    }
}
