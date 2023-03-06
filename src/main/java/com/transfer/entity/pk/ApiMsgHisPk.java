package com.transfer.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) //lombok
public class ApiMsgHisPk implements Serializable {
    private static final long serialVersionUID = 1L;
    /**   DATE         */    @Id @Column(name="DATE"            , nullable=false ) private LocalDateTime dateTime      ;
    /**   VARCHAR2(16) */    @Id @Column(name="API_NAME"        , nullable=false ) private String        apiName       ;
    /**   NUMBER       */    @Id @Column(name="REF_NO"          , nullable=false ) private Long          refNo      =0L;
}
