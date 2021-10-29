package com.edu.cart.DTOobjects.products;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Component
public class ProductClientImpl implements ProductClient {

    private RestTemplate restTemplate;

    public ProductClientImpl(RestTemplateBuilder builder) {

        this.restTemplate = builder.build();
    }

    @Override
    public void changeQuantity(List<ProductDTO> productDTO) {
        String serviceUrl = "http://localhost:8085//api";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<ProductDTO>> request = new HttpEntity<>(productDTO, headers);
        restTemplate.postForObject(serviceUrl + "/updateQnty", request, ProductResponse.class);

    }

}
