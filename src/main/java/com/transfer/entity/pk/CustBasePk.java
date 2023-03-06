package com.transfer.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) //lombok
public class CustBasePk  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**   NUMBER       */    @Id @Column(name="ACCT_NO"         , nullable=false ) private Long     acctNo     =0L;
    /**   VARCHAR2(16) */    @Id @Column(name="KAKAO_ID"        , nullable=false ) private String   kakaoId       ;
    /**   VARCHAR2(16) */    @Id @Column(name="UUID"            , nullable=false ) private String   uuid       ;
}
