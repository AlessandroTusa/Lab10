package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.Paper;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
		System.out.println(pd.getAutore(85));
		System.out.println(pd.getArticolo(2293546));
		for(Paper p : pd.getAllPaper())
		System.out.println("NUOVO:"+pd.getCollaboratori(p.getEprintid()));
	}

}
