package com.se4f7.prj301.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.se4f7.prj301.dto.request.LoginRequestDto;
import com.se4f7.prj301.dto.request.RegisterRequestDto;
import com.se4f7.prj301.dto.response.LoginResponseDto;
import com.se4f7.prj301.entities.UserEntity;
import com.se4f7.prj301.enums.UserStatus;
import com.se4f7.prj301.utils.DBUtil;

public class UserRepository {

	private static final String INSERT_USERS_SQL = "INSERT INTO users"
			+ "  (username, password, status, firstName, lastName, role) VALUES " + " (?, ?, ?, ?, ?, ?);";

	private static final String VALIDATE_LOGIN = "select * from users where username = ? and status = ?";

	private static final String VALIDATE_REGISTER = "select * from users where username = ?";

	private static final String CHANGE_PASSWORD = "update users SET password = ? WHERE username = ? ";

	private static final String UPDATE_PROFILE = "update users set firstName = ?, lastName = ?" + " where username = ?";

	private static final String UPDATE_USER = "update users set firstName = ?, lastName = ?, status = ?, role = ?"
			+ " where id = ?";

	private static final String ROLE = "select role from users where username = ?";

	private static final String STATUS = "select status from users where username = ?";

	private static final String COUNT = "select count(*) from users";

	private static final String SELECT_ALL = "select * from users ORDER BY role = 2 DESC, role DESC limit 5 offset ?";

	private static final String SELECT_STATUS = "select * from users where status = ?";

	private static final String SELECT_ROLE = "select * from users where role = ?";

	private static final String SELECT_USER_ID = "select * from users where id = ?";

	private static final String SELECT_NAME_USER = "select * from users where username like ?";

	private static final String SELECT_ID_BY_U = "SELECT id FROM users WHERE username = ?";

	private static final String SELECT_U_BY_ROLE = "SELECT * FROM users WHERE role = 1";

	private static final String SELECT_U_BY_ID = "SELECT username FROM users WHERE id = ?";

	public String getUser(int id) throws SQLException {
		String username = null;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_U_BY_ID)) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					username = resultSet.getString("username");
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return username;
	}

	public List<UserEntity> getAllRoleUser() throws SQLException {
		List<UserEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_U_BY_ROLE)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(rs.getInt(1));
				userEntity.setUsername(rs.getString(2));
				userEntity.setPassword(rs.getString(3));
				userEntity.setStatus(rs.getInt(4));
				userEntity.setFirstName(rs.getString(5));
				userEntity.setLastName(rs.getString(6));
				userEntity.setRole(rs.getInt(7));
				list.add(userEntity);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public int getUserIdByUsername(String username) throws SQLException {
		int userId = 0;
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ID_BY_U)) {
			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					userId = resultSet.getInt("id");
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return userId;
	}

	public List<UserEntity> searchUserByName(String username) throws SQLException {
		List<UserEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(SELECT_NAME_USER)) {
			pstm.setString(1, "%" + username + "%");
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(rs.getInt(1));
				userEntity.setUsername(rs.getString(2));
				userEntity.setPassword(rs.getString(3));
				userEntity.setStatus(rs.getInt(4));
				userEntity.setFirstName(rs.getString(5));
				userEntity.setLastName(rs.getString(6));
				userEntity.setRole(rs.getInt(7));
				list.add(userEntity);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public boolean updateUser(int id, String firstName, String lastName, int status, int role) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_USER)) {
			pstm.setString(1, firstName);
			pstm.setString(2, lastName);
			pstm.setInt(3, status);
			pstm.setInt(4, role);
			pstm.setInt(5, id);
			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public UserEntity getUserById(int id) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_ID);) {
			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					UserEntity userEntity = new UserEntity();
					userEntity.setId(resultSet.getInt("id"));
					userEntity.setUsername(resultSet.getString("username"));
					userEntity.setPassword(resultSet.getString("password"));
					userEntity.setStatus(resultSet.getInt("status"));
					userEntity.setFirstName(resultSet.getString("firstName"));
					userEntity.setLastName(resultSet.getString("lastName"));
					userEntity.setRole(resultSet.getInt("role"));
					return userEntity;
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

	public List<UserEntity> getFilterRole(int role) throws SQLException {
		List<UserEntity> list = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_ROLE)) {
			statement.setInt(1, role);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(rs.getInt(1));
				userEntity.setUsername(rs.getString(2));
				userEntity.setPassword(rs.getString(3));
				userEntity.setStatus(rs.getInt(4));
				userEntity.setFirstName(rs.getString(5));
				userEntity.setLastName(rs.getString(6));
				userEntity.setRole(rs.getInt(7));
				list.add(userEntity);
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return list;
	}

	public List<UserEntity> getFilterStatus(int status) throws SQLException {
		List<UserEntity> list = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_STATUS)) {
			statement.setInt(1, status);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(rs.getInt(1));
				userEntity.setUsername(rs.getString(2));
				userEntity.setPassword(rs.getString(3));
				userEntity.setStatus(rs.getInt(4));
				userEntity.setFirstName(rs.getString(5));
				userEntity.setLastName(rs.getString(6));
				userEntity.setRole(rs.getInt(7));
				list.add(userEntity);
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return list;
	}

	public UserEntity getUserRole(String username) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(ROLE);) {
			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					UserEntity userEntity = new UserEntity();
					userEntity.setRole(resultSet.getInt("role"));
					return userEntity;
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

	public UserEntity getUserStatus(String username) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(STATUS);) {
			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					UserEntity userEntity = new UserEntity();
					userEntity.setStatus(resultSet.getInt("status"));
					return userEntity;
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

	public boolean registerUser(RegisterRequestDto user) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, hashPassword);
			preparedStatement.setInt(3, UserStatus.ACTIVE.ordinal());
			preparedStatement.setString(4, user.getFirstName());
			preparedStatement.setString(5, user.getLastName());
			preparedStatement.setInt(6, 1);

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return false;
	}

	public LoginResponseDto validateLogin(LoginRequestDto user) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_LOGIN)) {
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setInt(2, UserStatus.ACTIVE.ordinal());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				if (BCrypt.checkpw(user.getPassword(), rs.getString("password"))) {
					return new LoginResponseDto(rs.getInt("id"), rs.getString("username"), UserStatus.ACTIVE);
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

	public UserEntity getUserByName(String username) throws SQLException {
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(VALIDATE_REGISTER);) {
			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					UserEntity userEntity = new UserEntity();
					userEntity.setId(resultSet.getInt("id"));
					userEntity.setUsername(resultSet.getString("username"));
					userEntity.setPassword(resultSet.getString("password"));
					userEntity.setStatus(resultSet.getInt("status"));
					userEntity.setFirstName(resultSet.getString("firstName"));
					userEntity.setLastName(resultSet.getString("lastName"));
					userEntity.setRole(resultSet.getInt("role"));
					return userEntity;
				}
			}
		} catch (SQLException e) {
			DBUtil.printSQLException(e);
		}
		return null;
	}

	public boolean changePassword(String username, String password) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstm = conn.prepareStatement(CHANGE_PASSWORD)) {
			String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
			pstm.setString(1, hashPassword);
			pstm.setString(2, username);

			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean updateProfile(String userName, String firstName, String lastName) throws SQLException {
		boolean result = false;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(UPDATE_PROFILE)) {
			pstm.setString(1, firstName);
			pstm.setString(2, lastName);
			pstm.setString(3, userName);
			pstm.executeUpdate();
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public int countUser() throws SQLException {
		int result = 0;
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(COUNT)) {
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public List<UserEntity> getAllUser(int page) throws SQLException {
		List<UserEntity> list = new ArrayList<>();
		try (Connection conn = DBUtil.getConnection(); PreparedStatement pstm = conn.prepareStatement(SELECT_ALL)) {
			pstm.setInt(1, ((page - 1) * 5));
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				UserEntity user = new UserEntity();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setStatus(rs.getInt(4));
				user.setFirstName(rs.getString(5));
				user.setLastName(rs.getString(6));
				user.setRole(rs.getInt(7));
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

}
