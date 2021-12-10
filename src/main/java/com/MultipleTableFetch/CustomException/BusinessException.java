package com.MultipleTableFetch.CustomException;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    static long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;
}
