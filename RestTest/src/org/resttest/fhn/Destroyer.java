package org.resttest.fhn;

import java.util.ArrayList;

import org.openrdf.model.Model;

public class Destroyer extends Satisfier {

	
	private int destoyerValue;

	private ArrayList<Satisfier> destroyedSatList;
	
	public Destroyer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param model
	 * @param existCat
	 */
	public Destroyer(Model model, ExistentialCategory existCat) {
		super(model, existCat);
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param model
	 * @param existCat
	 * @param destoyerValue
	 * @param destroyedSatList
	 */
	public Destroyer(Model model, ExistentialCategory existCat,
			int destoyerValue, ArrayList<Satisfier> destroyedSatList) {
		super(model, existCat);
		this.destoyerValue = destoyerValue;
		this.destroyedSatList = destroyedSatList;
	}

	public int getDestoyerValue() {
		return destoyerValue;
	}

	public ArrayList<Satisfier> getDestroyedSatList() {
		return destroyedSatList;
	}

	public void setDestoyerValue(int destoyerValue) {
		this.destoyerValue = destoyerValue;
	}

	public void setDestroyedSatList(ArrayList<Satisfier> destroyedSatList) {
		this.destroyedSatList = destroyedSatList;
	}

}
