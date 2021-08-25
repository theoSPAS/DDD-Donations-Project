package mk.ukim.finki.emt.donationmanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class DonationItemId extends DomainObjectId {

    private DonationItemId() {
        super(DonationItemId.randomId(DonationItemId.class).getId());
    }

    public DonationItemId(String uuid) {
        super(uuid);
    }

}
