
public class Event {
    // Event = 1 to 1 "events" which justify deleting an entire event when one (1)
    // contact is removed from event
    String title, date, location, time;
    Contact eventParticipant;

    public Event(String title, String date, String location, String time, Contact eventParticipant) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.time = time;
        this.eventParticipant=eventParticipant;
    }

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

    public Contact getEventParticipant() {
        return eventParticipant;
    }
    public String toString() {
        String result = "Title: " + this.title + "\n";
        result += "Date: " + this.date + "\n";
        result += "Time: " + this.time + "\n";
        result += "Location: " + this.location + "\n";
        result += "Contact Name: " + this.eventParticipant.getName() + "\n";
        return result;
    }
}

