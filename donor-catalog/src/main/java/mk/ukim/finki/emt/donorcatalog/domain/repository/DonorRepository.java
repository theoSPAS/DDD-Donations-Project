package mk.ukim.finki.emt.donorcatalog.domain.repository;

import mk.ukim.finki.emt.donorcatalog.domain.models.Donor;
import mk.ukim.finki.emt.donorcatalog.domain.models.DonorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, DonorId> {
}
