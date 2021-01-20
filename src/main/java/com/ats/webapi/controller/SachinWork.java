package com.ats.webapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ats.webapi.model.AllMenus;
import com.ats.webapi.repository.MainMenuConfigurationRepository;

@RestController
public class SachinWork {
	@Autowired
	private MainMenuConfigurationRepository mainMenuConfigurationRepository;
	@RequestMapping(value = { "/getNonConfMenus" }, method = RequestMethod.GET)
	public @ResponseBody List<AllMenus> getNonConfMenus() {

		List<AllMenus> allMenu = mainMenuConfigurationRepository.getAllNonConfMenus();

		return allMenu;
	}
	
}
