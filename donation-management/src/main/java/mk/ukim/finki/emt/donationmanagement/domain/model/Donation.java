package mk.ukim.finki.emt.donationmanagement.domain.model;

import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.Donor;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.lang.NonNull;


import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "donations")
public class Donation extends AbstractEntity<DonationId> {

    private Instant donationDate;

    @Enumerated(EnumType.STRING)
    private DonationState donationState;

    @Column(name="donation_currency")
    @Enumerated(value = EnumType.STRING)
    private Currency currency;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DonationItem> donationItemSet;

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

   public DonationItem addDonationItem(@NonNull Donor donor, int quantity){
       Objects.requireNonNull(donor, "donor must not be null");
       var donationItem = new DonationItem(donor.getId(), donor.getSum(), quantity);
       donationItemSet.add(donationItem);
       return donationItem;
   }

   public void removeDonationItem(@NonNull DonationItemId donationItemId){
        Objects.requireNonNull(donationItemId, "Donation item must not be null");
        donationItemSet.removeIf(d -> d.getId().equals(donationItemId));
   }

}
