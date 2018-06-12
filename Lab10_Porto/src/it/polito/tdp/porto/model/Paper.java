package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.porto.db.PortoDAO;

public class Paper {

	private int eprintid;
	private String title;
	private PortoDAO dao;
	private List<Author> collaboratori;

	public Paper(int eprintid, String title) {
		this.eprintid = eprintid;
		this.title = title;
		this.collaboratori= new ArrayList<>();
	}
	public void addList() {
		this.dao= new PortoDAO();
		this.collaboratori.addAll(this.dao.getCollaboratori(this.getEprintid()));
	}
	
	public int getSizeList(){
	return this.collaboratori.size();
	}
	public int getEprintid() {
		return eprintid;
	}

	public void setEprintid(int eprintid) {
		this.eprintid = eprintid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	@Override
	public String toString() {
		return "Paper [eprintid=" + eprintid + ", title=" + title ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eprintid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paper other = (Paper) obj;
		if (eprintid != other.eprintid)
			return false;
		return true;
	}


	public boolean isCollaboratore(Author a) {
		if(this.collaboratori.contains(a)) return true;
		return false;
	}
	
	

}
