package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.videominer.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();

    public UserRepository(){
        // Mock data for testing
        users.add( // Mock data have 3 users: User1, User2 and User 3
                new User( // The creation of a user is composed by an ID, some name, user_link and picture_link
                        1L,
                        "Test1",
                        "http://UserLink1.com",
                        "http://PictureLink1.com"
                )
        );
        // Repeat for each user
        users.add(
                new User(
                        2L,
                        "Test2",
                        "http://UserLink2.com",
                        "http://PictureLink2.com"
                )
        );
        users.add(
                new User(
                        3L,
                        "Test3",
                        "http://UserLink3.com",
                        "http://PictureLink3.com"
                )
        );
    }

    // Find all operation, you will be able to obtain all users stored
    // For now, this operation returns the info in list users
    public List<User> findAll() { return users; }

    // find one user with some specific id
    public User findOneById(Long id) {
        return users.stream() // Use streams for obtaining one user
                .filter(user -> user.getId().equals(id)) // Filter to all users with some specific id
                .findFirst() // Find the first user with such id
                .orElse(null); // If this value is not found, return null, otherwise, return the user
    }
}
