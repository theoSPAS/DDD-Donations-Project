package mk.ukim.finki.emt.donorcatalog.domain.models;

import mk.ukim.finki.emt.donorcatalog.domain.valueobjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="donor")
public class Donor extends AbstractEntity<DonorId> {

    private String donorName;

    private Quantity quantity;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="sum_amount")),
            @AttributeOverride(name="currency", column = @Column(name="sum_currency"))
    })
    private Money sum;

}
