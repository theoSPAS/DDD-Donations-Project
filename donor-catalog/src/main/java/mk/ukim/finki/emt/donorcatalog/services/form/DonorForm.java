package mk.ukim.finki.emt.donorcatalog.services.form;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class DonorForm {

    private String donorName;

    private Money sum;

    private int count;
}
