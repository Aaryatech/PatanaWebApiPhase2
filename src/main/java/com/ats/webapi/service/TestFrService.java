package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.ConfigureFranchisee;
import com.ats.webapi.model.Franchisee;

public interface TestFrService {

	
   public List<ConfigureFranchisee> findFrMenus();
   
   
   //Akhilesh 2021-01-21
   public List<ConfigureFranchisee> findAllFrBydelStatus();
   
   

}
