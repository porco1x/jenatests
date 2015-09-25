package org.resttest.fhn;

import java.util.ArrayList;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;

public class Satisfier implements FHNIndividual {

	
	private Model model;
	ExistentialCategory existCat;
	
	
	/**
	 * @param model
	 * @param existCat
	 */
	public Satisfier(Model model, ExistentialCategory existCat) {
		super();
		this.model = model;
		this.existCat = existCat;
	}

	public Satisfier() {
		// TODO Auto-generated constructor stub
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public ExistentialCategory getExistCat() {
		return existCat;
	}

	public void setExistCat(ExistentialCategory existCat) {
		this.existCat = existCat;
	}

	@Override
	public void createFHNIndividual(Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getIndividual(Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getStatements(ArrayList<Statement> statements) {
		// TODO Auto-generated method stub

	}

}
