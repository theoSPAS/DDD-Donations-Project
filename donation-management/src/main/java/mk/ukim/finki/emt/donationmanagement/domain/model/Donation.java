package mk.ukim.finki.emt.donationmanagement.domain.model;

import lombok.Getter;
import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.Donor;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import javax.validation.constraints.NotNull;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Table(name = "donations")
public class Donation extends AbstractEntity<DonationId> {

    private Instant donationDate;

    @Enumerated(EnumType.STRING)
    private DonationState donationState;

    @Column(name="donation_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DonationItem> donationItemSet = new HashSet<>();

    protected Donation() {
        super(DonationId.randomId(DonationId.class));
    }

    public Donation(Instant now, Currency currency) {
        super(DonationId.randomId(DonationId.class));
        this.donationDate = now;
        this.currency = currency;
    }

    public Money total(){
        return donationItemSet.stream().map(DonationItem::subtotal)
                .reduce(new Money(currency,0), Money::add);
    }

    public DonationItem addDonationItem(@NotNull Donor donor, int quantity){
        Objects.requireNonNull(donor, "donor must not be null");
        var donationItem = new DonationItem(donor.getId(), donor.getSum(), quantity);
        donationItemSet.add(donationItem);
        return donationItem;
    }

    public void removeDonationItem(@NotNull DonationItemId donationItemId){
        Objects.requireNonNull(donationItemId, "Donation item must not be null");
        donationItemSet.removeIf(d -> d.getId().equals(donationItemId));
    }

}
