package com.axelspringer.welt.weltuserapi.service;

import com.axelspringer.welt.weltuserapi.dto.UserDetails;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDetails> getUserDetails(Long userId);
}
