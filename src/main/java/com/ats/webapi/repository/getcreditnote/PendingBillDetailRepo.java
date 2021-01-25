package com.ats.webapi.repository.getcreditnote;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.bill.PendingBillDetail;

public interface PendingBillDetailRepo extends JpaRepository<PendingBillDetail, Integer> {

}
