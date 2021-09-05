package mk.ukim.finki.emt.sharedkernel.domain.events.donations;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class DonationItemRemoved extends DomainEvent {

    private String donorId;

    private int quantity;

    public DonationItemRemoved(String topic) {
        super(TopicHolder.TOPIC_DONATION_ITEM_REMOVED);
    }

    public DonationItemRemoved(String topic, String donorId, int quantity) {
        super(TopicHolder.TOPIC_DONATION_ITEM_REMOVED);
        this.donorId = donorId;
        this.quantity = quantity;
    }
}
