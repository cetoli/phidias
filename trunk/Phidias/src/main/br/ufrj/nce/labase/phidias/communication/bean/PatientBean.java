package br.ufrj.nce.labase.phidias.communication.bean;

import java.util.GregorianCalendar;

import br.ufrj.nce.labase.phidias.communication.container.AbstractActionContainer;

public class PatientBean extends AbstractActionContainer {
	private String id;
	private String name;
	private String gender;
	private GregorianCalendar birthday;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public GregorianCalendar getBirthday() {
		return birthday;
	}

	public void setBirthday(GregorianCalendar birthday) {
		this.birthday = birthday;
	}

}
