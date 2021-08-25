package mk.ukim.finki.emt.donorcatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="donor")
public class Donor extends AbstractEntity<DonorId> {

    private String donorName;

    private int count = 0;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="sum_amount")),
            @AttributeOverride(name="currency", column = @Column(name="sum_currency"))
    })
    private Money sum;

    protected Donor() {
        super(DonorId.randomId(DonorId.class));
    }

    public static Donor build(String donorName, Money sum, int count) {
        Donor donor = new Donor();
        donor.donorName = donorName;
        donor.sum = sum;
        donor.count = count;
        return donor;
    }

    public void addCount(int quantity) {
        this.count = this.count - quantity;
    }

    public void removeCount(int quantity) {
        this.count -= quantity;
    }


}
