package aiss.peertubeminer.model.videominer;

/**
 * @author Juan C. Alonso
 */
// This is the Comment class, it is used for obtaining information of comments that will be shared to video miner
// Once you obtain the information from the API, a service will transform those models into these
public class CommentVM {

    // The three properties in the data model
    private String id;
    private String text;
    private String createdOn;

    // Constructor
    public CommentVM(String id, String text, String createdOn) {
        this.id = id;
        this.text = text;
        this.createdOn = createdOn;
    }

    // Setters and getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }


    // Way of representing the class as a string
    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
