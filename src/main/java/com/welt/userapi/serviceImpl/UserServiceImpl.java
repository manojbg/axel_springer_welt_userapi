package com.welt.userapi.serviceImpl;

import com.welt.userapi.exceptions.ErrorType;
import com.welt.userapi.dto.UserInformation;
import com.welt.userapi.dto.UserPosts;
import com.welt.userapi.exceptions.UserException;
import com.welt.userapi.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Getter
@Setter
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private WebClient webClient;

    private String BASE_URI="https://jsonplaceholder.typicode.com";

    @Autowired
    private WebClient.Builder webClientBuilder;

    @PostConstruct
    public void initialize() {
        webClient = webClientBuilder.baseUrl(BASE_URI).build();
    }

    @Override
    public Mono<UserInformation> getUserInformation(Long userId) {
            logger.info("fetching user details by userId");
            Mono<UserInformation> userDetailsMono = webClient.get()
                    .uri("/users/{userId}", userId)
                    .retrieve()
                    .bodyToMono(UserInformation.class)
                    .onErrorMap(error -> {
                        // Map the exception to a custom exception
                        return new UserException(ErrorType.INVALID_USER, String.format("No user found with given userid %d", userId));
                    });

            logger.info("fetching user posts details by userId");
            Mono<UserPosts[]> userPostsMono = webClient.get()
                .uri("/posts?userId={userId}", userId)
                .retrieve()
                .bodyToMono(UserPosts[].class);


            return userDetailsMono.zipWith(userPostsMono, UserInformation::withPosts);
    }
}
