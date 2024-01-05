package com.axelspringer.welt.weltuserapi.serviceImpl;

import com.axelspringer.welt.weltuserapi.dto.UserDetails;
import com.axelspringer.welt.weltuserapi.dto.UserPosts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private final WebClient.Builder webClientBuilder = Mockito.mock(WebClient.Builder.class);
    private final WebClient webClient = Mockito.mock(WebClient.class);
    UserServiceImpl userService = new UserServiceImpl();
    private final WebClient.RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
    private final WebClient.RequestHeadersSpec requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);

    private final WebClient.ResponseSpec responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

    @BeforeEach
    void setup() {
        userService.setWebClientBuilder(webClientBuilder);
        when(webClientBuilder.baseUrl(anyString())).thenReturn(webClientBuilder);
        when(webClientBuilder.build()).thenReturn(webClient);
        userService.initialize();
    }

    @Test
    void getUserDetails() {
        // Mock WebClient responses
        UserDetails userDetails = new UserDetails(1L, "John Doe", "john.doe@example.com");
        UserPosts post = new UserPosts();
        post.setUserId(1L);
        post.setId(1L);
        post.setBody("Post Body");
        post.setTitle("Post Title");
        UserPosts[] userPosts = {post};
        Mono<UserDetails> userDetailsMono = Mono.just(userDetails);
        Mono<UserPosts[]> userPostsMono = Mono.just(userPosts);


        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/users/{userId}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UserDetails.class)).thenReturn(userDetailsMono);

        when(requestHeadersUriSpec.uri("/posts?userId={userId}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UserPosts[].class)).thenReturn(userPostsMono);

        // Test the service method
        Mono<UserDetails> result = userService.getUserDetails(1L);

        // Verify the result using StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(userDetailsResult -> userDetailsResult.getId().equals(1L))
                .verifyComplete();
    }
}
