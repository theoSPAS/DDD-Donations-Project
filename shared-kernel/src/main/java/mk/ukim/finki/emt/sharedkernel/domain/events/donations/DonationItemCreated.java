package mk.ukim.finki.emt.sharedkernel.domain.events.donations;

import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;

@Getter
public class DonationItemCreated extends DomainEvent {

    private String donorId;

    private int quantity;

    public DonationItemCreated(String topic){
        super(TopicHolder.TOPIC_DONATION_ITEM_CREATED);
    }

    public DonationItemCreated(String donorId, int quantity){
        super(TopicHolder.TOPIC_DONATION_ITEM_CREATED);
        this.donorId = donorId;
        this.quantity = quantity;
    }
}
