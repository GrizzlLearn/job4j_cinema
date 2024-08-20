package ru.job4j.cinema.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

/**
 * @author dl
 * @date 19.08.2024 23:52
 */

@Data
public class FilmSession {

	public static final Map<String, String> COLUMN_MAPPING = Map.of(
			"id", "id",
			"film_id", "filmId",
			"hassl_id", "hallsId",
			"start_time", "startTime",
			"end_time", "endTime",
			"price", "price"
	);

	private int id;
	private int filmId;
	private int hallsId;
	private Timestamp startTime;
	private Timestamp endTime;
	private int price;

	public FilmSession() { }

	public FilmSession(
			int id,
			int filmId,
			int hallsId,
			Timestamp startTime,
			Timestamp endTime,
			int price) {
		this.id = id;
		this.filmId = filmId;
		this.hallsId = hallsId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FilmSession that = (FilmSession) o;
		return id == that.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
