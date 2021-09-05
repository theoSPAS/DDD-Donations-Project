package mk.ukim.finki.emt.donationmanagement.service;

import mk.ukim.finki.emt.donationmanagement.domain.exceptions.DonationIdNotExistException;
import mk.ukim.finki.emt.donationmanagement.domain.model.Donation;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationId;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationItem;
import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.Donor;
import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.DonorId;
import mk.ukim.finki.emt.donationmanagement.service.forms.DonationForm;
import mk.ukim.finki.emt.donationmanagement.service.forms.DonationItemForm;
import mk.ukim.finki.emt.donationmanagement.xport.client.DonorClient;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class DonationServiceImplementationTests {

    @Autowired
    private DonationService donationService;

    @Autowired
    private DonorClient donorClient;

    private static Donor newDonor(String name, Money sum){
        Donor donor = new Donor(DonorId.randomId(DonorId.class),name,sum,0);
        return donor;
    }

    @Test
    public void testMakeADonation() {
        DonationItemForm donationItemForm = new DonationItemForm();
        donationItemForm.setDonor(newDonor("World Health Organization",Money.valueOf(Currency.MKD,1000)));
        donationItemForm.setQuantity(1);

        DonationItemForm donationItemForm_1 = new DonationItemForm();
        donationItemForm_1.setDonor(newDonor("Healthy Children",Money.valueOf(Currency.MKD,4500)));
        donationItemForm_1.setQuantity(2);

        DonationForm donationForm = new DonationForm();
        donationForm.setCurrency(Currency.MKD);
        donationForm.setItems(Arrays.asList(donationItemForm,donationItemForm_1));

        DonationId donationId = this.donationService.makeADonation(donationForm);

        Donation donation = this.donationService.findById(donationId).orElseThrow(DonationIdNotExistException::new);

        Assertions.assertEquals(donation.total(),Money.valueOf(Currency.MKD,10000));
    }

    @Test
    public void testMakeDonationWithRealData() {
        List<Donor> donorList = this.donorClient.findAll();
        System.out.println(donorList);
        Donor donor1 = donorList.get(0);
        Donor donor2 = donorList.get(1);

        DonationItemForm donationItemForm = new DonationItemForm();
        donationItemForm.setDonor(donor1);
        donationItemForm.setQuantity(1);

        DonationItemForm donationItemForm_1 = new DonationItemForm();
        donationItemForm_1.setDonor(donor2);
        donationItemForm_1.setQuantity(2);

        DonationForm donationForm = new DonationForm();
        donationForm.setCurrency(Currency.MKD);
        donationForm.setItems(Arrays.asList(donationItemForm,donationItemForm_1));

        DonationId newDonationId = this.donationService.makeADonation(donationForm);
        Donation donation = this.donationService.findById(newDonationId).orElseThrow(DonationIdNotExistException::new);

        Money outMoney = donor1.getSum().multiply(donationItemForm.getQuantity()).add(donor2.getSum().multiply(donationItemForm_1.getQuantity()));
        Assertions.assertEquals(donation.total(),outMoney);
    }

}
