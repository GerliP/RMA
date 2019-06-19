package rma.project.rma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rma.project.rma.Entity.Device;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gerli on 22/05/2018.
 */
@Service
public class DeviceService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${resource.products}")
    private String resource;
    @Value("${resource.products}/{id}")
    private String idResource;

    public List<Device> findAll() {
        return Arrays.stream(restTemplate.getForObject(resource, Device[].class)).collect(Collectors.toList());
    }

    public Device create(Device device) {
        return restTemplate.postForObject(resource, device, Device.class);
    }

    public Device update(int id, Device device) {
        return restTemplate.exchange(idResource, HttpMethod.PUT, new HttpEntity<>(device), Device.class, id).getBody();
    }

    public void delete(int id) {
        restTemplate.delete(idResource, id);
    }

    public Device findOne(int id) {
        return restTemplate.getForEntity(idResource, Device.class, id).getBody();


    }
}

