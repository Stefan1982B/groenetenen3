package be.vdab.groenetenen.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class VanTotPostcodeForm {
	@NotNull
	@Range(min = 1000, max = 9999)
	private int van;
	@NotNull
	@Range(min = 1000, max = 9999)
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
