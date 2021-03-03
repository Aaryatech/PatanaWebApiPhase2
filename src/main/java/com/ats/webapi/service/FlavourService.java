package com.ats.webapi.service;

import java.util.List;

import com.ats.webapi.model.ErrorMessage;
import com.ats.webapi.model.Flavour;

public interface FlavourService {
	public ErrorMessage save(Flavour flavour);
	public List<Flavour> findAllFlavour();
	public List<Flavour> findAllFlavourList();
	public Flavour findFlavour(int spfId);
	public Flavour updateFlavour(int id);

}
