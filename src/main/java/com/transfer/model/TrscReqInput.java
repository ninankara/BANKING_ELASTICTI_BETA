package com.transfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) //lombok
public class TrscReqInput implements Serializable {

    private static final long serialVersionUID = -1L;
    private String kakaoId;
    private String authToken;
    private Long acctNo;
    private Long recvNo;
    private String recvNm;
    private Double amt;
    private String rmk;
    private LocalDateTime dateTime;

}
