package aiss.dailymotionminer.model.videominer;

/**
 * @author Juan C. Alonso
 */
// This is the User class, it is used for obtaining information of a user which will be shared to video miner
// Once you obtain the information from the API, a service will transform those models into these
public class UserVM {
    // The four properties in the data model
    private Long id;
    private String name;
    private String user_link;
    private String picture_link;

    // Constructor
    public UserVM(Long id, String name, String user_link, String picture_link) {
        this.id = id;
        this.name = name;
        this.user_link = user_link;
        this.picture_link = picture_link;
    }

    // Setters and getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_link() {
        return user_link;
    }

    public void setUser_link(String user_link) {
        this.user_link = user_link;
    }

    public String getPicture_link() {
        return picture_link;
    }

    public void setPicture_link(String picture_link) {
        this.picture_link = picture_link;
    }

    // Way of representing the class as a string
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user_link='" + user_link + '\'' +
                ", picture_link='" + picture_link + '\'' +
                '}';
    }

}
