package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OwnerService {
    @Autowired
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.dailymotion.com/";

    // Method to GET user from the URI
    public Owner getOwner(String id){
        String uri = BASE_URL + "user/" + id + "?fields=id,screenname,url,avatar_720_url"; // Build uri to retrieve all info of an user
        return restTemplate.getForObject(uri, Owner.class);
    }
}
