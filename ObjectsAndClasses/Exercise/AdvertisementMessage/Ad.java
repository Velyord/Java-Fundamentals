package ObjectsAndClasses.Exercise.AdvertisementMessage;

public class Ad {
    private String phrase;
    private String event;
    private String author;
    private String city;

    public Ad (String phrase, String event, String author, String city) {
        this.phrase = phrase;
        this.event = event;
        this.author = author;
        this.city = city;
    }

    public String getPhrase() { return phrase; }
    public String getEvent()  { return event; }
    public String getAuthor() { return author; }
    public String getCity()   { return city; }

    public void setPhrase(String phrase) { this.phrase = phrase; }
    public void setEvent(String event)   { this.event = event; }
    public void setAuthor(String author) { this.author = author; }
    public void setCity(String city)     { this.city = city; }
}