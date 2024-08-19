package ru.job4j.cinema.model;

import lombok.Data;

import java.util.Map;
import java.util.Objects;

/**
 * @author dl
 * @date 19.08.2024 23:51
 */

@Data
public class Ticket {

	public static final Map<String, String> COLUMN_MAPPING = Map.of(
			"id", "id",
			"session_id", "sessionId",
			"user_id", "userId",
			"row_number", "rowNumber",
			"place_umber", "placeNumber"
	);

	private int id;
	private int sessionId;
	private int userId;
	private int rowNumber;
	private int placeNumber;

	public Ticket(
			int id,
			int sessionId,
			int userId,
			int rowNumber,
			int placeNumber
			) {
		this.id = id;
		this.sessionId = sessionId;
		this.userId = userId;
		this.rowNumber = rowNumber;
		this.placeNumber = placeNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ticket ticket = (Ticket) o;
		return id == ticket.id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
