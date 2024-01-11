package com.welt.userapi.serviceImpl;

import com.welt.userapi.dto.UserAddress;
import com.welt.userapi.dto.UserInformation;
import com.welt.userapi.dto.UserPosts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void getUserInformationTest() {
        // Mock WebClient responses
        UserInformation userInfo = getUserInfo();
        UserPosts post = new UserPosts();
        post.setUserId(1L);
        post.setId(1L);
        post.setBody("Post Body");
        post.setTitle("Post Title");
        UserPosts[] userPosts = {post};
        Mono<UserInformation> userDetailsMono = Mono.just(userInfo);
        Mono<UserPosts[]> userPostsMono = Mono.just(userPosts);


        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/users/{userId}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UserInformation.class)).thenReturn(userDetailsMono);

        when(requestHeadersUriSpec.uri("/posts?userId={userId}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UserPosts[].class)).thenReturn(userPostsMono);

        // Test the service method
        Mono<UserInformation> result = userService.getUserInformation(1L);

        // Verify the result using StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(userDetailsResult -> userDetailsResult.getId().equals(1L))
                .verifyComplete();
    }

    @Test
    void invalidUserInformationTest() {

        // when

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("/users/{userId}", 1L)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(UserInformation.class)).thenThrow(new NullPointerException());

        // then

        assertThrows(NullPointerException.class, () -> {
            userService.getUserInformation(0L);
        });

    }

    public UserInformation getUserInfo(){
        UserInformation userInfo = new UserInformation();
        userInfo.setId(1L);
        userInfo.setName("John Doe");
        userInfo.setEmail("john.doe@example.com");
        userInfo.setPhone("1-770-7x6-x0x1 x56442");
        userInfo.setUsername("John");
        userInfo.setWebsite("jondoe.org");
        UserAddress address = new UserAddress();
        address.setStreet("Kulas");
        address.setCity("Gwenborough");
        address.setZipcode("92998-387");
        address.setSuite("Apt. 55");
        address.setGeo(new UserAddress.Geo("-37.31","81.1"));
        userInfo.setAddress(address);
        return userInfo;
    }
}
