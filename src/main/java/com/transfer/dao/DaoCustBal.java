package com.transfer.dao;

import com.transfer.entity.CustBal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DaoCustBal extends JpaRepository<CustBal, String> {
    @Query(value =
            " SELECT * FROM CUST_BAL             "
                    + "   WHERE ACCT_NO = :acctNo    "
            , nativeQuery = true
    )
    public CustBal getBal(@Param("acctNo") Long acctNo);
}
