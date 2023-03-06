package com.transfer.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    public enum ResponseStatus {
        OK,
        FAIL;
    }
    private ResponseStatus status;
    private String message;
    private T output;
}
