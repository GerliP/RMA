package rma.project.rma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rma.project.rma.Entity.Claim;
/**
 * Created by gerli on 27/05/2018.
 */
@Service
public class ClaimService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.claims}")
    private String resource;
    @Value("${resource.claims}/{id}")
    private String idResource;

    public Claim create(Claim claim) {
        return restTemplate.postForObject(resource, claim, Claim.class);
    }

    public Claim findOne(int id) {
        return restTemplate.getForEntity(idResource, Claim.class, id).getBody();


    }
}
