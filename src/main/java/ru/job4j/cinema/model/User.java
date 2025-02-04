package ru.job4j.cinema.model;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @author dl
 * @date 19.08.2024 23:51
 */

@Data
public class User {

	public static final Map<String, String> COLUMN_MAPPING = Map.of(
			"id", "id",
			"full_name", "name",
			"email", "email",
			"password", "password"
	);

	private int id;
	private String name;
	private String email;
	private String password;

	public User() { }

	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		User user = (User) o;
		return id == user.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
