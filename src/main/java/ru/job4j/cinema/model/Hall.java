package ru.job4j.cinema.model;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @author dl
 * @date 19.08.2024 23:51
 */

@Data
public class Hall {

	public static final Map<String, String> COLUMN_MAPPING = Map.of(
			"id", "id",
			"name", "name",
			"row_count", "rowCount",
			"place_count", "placeCount",
			"description", "description"
	);

	private int id;
	private String name;
	private int rowCount;
	private int placeCount;
	private String description;

	public Hall() { }

	public Hall(int id, String name, int rowCount, int placeCount, String description) {
		this.id = id;
		this.name = name;
		this.rowCount = rowCount;
		this.placeCount = placeCount;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hall hall = (Hall) o;
		return id == hall.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
