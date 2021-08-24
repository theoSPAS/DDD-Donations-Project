package mk.ukim.finki.emt.donationmanagement.domain.model;

import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.DonorId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="donation_item")
public class DonationItem extends AbstractEntity<DonationItemId> {

    private Money itemPrice;

    @Column(nullable = false)
    private int quantity;

    @AttributeOverride(name="id", column = @Column(name="donor_id", nullable = false))
    private DonorId donorId;
}
