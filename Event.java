
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
        this.eventParticipant = eventParticipant;
    }
    
}