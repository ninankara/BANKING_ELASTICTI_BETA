package com.transfer.dao;

import com.transfer.entity.TrnfHis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoTrnfHis extends JpaRepository<TrnfHis, String> {

    @Query(value =
            " SELECT * FROM TRNF_HIS              "
                    + "   WHERE TRSC_DATE < (SYSDATE - interval '23' HOUR) AND STS = 'WT'    "
            , nativeQuery = true
    )
    public List<TrnfHis> findUnfinishedTrsc();

    @Query(value =
            " SELECT * FROM TRNF_HIS             "
                    + "   WHERE TRSC_DATE < (SYSDATE - interval '24' HOUR) AND STS = 'WT'    "
            , nativeQuery = true
    )
    public List<TrnfHis> findTimeoutTrsc();

    @Query(value =
            " SELECT * FROM TRNF_HIS             "
                    + "   WHERE REF_NO = :refNo   "
            , nativeQuery = true
    )
    public TrnfHis findByRefNo(@Param("refNo") Long refNo);

    @Query(value =
            " SELECT * FROM TRNF_HIS             "
                    + "   WHERE SEND_ACCT_NO = :acctNo   "
                    + "   AND TRSC_DATE BETWEEN :strDt and :endDt    "
            , nativeQuery = true
    )
    public List<TrnfHis> findTrscHis(@Param("acctNo") Long acctNo, @Param("strDt") LocalDateTime strDt, @Param("endDt") LocalDateTime endDt);
}
