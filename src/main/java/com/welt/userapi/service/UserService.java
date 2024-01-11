package com.welt.userapi.service;

import com.welt.userapi.dto.UserInformation;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserInformation> getUserInformation(Long userId);
}
