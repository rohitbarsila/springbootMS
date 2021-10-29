package com.edu.cart.DTOobjects.user;

import com.edu.cart.exceptions.SessionExpired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Component
public class UserClientImpl implements UserClient {

    private RestTemplate restTemplate;

    public UserClientImpl(RestTemplateBuilder builder) {

        this.restTemplate = builder.build();
    }

    @Override
    public UserResponse user(UserDTO userDTO) throws SessionExpired {
        UserResponse userResponse = new UserResponse();
        String serviceUrl = "http://localhost:8089/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(userDTO.getToken());
        HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
        try {

            userResponse = restTemplate.postForObject(serviceUrl + "/userDTLS", request, UserResponse.class);
        } catch (HttpClientErrorException error) {
            throw new SessionExpired();
        }
        return userResponse;

    }

}
