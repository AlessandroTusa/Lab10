package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;


public class Model {
	private Graph<Author,DefaultEdge> grafo;
	private List<Author> listaAutori;
	private List<Paper> listaPaper;
	 
	public Model() {

	 }
		
	public void creaGrafo() {
		 PortoDAO dao = new PortoDAO();
		 this.grafo=  new SimpleGraph<>(DefaultEdge.class);
		 listaAutori= new ArrayList<>();
		 listaPaper= new ArrayList<>();	
		 
		 this.listaAutori.addAll(dao.getAllAutori());
		 Graphs.addAllVertices(this.grafo, this.listaAutori);
		 
		 System.out.println("VertexSize : "+this.grafo.vertexSet().size());
		 
		 this.listaPaper.addAll(dao.getAllPaper());
		 for(Paper p: this.listaPaper) {
			 p.addList();

		 }
		 
 for(Author a: this.grafo.vertexSet())
		 for(Author b: this.grafo.vertexSet())
				 for(Paper c: this.listaPaper)
					 if(c.isCollaboratore(a) && c.isCollaboratore(b) && !a.equals(b)){
						 this.grafo.addEdge(a, b);
						 System.out.println(a+" "+b);
					 }
	 System.out.println("EdgeSize : "+this.grafo.edgeSet().size());
		 
	

		}

	public String trovaCoautori(Author value) {
		return this.creaStringaCoautori(value);
	}

private String creaStringaCoautori(Author value) {
	String str="";
	List<Author> coautori = Graphs.neighborListOf(this.grafo, value) ;
		for(Author a: coautori)
		str+=a+"\n";
	return str;
	}

}
