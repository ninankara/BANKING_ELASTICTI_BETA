package com.transfer.dao;

import com.transfer.entity.ApiMsgHis;
import com.transfer.entity.pk.ApiMsgHisPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoApiMsgHis extends JpaRepository<ApiMsgHis, ApiMsgHisPk> {
}
