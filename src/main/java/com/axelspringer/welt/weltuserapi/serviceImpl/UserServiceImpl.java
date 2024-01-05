package com.axelspringer.welt.weltuserapi.serviceImpl;

import com.axelspringer.welt.weltuserapi.dto.UserDetails;
import com.axelspringer.welt.weltuserapi.dto.UserPosts;
import com.axelspringer.welt.weltuserapi.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private WebClient.Builder webClientBuilder;

    private WebClient webClient;

    private String BASE_URI = "https://jsonplaceholder.typicode.com";

    @PostConstruct
    public void initialize() {
        webClient = webClientBuilder.baseUrl(BASE_URI).build();
    }

    @Override
    public Mono<UserDetails> getUserDetails(Long userId){

        logger.info("fetching user details by userId");
        Mono<UserDetails> userDetailsMono = webClient.get()
                .uri("/users/{userId}", userId)
                .retrieve()
                .bodyToMono(UserDetails.class);

        logger.info("fetching user posts details by userId");
        Mono<UserPosts[]> userPostsMono = webClient.get()
                .uri("/posts?userId={userId}", userId)
                .retrieve()
                .bodyToMono(UserPosts[].class);

        return userDetailsMono.zipWith(userPostsMono, UserDetails::withPosts);
    }
}
