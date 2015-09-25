package org.resttest.fhn;

import java.util.ArrayList;

import org.openrdf.model.Model;

public class InhibittingSatisfier extends Satisfier {

	
	private int inhibittingValue;
	

	private ArrayList<Satisfier> satsInhibitted;
	
	
	public InhibittingSatisfier() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param model
	 * @param existCat
	 * @param inhibittingValue
	 * @param satsInhibitted
	 */
	public InhibittingSatisfier(Model model, ExistentialCategory existCat,
			int inhibittingValue, ArrayList<Satisfier> satsInhibitted) {
		super(model, existCat);
		this.inhibittingValue = inhibittingValue;
		this.satsInhibitted = satsInhibitted;
	}

	/**
	 * @param model
	 * @param existCat
	 */
	public InhibittingSatisfier(Model model, ExistentialCategory existCat) {
		super(model, existCat);
		// TODO Auto-generated constructor stub
	}

	
	public int getInhibittingValue() {
		return inhibittingValue;
	}


	public void setInhibittingValue(int inhibittingValue) {
		this.inhibittingValue = inhibittingValue;
	}


	public ArrayList<Satisfier> getSatsInhibitted() {
		return satsInhibitted;
	}


	public void setSatsInhibitted(ArrayList<Satisfier> satsInhibitted) {
		this.satsInhibitted = satsInhibitted;
	}

}
