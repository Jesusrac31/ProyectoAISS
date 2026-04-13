package aiss.dailymotionminer.repository;

import aiss.dailymotionminer.model.videominer.Caption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CaptionRepository {
    List<Caption> captions = new ArrayList<>();

    public CaptionRepository() {
        // Mock data for testing
        captions.add(
                new Caption(
                        "ForTests",
                        "English",
                        "en"
                )
        );
        captions.add(
                new Caption(
                        UUID.randomUUID().toString(),
                        "English",
                        "en"
                )
        );
        captions.add(
                new Caption(
                        UUID.randomUUID().toString(),
                        "Spanish",
                        "es"
                )
        );
        captions.add(
                new Caption(
                        UUID.randomUUID().toString(),
                        "French",
                        "fr"
                )
        );
    }

    // Find all operation, you will be able to obtain all captions stored
    // For now, this operation returns the info in list captions
    public List<Caption> findAll() {
        return captions;
    }

    // Find one caption with some specific id
    public Caption findOneById(String id) {
        return captions.stream()
                .filter(caption -> caption.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some caption which is passed as a parameter
    public Caption create(Caption caption) {
        Caption newCaption = new Caption(
                UUID.randomUUID().toString(),
                caption.getName(),
                caption.getLanguage()
        );
        captions.add(newCaption);
        return newCaption; // Returns the created caption
    }

    // Updates some caption with some id
    public void update(Caption updatedCaption, String id) {
        Caption existingCaption = findOneById(id);
        int i = captions.indexOf(existingCaption);
        updatedCaption.setId(existingCaption.getId());
        captions.set(i, updatedCaption);
    }

    // Remove some caption with some id
    public void delete(String id) {
        captions.removeIf(caption -> caption.getId().equals(id));
    }
}
