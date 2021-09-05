package mk.ukim.finki.emt.donationmanagement.xport.client;


import mk.ukim.finki.emt.donationmanagement.domain.valueobjects.Donor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class DonorClient {

    private final RestTemplate restTemplate;

    private  final String serverUrl;

    public DonorClient(@Value("${app.donor-catalog.url}")String serverUrl){
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    private UriComponentsBuilder uri(){
        return  UriComponentsBuilder.fromUriString(this.serverUrl);
    }

    public List<Donor> findAll(){
        try{
            return restTemplate.exchange(uri().path("/api/donor").build().toUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Donor>>() {
            }).getBody();
        }catch (Exception exception){
            return Collections.emptyList();
        }
    }
}
