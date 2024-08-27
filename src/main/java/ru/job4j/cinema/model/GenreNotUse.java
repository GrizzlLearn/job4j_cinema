package ru.job4j.cinema.model;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @author dl
 * @date 19.08.2024 23:52
 */
@Data
public class GenreNotUse {

	public static final Map<String, String> COLUMN_MAPPING = Map.of(
			"id", "id",
			"name", "name"
	);

	private int id;
	private String name;

	public GenreNotUse() { }

	public GenreNotUse(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GenreNotUse genre = (GenreNotUse) o;
		return id == genre.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
