package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.entities.CommentEntity;
import com.se4f7.prj301.utils.DBUtil;

public class CommentRepository {

	private static final String INSERT_CMT_SQL = "insert into comments (text, admin_id, task_id, user_id, date) values (?, ?, ?, ?, ?)";

	private static final String SELECT_COMMENTS_BY_TASK_ID = "SELECT * FROM comments WHERE task_id = ? and admin_id = ? and user_id = ? ORDER BY date DESC";

	private static final String SELECT_CMT_USER = "SELECT * FROM comments WHERE task_id = ? and user_id LIKE ? ORDER BY date DESC";

	private static final String DELETE_CMT_SQL = "delete from comments where id = ?";

	public boolean insertComments(String text, int adminId, int taskId, String user, String createdBy)
			throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(INSERT_CMT_SQL)) {
			pstm.setString(1, text);
			pstm.setInt(2, adminId);
			pstm.setInt(3, taskId);
			pstm.setString(4, user);
			pstm.setString(5, createdBy);

			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public List<CommentEntity> getCommentsByTaskId(int taskId, int adminId, String user) throws SQLException {
		List<CommentEntity> comments = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_COMMENTS_BY_TASK_ID)) {
			pstm.setInt(1, taskId);
			pstm.setInt(2, adminId);
			pstm.setString(3, user);
			try (ResultSet rs = pstm.executeQuery()) {
				while (rs.next()) {
					CommentEntity comment = new CommentEntity();
					comment.setId(rs.getInt("id"));
					comment.setText(rs.getString("text"));
					comment.setAdminId(rs.getInt("admin_id"));
					comment.setTaskId(rs.getInt("task_id"));
					comment.setUser(rs.getString("user_id"));
					comment.setCreatedDate(rs.getString("date"));
					comments.add(comment);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return comments;
	}

	public List<CommentEntity> getComments(int taskId, String user) throws SQLException {
		List<CommentEntity> comments = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_CMT_USER)) {
			pstm.setInt(1, taskId);
			pstm.setString(2, "%" + user + "%");
			try (ResultSet rs = pstm.executeQuery()) {
				while (rs.next()) {
					CommentEntity comment = new CommentEntity();
					comment.setId(rs.getInt("id"));
					comment.setText(rs.getString("text"));
					comment.setAdminId(rs.getInt("admin_id"));
					comment.setTaskId(rs.getInt("task_id"));
					comment.setUser(rs.getString("user_id"));
					comment.setCreatedDate(rs.getString("date"));
					comments.add(comment);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return comments;
	}

	public boolean deleteCmt(String id) throws SQLException {
		boolean deleted = false;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(DELETE_CMT_SQL)) {
			pstm.setString(1, id);
			pstm.executeUpdate();
			deleted = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return deleted;
	}

}
