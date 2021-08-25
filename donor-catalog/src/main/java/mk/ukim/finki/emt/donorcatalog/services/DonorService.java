package mk.ukim.finki.emt.donorcatalog.services;

import mk.ukim.finki.emt.donorcatalog.domain.models.Donor;
import mk.ukim.finki.emt.donorcatalog.domain.models.DonorId;
import mk.ukim.finki.emt.donorcatalog.services.form.DonorForm;

import java.util.List;

public interface DonorService {

    Donor findById(DonorId id);

    Donor addDonor(DonorForm form);

    Donor donationItemCreated(DonorId donorId, int quantity);

    Donor donationItemRemoved(DonorId donorId, int quantity);

    List<Donor> findAll();

}
