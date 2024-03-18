package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.entities.ToDoEntity;
import com.se4f7.prj301.utils.DBUtil;

public class ToDoRepository {

	private static final String SELECT_ALL = "select * from work";

	private static final String SELECT_ALL_LIMIT = "select * from work limit 5 offset ?";

	private static final String COUNT_U = "SELECT COUNT(*) from work where created_by = ?";

	private static final String COUNT = "select count(*) from work";

	private static final String INSERT_WORK_SQL = "insert into work (name, description, status, created_by, updated_by, created_date, updated_date, priority, due) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String UPDATE_WORK_SQL = "update work set name = ?, description = ?, status = ?, created_by = ?, updated_by = ?, created_date = ?, updated_date = ?, priority = ?, due = ?"
			+ " where id = ?";

	private static final String DELETE_WORK_SQL = "delete from work where id = ?";

	private static final String SELECT_WORK_ID = "select * from work where id = ?";

	private static final String SELECT_STATUS = "select * from work where status = ?";

	private static final String SELECT_USER_WORK = "SELECT * FROM work\n"
			+ "WHERE created_by = ? ORDER BY priority = 1 DESC, priority DESC LIMIT 5 OFFSET ?";

	private static final String SELECT_STATUS_USER = "select * from work where status = ? and created_by = ?";

	private static final String SELECT_PRIORITY_USER = "select * from work where priority = ? and created_by = ?";

	private static final String SELECT_NAME_TODO = "select * from work where name like ?";

	private static final String SELECT_NAME_TODO_U = "select * from work where name like ? and created_by = ?";

	private static final String EXCEL = "select * from work where created_by = ?";

	private static final String SELECT_DUE = "select due from work where id = ?";

	private static final String SELECT_CREATED = "select created_by from work where id = ?";

	private static final String SELECT_FILTER_DUE = "select * from work WHERE due <= CURDATE()";

	private static final String SELECT_NOT_DUE = "select * from work WHERE due > CURDATE()";

	private static final String SELECT_DUE_USER = "select * from work WHERE due <= CURDATE() and created_by = ?";

	private static final String SELECT_NOT_DUE_USER = "select * from work WHERE due > CURDATE() and created_by = ?";

	public List<ToDoEntity> getWorkByDue(String due, String username) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {
			String sql;
			if ("due".equalsIgnoreCase(due)) {
				sql = SELECT_DUE_USER;
			} else if ("not-due".equalsIgnoreCase(due)) {
				sql = SELECT_NOT_DUE_USER;
			} else {
				return list;
			}

			try (PreparedStatement pstm = conn.prepareStatement(sql)) {
				pstm.setString(1, username);

				try (ResultSet rs = pstm.executeQuery()) {
					while (rs.next()) {
						ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"),
								rs.getString("description"), rs.getInt("status"), rs.getString("created_by"),
								rs.getString("updated_by"), rs.getString("created_date"), rs.getString("updated_date"),
								rs.getInt("priority"), rs.getString("due"));
						list.add(toDo);
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return list;
	}

	public List<ToDoEntity> getWorkByDue(String due) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection()) {
			String sql;
			if ("due".equalsIgnoreCase(due)) {
				sql = SELECT_FILTER_DUE;
			} else if ("not-due".equalsIgnoreCase(due)) {
				sql = SELECT_NOT_DUE;
			} else {
				return list;
			}
			try (PreparedStatement pstm = conn.prepareStatement(sql)) {
				ResultSet rs = pstm.executeQuery();
				while (rs.next()) {
					ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
							rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
							rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
							rs.getString("due"));
					list.add(toDo);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return list;
	}

	public String getCreatedById(int id) throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_CREATED)) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String toDo = rs.getString(1);
				return toDo;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public String getDueById(int id) throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_DUE)) {
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				String toDo = rs.getString(1);
				return toDo;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public List<ToDoEntity> getWorkByName(String name) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_NAME_TODO)) {
			pstm.setString(1, "%" + name + "%");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setDescription(rs.getString(3));
				toDo.setStatus(rs.getInt(4));
				toDo.setCreatedBy(rs.getString(5));
				toDo.setUpdatedBy(rs.getString(6));
				toDo.setCreatedDate(rs.getString(7));
				toDo.setUpdatedDate(rs.getString(8));
				toDo.setPriority(rs.getInt(9));
				toDo.setDue(rs.getString(10));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getWorkByNameU(String name, String createdBy) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_NAME_TODO_U)) {
			pstm.setString(1, "%" + name + "%");
			pstm.setString(2, createdBy);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity();
				toDo.setId(rs.getInt(1));
				toDo.setName(rs.getString(2));
				toDo.setDescription(rs.getString(3));
				toDo.setStatus(rs.getInt(4));
				toDo.setCreatedBy(rs.getString(5));
				toDo.setUpdatedBy(rs.getString(6));
				toDo.setCreatedDate(rs.getString(7));
				toDo.setUpdatedDate(rs.getString(8));
				toDo.setPriority(rs.getInt(9));
				toDo.setDue(rs.getString(10));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getWorkByNameToExcel(String createdBy) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(EXCEL)) {
			pstm.setString(1, createdBy);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getAllWorkUser(int page, String userName) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_USER_WORK)) {
			pstm.setString(1, userName);
			pstm.setInt(2, ((page - 1) * 5));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getAllWork() throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ALL)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getAllWorkLimit(int page) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL_LIMIT)) {
			pstm.setInt(1, ((page - 1) * 5));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getFilter(int status) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_STATUS)) {
			pstm.setInt(1, status);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getFilterUser(int status, String username) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_STATUS_USER)) {
			pstm.setInt(1, status);
			pstm.setString(2, username);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<ToDoEntity> getPriorityUser(int priority, String username) throws SQLException {
		List<ToDoEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_PRIORITY_USER)) {
			pstm.setInt(1, priority);
			pstm.setString(2, username);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				ToDoEntity toDo = new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
				list.add(toDo);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public int countToDo() throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(COUNT)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		return 0;
	}

	public int countUserWork(String userName) throws SQLException {
		int count = 0;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(COUNT_U)) {
			pstm.setString(1, userName);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			throw e;
		}
		return count;
	}

	public boolean insertWork(String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(INSERT_WORK_SQL)) {
			pstm.setString(1, name);
			pstm.setString(2, description);
			pstm.setInt(3, status);
			pstm.setString(4, createdBy);
			pstm.setString(5, updatedBy);
			pstm.setString(6, createdDate);
			pstm.setString(7, updatedDate);
			pstm.setInt(8, priority);
			pstm.setString(9, due);

			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean updateWork(int id, String name, String description, int status, String createdBy, String updatedBy,
			String createdDate, String updatedDate, int priority, String due) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(UPDATE_WORK_SQL)) {
			pstm.setString(1, name);
			pstm.setString(2, description);
			pstm.setInt(3, status);
			pstm.setString(4, createdBy);
			pstm.setString(5, updatedBy);
			pstm.setString(6, createdDate);
			pstm.setString(7, updatedDate);
			pstm.setInt(8, priority);
			pstm.setString(9, due);
			pstm.setInt(10, id);

			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public ToDoEntity getWorkById(String id) throws SQLException {
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_WORK_ID)) {
			pstm.setString(1, id);

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				return new ToDoEntity(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
						rs.getInt("status"), rs.getString("created_by"), rs.getString("updated_by"),
						rs.getString("created_date"), rs.getString("updated_date"), rs.getInt("priority"),
						rs.getString("due"));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public boolean deleteWork(String id) throws SQLException {
		boolean deleted = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(DELETE_WORK_SQL)) {
			pstm.setString(1, id);
			pstm.executeUpdate();
			deleted = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return deleted;
	}
}
