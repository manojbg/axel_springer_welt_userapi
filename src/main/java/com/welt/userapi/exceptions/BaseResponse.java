package com.welt.userapi.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.welt.userapi.exceptions.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    ErrorType errorType;
}
