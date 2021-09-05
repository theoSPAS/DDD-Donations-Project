package mk.ukim.finki.emt.donorcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.donorcatalog.domain.models.Donor;
import mk.ukim.finki.emt.donorcatalog.services.DonorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/donor")
@AllArgsConstructor
public class DonorResource {

    private final DonorService donorService;

    @GetMapping
    public List<Donor> getAll(){
        return this.donorService.findAll();
    }
}
