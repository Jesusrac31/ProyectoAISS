package aiss.videominer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author Juan C. Alonso
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @JsonProperty("name")
    @Column(name = "name")//
    @NotNull(message = "The name of the user cannot be null")//
    @NotEmpty(message = "The name of the user cannot be empty")//
    private String name;

    @JsonProperty("user_link")
    @Column(name = "user_link")//
    @NotNull(message = "The link of the user cannot be null")//
    @NotEmpty(message = "The link of the user cannot be empty")//
    private String user_link;

    @JsonProperty("picture_link")
    @Column(name = "picture_link")//
//    @NotNull(message = "The picture link of the user cannot be null")
//    @NotEmpty(message = "The picture of the user cannot be empty")
    private String picture_link;

    // Empty constructor required by Spring
    public User(){

    }

    // Default constructor for User
    public User(String name, String user_link, String picture_link) {
        this.name = name;
        this.picture_link = picture_link;
        this.user_link = user_link;
    }

    public String getId() {
        return id;
    } //

    public void setId(String id) {
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
