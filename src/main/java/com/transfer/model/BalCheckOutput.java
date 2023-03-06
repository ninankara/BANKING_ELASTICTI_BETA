package com.transfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) //lombok
public class BalCheckOutput implements Serializable {

    private static final long serialVersionUID = -1L;
    private String status;
    private String desc;
    private Long acctNo;
    private Double bal;
    private LocalDateTime dateTime;

}
