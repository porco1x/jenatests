package org.resttest.fhn;

import org.openrdf.model.Model;

public class SynergesticSatisfier extends Satisfier {

	private int satValue;
	
	
	/**
	 * @param model
	 * @param existCat
	 * @param satValue
	 */
	public SynergesticSatisfier(Model model, ExistentialCategory existCat,
			int satValue) {
		super(model, existCat);
		this.satValue = satValue;
	}

	public SynergesticSatisfier() {
		
	}

	/**
	 * @param model
	 * @param existCat
	 */
	public SynergesticSatisfier(Model model, ExistentialCategory existCat) {
		super(model, existCat);
		// TODO Auto-generated constructor stub
	}

	public int getSatValue() {
		return satValue;
	}

	public void setSatValue(int satValue) {
		this.satValue = satValue;
	}

}
