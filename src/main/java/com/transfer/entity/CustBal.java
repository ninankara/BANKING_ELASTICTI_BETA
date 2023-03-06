package com.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/** AEBK_LBH_TRXHIS[] JPA Entity AUTO Generate */
@Data
@AllArgsConstructor @EqualsAndHashCode(callSuper=false) //lombok
@Entity @Table(name="CUST_BAL") //JPA
public class CustBal implements Serializable
{

    private static final long serialVersionUID = 1L;
    /**   NUMBER       */    @Id @Column(name="ACCT_NO"         , nullable=false ) private Long           acctNo     =0L;
    /**   VARCHAR2(30) */        @Column(name="CUST_NM"                          ) private String         custNm        ;
    /**   VARCHAR2(2)  */        @Column(name="BAL"                              ) private Double         bal           ;
    /**   DATE         */        @Column(name="LAST_TRSC_DATE"                   ) private LocalDateTime  lastTrscDate  ;
    public CustBal() {

    }
}

