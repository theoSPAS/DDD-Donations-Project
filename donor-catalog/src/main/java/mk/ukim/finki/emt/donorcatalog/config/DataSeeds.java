package mk.ukim.finki.emt.donorcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.donorcatalog.domain.models.Donor;
import mk.ukim.finki.emt.donorcatalog.domain.repository.DonorRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataSeeds {

    private final DonorRepository donorRepository;

    @PostConstruct
    public void init(){
        Donor donor_1 = Donor.build("Save humanity", Money.valueOf(Currency.MKD, 40000),7);

        Donor donor_2 = Donor.build("WSS", Money.valueOf(Currency.MKD, 10000),2);

        if(this.donorRepository.findAll().isEmpty()){
            this.donorRepository.saveAll(Arrays.asList(donor_1,donor_2));
        }
    }
}
