package com.ats.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ats.webapi.model.frsetting.NewSetting;

@Repository
public interface NewSettingRepository extends JpaRepository<NewSetting, Integer>{

	NewSetting findBySettingKeyAndDelStatus(String settingKey, int i);

}
