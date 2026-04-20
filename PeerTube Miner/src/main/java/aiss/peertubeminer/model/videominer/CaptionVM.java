package aiss.peertubeminer.model.videominer;

/**
 * @author Juan C. Alonso
 */
// This is the Caption class, it is used for obtaining information of captions that will be shared to video miner
// Once you obtain the information from the API, a service will transform those models into these
public class CaptionVM {

    // The three properties in the data model
    private String id;
    private String link;
    private String language;

    // Constructor
    public CaptionVM(String id, String link, String language) {
        this.id = id;
        this.link = link;
        this.language = language;
    }

    // Setters and getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // Way of representing the class as a string
    @Override
    public String toString() {
        return "Caption{" +
                "id='" + id + '\'' +
                ", link='" + link + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
