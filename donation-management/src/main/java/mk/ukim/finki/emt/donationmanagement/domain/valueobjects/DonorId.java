package mk.ukim.finki.emt.donationmanagement.domain.valueobjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class DonorId extends DomainObjectId {

    protected DonorId() {
        super(DonorId.randomId(DonorId.class).getId());
    }

    public DonorId(String uuid) {
        super(uuid);
    }

}
