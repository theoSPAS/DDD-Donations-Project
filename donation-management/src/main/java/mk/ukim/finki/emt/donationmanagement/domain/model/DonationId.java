package mk.ukim.finki.emt.donationmanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class DonationId extends DomainObjectId {

    private DonationId(){
        super(DonationId.randomId(DonationId.class).getId());
    }

    public DonationId(@NonNull String uuid){
        super(uuid);
    }
}
