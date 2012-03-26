package pur.gwtplatform.service;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pur.gwtplatform.samples.model.Data;


@XmlRootElement(name = "keys")
public class ListeData {

	private List<Data> keys;

	public List<Data> getKeys() {
		return keys;
	}

	public void setKeys(List<Data> keys) {
		this.keys = keys;
	}

	

}
