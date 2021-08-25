package mk.ukim.finki.emt.donorcatalog.services.implementation;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.donorcatalog.domain.exceptions.DonorNotFoundException;
import mk.ukim.finki.emt.donorcatalog.domain.models.Donor;
import mk.ukim.finki.emt.donorcatalog.domain.models.DonorId;
import mk.ukim.finki.emt.donorcatalog.domain.repository.DonorRepository;
import mk.ukim.finki.emt.donorcatalog.services.DonorService;
import mk.ukim.finki.emt.donorcatalog.services.form.DonorForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DonorServiceImplementation implements DonorService {

    private final DonorRepository donorRepository;

    @Override
    public Donor findById(DonorId id) {
        return this.donorRepository.findById(id).orElseThrow(DonorNotFoundException::new);
    }

    @Override
    public Donor addDonor(DonorForm form) {
        Donor donor = Donor.build(form.getDonorName(),form.getSum(),form.getCount());
        this.donorRepository.save(donor);
        return donor;
    }

    @Override
    public Donor donationItemCreated(DonorId donorId, int quantity) {
        Donor donor = this.donorRepository.findById(donorId).orElseThrow(DonorNotFoundException::new);
        donor.addCount(quantity);
        this.donorRepository.saveAndFlush(donor);
        return donor;
    }

    @Override
    public Donor donationItemRemoved(DonorId donorId, int quantity) {
        Donor donor = this.donorRepository.findById(donorId).orElseThrow(DonorNotFoundException::new);
        donor.removeCount(quantity);
        this.donorRepository.saveAndFlush(donor);
        return donor;

    }

    @Override
    public List<Donor> findAll() {
       return this.donorRepository.findAll();
    }
}
