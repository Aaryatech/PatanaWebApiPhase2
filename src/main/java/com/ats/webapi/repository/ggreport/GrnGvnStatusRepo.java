package com.ats.webapi.repository.ggreport;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.webapi.model.grngvnreport.GrnGvnStatus;

public interface GrnGvnStatusRepo extends JpaRepository<GrnGvnStatus, Integer> {

}
