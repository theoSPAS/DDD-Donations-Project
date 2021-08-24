package mk.ukim.finki.emt.donationmanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "donations")
public class Donation extends AbstractEntity<DonationId> {

    private Instant donationDate;

    @Enumerated(EnumType.STRING)
    private DonationState donationState;

    private Money total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DonationItem> donationItemSet;

    public Donation(){
    }
}
