package mk.ukim.finki.emt.donationmanagement.service;

import mk.ukim.finki.emt.donationmanagement.domain.exceptions.DonationIdNotExistException;
import mk.ukim.finki.emt.donationmanagement.domain.exceptions.DonationItemIdNotExistException;
import mk.ukim.finki.emt.donationmanagement.domain.model.Donation;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationId;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationItemId;
import mk.ukim.finki.emt.donationmanagement.service.forms.DonationForm;
import mk.ukim.finki.emt.donationmanagement.service.forms.DonationItemForm;

import java.util.List;
import java.util.Optional;

public interface DonationService {

    DonationId makeADonation(DonationForm donationForm);

    List<Donation> findAll();

    Optional<Donation> findById(DonationId id);

    void addItem(DonationId donationId, DonationItemForm donationItemForm) throws DonationIdNotExistException;

    void deleteItem(DonationId donationId, DonationItemId donationItemId) throws DonationIdNotExistException, DonationItemIdNotExistException;


}
