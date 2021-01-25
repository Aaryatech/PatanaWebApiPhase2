package com.ats.webapi.repository.getcreditnote;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.bill.PendingBillHeader;

public interface PendingBillHeaderRepo extends JpaRepository<PendingBillHeader, Integer> {

}
