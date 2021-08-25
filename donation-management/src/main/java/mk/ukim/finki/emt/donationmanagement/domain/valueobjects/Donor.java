package mk.ukim.finki.emt.donationmanagement.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Donor implements ValueObject {

    private final DonorId id;

    private final String name;

    private final Money sum;

    private final int count;

    private Donor(){
        this.id = DonorId.randomId(DonorId.class);
        this.name = "";
        this.sum = Money.valueOf(Currency.MKD, 0);
        this.count = 0;
    }

    @JsonCreator
    public Donor( DonorId id,
                    String name,
                  Money sum,
                   int count) {
        this.id = id;
        this.name = name;
        this.sum = sum;
        this.count = count;
    }




}
