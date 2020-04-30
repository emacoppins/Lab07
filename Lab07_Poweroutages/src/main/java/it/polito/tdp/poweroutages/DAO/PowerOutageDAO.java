package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<Poweroutage> getPowerOutagesByNerc(int id) {

		String sql = "SELECT id, nerc_id, date_event_began, date_event_finished, customers_affected " + 
				"FROM poweroutages WHERE nerc_id=?";
		
		List<Poweroutage> PwrList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Poweroutage p = new Poweroutage(res.getInt(1), res.getInt(2),res.getTimestamp(3).toLocalDateTime(),res.getTimestamp(4).toLocalDateTime(), res.getInt(5));
				PwrList.add(p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return PwrList;
	}
	

}
