package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.se4f7.prj301.utils.DBUtil;

public class ViewRepository {

	private static final String VIEW = "SELECT * FROM view";

	private static final String UPDATE_VIEW = "UPDATE view SET viewed = viewed + 1";

	public int getViews() throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(VIEW)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		return 0;
	}

	public void updateView() {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_VIEW)) {
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
