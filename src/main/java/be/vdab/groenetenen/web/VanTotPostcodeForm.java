package be.vdab.groenetenen.web;

import javax.validation.constraints.NotNull;

import be.vdab.groenetenen.constraints.Postcode;
import be.vdab.groenetenen.constraints.VanTotPostcodeFormVanKleinerDanOfGelijkAanTot;

@VanTotPostcodeFormVanKleinerDanOfGelijkAanTot
public class VanTotPostcodeForm {
	@NotNull
	@Postcode
	private int van;
	@NotNull
	@Postcode
	private int tot;

	public int getVan() {
		return van;
	}

	public void setVan(int van) {
		this.van = van;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

}
