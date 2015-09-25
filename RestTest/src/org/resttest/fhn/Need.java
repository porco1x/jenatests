package org.resttest.fhn;

import java.util.ArrayList;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;

public class Need implements FHNIndividual {

	
	private Model model;
	AxiologicalNeeds axio;
	private ArrayList<Satisfier> assignedSatList;
	
	
	
	/**
	 * @param model
	 * @param axio
	 * @param assignedSatList
	 */
	public Need(Model model, AxiologicalNeeds axio,
			ArrayList<Satisfier> assignedSatList) {
		super();
		this.model = model;
		this.axio = axio;
		this.assignedSatList = assignedSatList;
	}

	public void setAssignedSatList(ArrayList<Satisfier> assignedSatList) {
		this.assignedSatList = assignedSatList;
	}

	/**
	 * @param model
	 * @param axio
	 */
	public Need(Model model, AxiologicalNeeds axio) {
		super();
		this.model = model;
		this.axio = axio;
	}
	
	public Need() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Satisfier> getAssignedSatList() {
		return assignedSatList;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public AxiologicalNeeds getAxio() {
		return axio;
	}

	public void setAxio(AxiologicalNeeds axio) {
		this.axio = axio;
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
