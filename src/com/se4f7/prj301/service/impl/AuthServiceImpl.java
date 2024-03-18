package com.se4f7.prj301.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se4f7.prj301.dto.request.LoginRequestDto;
import com.se4f7.prj301.dto.request.RegisterRequestDto;
import com.se4f7.prj301.dto.response.LoginResponseDto;
import com.se4f7.prj301.dto.response.UserResponseDto;
import com.se4f7.prj301.entities.UserEntity;
import com.se4f7.prj301.repository.UserRepository;
import com.se4f7.prj301.service.AuthService;

public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository = new UserRepository();

	@Override
	public boolean register(RegisterRequestDto registerRequestDto) throws SQLException {
		if (!validateRegistrationFields(registerRequestDto)) {
			return false;
		}
		return this.userRepository.registerUser(registerRequestDto);
	}

	public boolean updateUser(int id, String firstName, String lastName, int status, int role) {
		boolean result = false;
		try {
			this.userRepository.updateUser(id, firstName, lastName, status, role);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean change(String username, String password) {
		boolean result = false;
		try {
			this.userRepository.changePassword(username, password);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public boolean update(String userName, String firstName, String lastName) {
		boolean result = false;
		try {
			this.userRepository.updateProfile(userName, firstName, lastName);
			result = true;
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	@Override
	public int getUserRole(String username) {
		try {
			UserEntity userEntity = userRepository.getUserRole(username);

			if (userEntity != null) {
				return userEntity.getRole();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getUserStatus(String username) {
		try {
			UserEntity userEntity = userRepository.getUserStatus(username);

			if (userEntity != null) {
				return userEntity.getStatus();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getUserIdByUsername(String userName) {
		int result = 0;
		try {
			result = userRepository.getUserIdByUsername(userName);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

	public int count() {
		int count = 0;
		try {
			count = userRepository.countUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public UserEntity getUserById(int id) {
		try {
			UserEntity userEntity = userRepository.getUserById(id);
			return userEntity;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) throws SQLException {
		if (!validateLoginFields(loginRequestDto)) {
			return null;
		}
		return this.userRepository.validateLogin(loginRequestDto);
	}

	@Override
	public UserResponseDto getUserInfo(String username) {
		try {
			UserEntity userEntity = userRepository.getUserByName(username);

			if (userEntity != null) {
				return convertToUserResponseDto(userEntity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserEntity> getAllUser(int page) {
		List<UserEntity> list = new ArrayList<>();
		try {
			list = userRepository.getAllUser(page);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<UserEntity> getFilterStatus(int status) {
		List<UserEntity> list = new ArrayList<>();
		try {
			list = userRepository.getFilterStatus(status);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<UserEntity> getFilterRole(int role) {
		List<UserEntity> list = new ArrayList<>();
		try {
			list = userRepository.getFilterRole(role);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public List<UserEntity> searchUserByName(String username) {
		List<UserEntity> list = new ArrayList<>();
		try {
			list = userRepository.searchUserByName(username);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	private boolean validateLoginFields(LoginRequestDto loginRequestDto) {
		String username = loginRequestDto.getUsername();
		String password = loginRequestDto.getPassword();

		return username != null && !username.isEmpty() && password != null && !password.isEmpty();
	}

	private boolean validateRegistrationFields(RegisterRequestDto registerRequestDto) {
		String username = registerRequestDto.getUsername();
		String lastName = registerRequestDto.getLastName();
		String firstName = registerRequestDto.getFirstName();

		if (username == null || username.length() < 6 || username.length() > 50) {
			return false;
		}

		if (lastName == null || lastName.isEmpty()) {
			return false;
		}

		if (firstName == null || firstName.isEmpty()) {
			return false;
		}

		return true;
	}

	private UserResponseDto convertToUserResponseDto(UserEntity userEntity) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setId(userEntity.getId());
		userResponseDto.setUsername(userEntity.getUsername());
		userResponseDto.setFirstName(userEntity.getFirstName());
		userResponseDto.setLastName(userEntity.getLastName());
		userResponseDto.setRole(userEntity.getRole());
		return userResponseDto;
	}

	public List<UserEntity> getAllRoleUser() {
		List<UserEntity> list = new ArrayList<>();
		try {
			list = userRepository.getAllRoleUser();
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return list;
	}

	public String getUser(int id) {
		String result = null;
		try {
			result = userRepository.getUser(id);
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return result;
	}

}
