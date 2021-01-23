package com.ats.webapi.repository.rejection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.rejection.RejectionHeader;
//Akhilesh 2021-01-23
public interface RejectionHeaderRepository extends JpaRepository<RejectionHeader, Integer> {

}
