package be.vdab.groenetenen.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.vdab.groenetenen.entities.Offerte;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferteEnOffertesURL {
	private Offerte offerte;
	private String OffertesURL;

	public Offerte getOfferte() {
		return offerte;
	}

	public String getOffertesURL() {
		return OffertesURL;
	}

	public OfferteEnOffertesURL(Offerte offerte, String offertesURL) {
		this.offerte = offerte;
		OffertesURL = offertesURL;
	}

	protected OfferteEnOffertesURL() {

	}
}
