package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getAllAutori() {
		final String sql = "SELECT * FROM author";
		List<Author> lista= new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author a = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				if(a!=null)lista.add(a);
			}
			return lista;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Paper> getAllPaper() {
		final String sql = "SELECT eprintid, title FROM paper ";
		List<Paper> lista= new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper p = new Paper(rs.getInt("eprintid"), rs.getString("title"));
				lista.add(p);
				
			}
			return lista;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Author> getCollaboratori(int id) {
		final String sql = "SELECT * FROM author as a, creator as c  WHERE a.id=c.authorid AND c.eprintid=?";
		List<Author> lista= new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author a = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				lista.add(a);
			}
			return lista;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		}

	public List<Author> getAmici(Author a) {
		final String sql = "SELECT DISTINCT a2.id, a2.lastname, a2.firstname " + 
				"FROM creator c1, creator c2, author a2 " + 
				"WHERE c1.eprintid=c2.eprintid " + 
				"AND c2.authorid=a2.id " + 
				"AND c1.authorid= ? " + 
				"AND a2.id <> c1.authorid " + 
				"ORDER BY a2.lastname ASC, a2.firstname ASC" ;
		List<Author> lista= new ArrayList<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Author b = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				lista.add(b);
			}
			return lista;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}