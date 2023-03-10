package com.transfer.entity;

import com.transfer.entity.pk.ApiMsgHisPk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor @EqualsAndHashCode(callSuper=false) //lombok
@IdClass(ApiMsgHisPk.class)
@Entity @Table(name="API_MSG_HIS") //JPA
public class ApiMsgHis implements Serializable
{

    private static final long serialVersionUID = 1L;
    /**   DATE         */    @Id @Column(name="DATE"            , nullable=false ) private LocalDateTime  dateTime    ;
    /**   VARCHAR2(16) */    @Id @Column(name="API_NAME"        , nullable=false ) private String         apiName     ;
    /**   VARCHAR2(16) */    @Id @Column(name="REF_NO"          , nullable=false ) private Long           refNo       ;
    /**   NUMBER       */        @Column(name="ACCT_NO"                          ) private Long           acctNo   =0L;
    /**   VARCHAR2(500)*/        @Column(name="MSG"                              ) private String         msg         ;

    public ApiMsgHis() {

    }
}

