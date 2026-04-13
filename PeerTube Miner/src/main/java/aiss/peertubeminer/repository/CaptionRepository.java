package aiss.peertubeminer.repository;

import aiss.peertubeminer.model.videominer.CaptionVM;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CaptionRepository {
    List<CaptionVM> captions = new ArrayList<>();

    public CaptionRepository() {
        // Mock data for testing
        captions.add(
                new CaptionVM(
                        "ForTests",
                        "English",
                        "en"
                )
        );
        captions.add(
                new CaptionVM(
                        UUID.randomUUID().toString(),
                        "English",
                        "en"
                )
        );
        captions.add(
                new CaptionVM(
                        UUID.randomUUID().toString(),
                        "Spanish",
                        "es"
                )
        );
        captions.add(
                new CaptionVM(
                        UUID.randomUUID().toString(),
                        "French",
                        "fr"
                )
        );
    }

    // Find all operation, you will be able to obtain all captions stored
    // For now, this operation returns the info in list captions
    public List<CaptionVM> findAll() {
        return captions;
    }

    // Find one caption with some specific id
    public CaptionVM findOneById(String id) {
        return captions.stream()
                .filter(caption -> caption.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some caption which is passed as a parameter
    public CaptionVM create(CaptionVM caption) {
        CaptionVM newCaption = new CaptionVM(
                UUID.randomUUID().toString(),
                caption.getName(),
                caption.getLanguage()
        );
        captions.add(newCaption);
        return newCaption; // Returns the created caption
    }

    // Updates some caption with some id
    public void update(CaptionVM updatedCaption, String id) {
        CaptionVM existingCaption = findOneById(id);
        int i = captions.indexOf(existingCaption);
        updatedCaption.setId(existingCaption.getId());
        captions.set(i, updatedCaption);
    }

    // Remove some caption with some id
    public void delete(String id) {
        captions.removeIf(caption -> caption.getId().equals(id));
    }
}
