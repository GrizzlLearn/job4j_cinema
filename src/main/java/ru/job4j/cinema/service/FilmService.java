package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 22:17
 */
public interface FilmService {
	Optional<Film> getFilmById(int id);

	Collection<Film> getAll();
}
