package mk.ukim.finki.emt.donorcatalog.domain.models;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class DonorId extends DomainObjectId {

    private DonorId() {
        super(DonorId.randomId(DonorId.class).getId());
    }

    public DonorId(@NonNull String uuid) {
        super(uuid);
    }

    public static DonorId of(String donorId) {
        return DonorId.randomId(DonorId.class);
    }
}
