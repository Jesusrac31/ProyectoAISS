package aiss.dailymotionminer.service.Dailymotion;

import aiss.dailymotionminer.model.Dailymotion.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OwnerServiceTest {
    @Autowired
    OwnerService service;
    @Test
    @DisplayName("Get owner by Id")
    void getOwner(){
        Owner owner = service.getOwner("x4zgd74");
        System.out.println(owner);
    }
}