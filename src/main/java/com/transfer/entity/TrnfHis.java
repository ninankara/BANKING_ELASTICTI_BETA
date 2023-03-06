package com.transfer.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/** AEBK_LBH_TRXHIS[] JPA Entity AUTO Generate */
@Data @AllArgsConstructor @EqualsAndHashCode(callSuper=false) //lombok
@Entity @Table(name="TRNF_HIS") //JPA
public class TrnfHis implements Serializable
{

    private static final long serialVersionUID = 1L;
    /**   VARCHAR2(16) */    @Id @Column(name="REF_NO"          , nullable=false ) private Long          refNo             ;
    /**   NUMBER       */        @Column(name="SEND_ACCT_NO"                     ) private Long          sendAcctNo     =0L;
    /**   NUMBER       */        @Column(name="RECV_ACCT_NO"                     ) private Long          recvAcctNo     =0L;
    /**   DATE         */        @Column(name="TRSC_DATE"                        ) private LocalDateTime trscDate          ;
    /**   NUMBER       */        @Column(name="AMT"                              ) private Double        amt               ;
    /**   VARCHAR2(20) */        @Column(name="RMK"                              ) private String        rmk               ;
    /**   NUMBER       */        @Column(name="BAL_AF_TRSC"                      ) private Double        balAfTrsc         ;
    /**   DATE         */        @Column(name="REQUEST_TIME"                     ) private LocalDateTime requestTime       ;
    /**   DATE         */        @Column(name="CONFIRMATION_TIME"                ) private LocalDateTime confirmationTime  ;
    /**   VARCHAR2(2)  */        @Column(name="STS"                              ) private String        sts               ;

    public TrnfHis() {

    }
}

