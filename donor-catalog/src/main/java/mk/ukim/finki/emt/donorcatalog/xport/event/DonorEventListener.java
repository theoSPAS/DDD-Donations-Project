package mk.ukim.finki.emt.donorcatalog.xport.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.donorcatalog.domain.models.DonorId;
import mk.ukim.finki.emt.donorcatalog.services.DonorService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.donations.DonationItemCreated;
import mk.ukim.finki.emt.sharedkernel.domain.events.donations.DonationItemRemoved;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DonorEventListener {
    private final DonorService donorService;

    @KafkaListener(topics = TopicHolder.TOPIC_DONATION_ITEM_CREATED, groupId = "donorCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            DonationItemCreated event = DomainEvent.fromJson(jsonMessage,
                    DonationItemCreated.class);
            this.donorService.donationItemCreated(DonorId.of(event.getDonorId()), event.getQuantity());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_DONATION_ITEM_REMOVED, groupId = "donorCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            DonationItemRemoved event = DomainEvent.fromJson(jsonMessage,
                    DonationItemRemoved.class);
            this.donorService.donationItemRemoved(DonorId.of(event.getDonorId()), event.getQuantity());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
