package mk.ukim.finki.emt.donationmanagement.domain.repository;

import mk.ukim.finki.emt.donationmanagement.domain.model.Donation;
import mk.ukim.finki.emt.donationmanagement.domain.model.DonationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, DonationId> {
}
