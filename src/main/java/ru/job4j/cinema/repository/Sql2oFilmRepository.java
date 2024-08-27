package ru.job4j.cinema.repository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 22:45
 */
@Repository
public class Sql2oFilmRepository implements FilmRepository {
	private static final Logger LOG = LoggerFactory.getLogger(Sql2oFilmRepository.class.getName());
	private final Sql2o sql2o;

	public Sql2oFilmRepository(Sql2o sql2o) {
		this.sql2o = sql2o;
	}

	@Override
	public Optional<Film> getFilmById(int id) {
		try (var connection = sql2o.open()) {
			String sql = "SELECT * FROM films WHERE id = :id";
			var query = connection.createQuery(sql)
					.addParameter("id", id);
			return Optional.ofNullable(query.executeAndFetchFirst(Film.class));
		}
	}

	@Override
	public Collection<Film> getAll() {
		try (var connection = sql2o.open()) {
			String sql = "SELECT * FROM films";
			var query = connection.createQuery(sql);
			return query.setColumnMappings(Film.COLUMN_MAPPING).executeAndFetch(Film.class);
		}
	}
}
