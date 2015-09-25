/**
 * 
 */
package org.resttest.fhn;

import java.util.ArrayList;

import org.openrdf.model.Model;
import org.openrdf.model.Statement;

/**
 * @author PoRcO
 *
 */
public class Role implements FHNIndividual {

	private Model model;


	private ArrayList<Agent> assignedAgents;
	private ArrayList<Need> assignedNeeds;
	
	
	/**
	 * 
	 */
	
	public Role(Model model, ArrayList<Agent> assignedAgents,
			ArrayList<Need> assignedNeeds) {
		super();
		this.model = model;
		this.assignedAgents = assignedAgents;
		this.assignedNeeds = assignedNeeds;
	}
	
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public ArrayList<Agent> getAssignedAgents() {
		return assignedAgents;
	}

	public void setAssignedAgents(ArrayList<Agent> assignedAgents) {
		this.assignedAgents = assignedAgents;
	}

	public ArrayList<Need> getAssignedNeeds() {
		return assignedNeeds;
	}

	public void setAssignedNeeds(ArrayList<Need> assignedNeeds) {
		this.assignedNeeds = assignedNeeds;
	}

	/* (non-Javadoc)
	 * @see org.resttest.fhn.FHNIndividual#createFHNIndividual(org.openrdf.model.Model)
	 */
	@Override
	public void createFHNIndividual(Model model) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.resttest.fhn.FHNIndividual#getIndividual(org.openrdf.model.Model)
	 */
	@Override
	public void getIndividual(Model model) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.resttest.fhn.FHNIndividual#getStatements(java.util.ArrayList)
	 */
	@Override
	public void getStatements(ArrayList<Statement> statements) {
		// TODO Auto-generated method stub

	}

}
