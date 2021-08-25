package mk.ukim.finki.emt.donationmanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.Donor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DonationItemForm {

    @NotNull
    private Donor donor;

    @Min(1)
    private int quantity = 1;
}
