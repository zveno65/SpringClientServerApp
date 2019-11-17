package ru.plotnikov.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.plotnikov.exception.UserAccountException;
import ru.plotnikov.model.Role;
import ru.plotnikov.model.User;

import java.util.Collections;
import java.util.List;

@Service
@Cacheable("users")
@Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 5000))
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public UserServiceImpl(
            RestTemplate restTemplate, @Value("${application.server.url}") String serverUrl
    ) {
        this.restTemplate = restTemplate;
        this.serverUrl = serverUrl;
    }

    @Override
    public User findById(Long id) {
        return  restTemplate.exchange(
                serverUrl + "/rest/userRest?id={id}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                },
                id
        ).getBody();
    }

    @Override
    public User loadUserByUsername(String name) {
        User user = restTemplate.exchange(
                serverUrl + "/rest/checkUserRest?name={name}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<User>() {
                },
                name
        ).getBody();
        return user;
    }

    @Override
    public User addUser(User user) throws UserAccountException {
        return restTemplate.postForObject(serverUrl + "/rest/newRest", user, User.class);
    }

    @Override
    public User updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        return restTemplate.postForObject(serverUrl + "/rest/editRest", request, User.class);
    }

    @Override
    public List<User> findAll() {
        return  restTemplate.exchange(
            serverUrl + "/rest/usersRest",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<User>>() {
            }
        ).getBody();
    }



}
