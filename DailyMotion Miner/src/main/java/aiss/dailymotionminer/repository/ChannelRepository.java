package aiss.dailymotionminer.repository;

import aiss.dailymotionminer.model.videominer.ChannelVM;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ChannelRepository {
    List<ChannelVM> channels = new ArrayList<>();

    public ChannelRepository() {
        // Mock data for testing
        channels.add(
                new ChannelVM(
                        "ForTests",
                        "Tech Reviews",
                        "Technology channel",
                        "2026-01-01T10:00:00Z"
                )
        );
        channels.add(
                new ChannelVM(
                        UUID.randomUUID().toString(),
                        "Tech Reviews",
                        "Technology channel",
                        "2026-01-01T10:00:00Z"
                )
        );
        channels.add(
                new ChannelVM(
                        UUID.randomUUID().toString(),
                        "Daily News",
                        "News channel",
                        "2026-01-02T10:00:00Z"
                )
        );
        channels.add(
                new ChannelVM(
                        UUID.randomUUID().toString(),
                        "Music Lab",
                        "Music channel",
                        "2026-01-03T10:00:00Z"
                )
        );
    }

    // Find all operation, you will be able to obtain all channels stored
    // For now, this operation returns the info in list channels
    public List<ChannelVM> findAll() {
        return channels;
    }

    // Find one channel with some specific id
    public ChannelVM findOneById(String id) {
        return channels.stream()
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Create some channel which is passed as a parameter
    public ChannelVM create(ChannelVM channel) {
        ChannelVM newChannel = new ChannelVM(
                UUID.randomUUID().toString(),
                channel.getName(),
                channel.getDescription(),
                channel.getCreatedTime()
        );
        newChannel.setVideos(channel.getVideos());
        channels.add(newChannel);
        return newChannel; // Returns the created channel
    }

    // Updates some channel with some id
    public void update(ChannelVM updatedChannel, String id) {
        ChannelVM existingChannel = findOneById(id);
        int i = channels.indexOf(existingChannel);
        updatedChannel.setId(existingChannel.getId());
        channels.set(i, updatedChannel);
    }

    // Remove some channel with some id
    public void delete(String id) {
        channels.removeIf(channel -> channel.getId().equals(id));
    }
}
