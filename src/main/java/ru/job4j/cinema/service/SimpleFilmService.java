package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;
import java.util.Collection;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 22:44
 */
@Service
public class SimpleFilmService implements FilmService {
	private final FilmRepository filmRepository;

	public SimpleFilmService(FilmRepository filmRepository) {
		this.filmRepository = filmRepository;
	}

	@Override
	public Optional<Film> getFilmById(int id) {
		return filmRepository.getFilmById(id);
	}

	@Override
	public Collection<Film> getAll() {
		return filmRepository.getAll();
	}
}
