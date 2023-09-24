
public class Event {
private String title;
private String date;
private String time;
private String location;
private Contact thing;


public Event(String title, String date, String time, String location) {
	this.title = title;
	this.date = date;
	this.time = time;
	this.location = location;
}
public Event(Event e) {
	this.title = e.title;
	this.date = e.date;
	this.time = e.time;
	this.location = e.location;
}

public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getDate() {
	return date;
}


public void setDate(String date) {
	this.date = date;
}


public String getTime() {
	return time;
}


public void setTime(String time) {
	this.time = time;
}


public String getLocation() {
	return location;
}


public void setLocation(String location) {
	this.location = location;
}



}
