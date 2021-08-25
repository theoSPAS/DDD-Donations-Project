package mk.ukim.finki.emt.donationmanagement.service.implementation;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.donationmanagement.domain.exceptions.DonationIdNotExistException;
import mk.ukim.finki.emt.donationmanagement.domain.exceptions.DonationItemIdNotExistException;
import mk.ukim.finki.emt.donationmanagement.domain.model.Donation;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationId;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationItemId;
import mk.ukim.finki.emt.donationmanagement.domain.repository.DonationRepository;
import mk.ukim.finki.emt.donationmanagement.service.DonationService;
import mk.ukim.finki.emt.donationmanagement.service.forms.DonationForm;
import mk.ukim.finki.emt.donationmanagement.service.forms.DonationItemForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Validator;
import javax.validation.ConstraintViolationException;

@Service
@Transactional
@AllArgsConstructor
public class DonationServiceImplementation implements DonationService {

   private final DonationRepository donationRepository;
    private final Validator validator;

    @Override
    public DonationId makeADonation(DonationForm donationForm) {
        Objects.requireNonNull(donationForm, "donation must not be null");
        var constraintViolations = validator.validate(donationForm);
        if(constraintViolations.size() > 0){
            throw new ConstraintViolationException("The donation form is not valid", constraintViolations);
        }

        var newDonation = donationRepository.saveAndFlush(toDomainObject(donationForm));
        return newDonation.getId();

    }

    private Donation toDomainObject(DonationForm donationForm){
        var donation = new Donation(Instant.now(), donationForm.getCurrency());
        donationForm.getItems().forEach(donationItem -> donation.addDonationItem(donationItem.getDonor(), donationItem.getQuantity()));
        return donation;
    }

    @Override
    public List<Donation> findAll() {
        return this.donationRepository.findAll();
    }

    @Override
    public Optional<Donation> findById(DonationId id) {
        return this.donationRepository.findById(id);
    }

    @Override
    public void addItem(DonationId donationId, DonationItemForm donationItemForm) throws DonationIdNotExistException {
        Donation donation = this.donationRepository.findById(donationId).orElseThrow(DonationIdNotExistException::new);
        donation.addDonationItem(donationItemForm.getDonor(), donationItemForm.getQuantity());

        donationRepository.saveAndFlush(donation);
    }

    @Override
    public void deleteItem(DonationId donationId, DonationItemId donationItemId) throws DonationIdNotExistException, DonationItemIdNotExistException {
         Donation donation = this.donationRepository.findById(donationId).orElseThrow(DonationItemIdNotExistException::new);
         donation.removeDonationItem(donationItemId);

         donationRepository.saveAndFlush(donation);
    }
}
