package com.transfer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;
import javax.persistence.*;
import com.transfer.entity.pk.CustBasePk;

/** AEBK_LBH_TRXHIS[] JPA Entity AUTO Generate */
@Data
@AllArgsConstructor @EqualsAndHashCode(callSuper=false) //lombok
@IdClass(CustBasePk.class)
@Entity @Table(name="CUST_BASE") //JPA
public class CustBase implements Serializable
{

    private static final long serialVersionUID = 1L;
    /**   NUMBER       */    @Id @Column(name="ACCT_NO"         , nullable=false ) private Long          acctNo     =0L;
    /**   VARCHAR2(16) */    @Id @Column(name="KAKAO_ID"        , nullable=false ) private String        kakaoId       ;
    /**   VARCHAR2(16) */    @Id @Column(name="UUID"            , nullable=false ) private String        uuid          ;
    /**   VARCHAR2(30) */        @Column(name="CUST_NM"                          ) private String        custNm       ;
    /**   VARCHAR2(2)  */        @Column(name="STS"                              ) private String        sts         ;
    /**   NUMBER(16,2) */        @Column(name="PHONE"                            ) private Long          phone     =0L;
    public CustBase() {

    }
}

