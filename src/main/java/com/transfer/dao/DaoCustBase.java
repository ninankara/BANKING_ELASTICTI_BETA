package com.transfer.dao;

import com.transfer.entity.CustBase;
import com.transfer.entity.pk.CustBasePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DaoCustBase extends JpaRepository<CustBase, CustBasePk> {
    @Query(value =
            " SELECT UUID FROM CUST_BASE             "
                    + "   WHERE ACCT_NO = :acctNo    "
            , nativeQuery = true
    )
    public String getUuid(@Param("acctNo") Long acctNo);
}
