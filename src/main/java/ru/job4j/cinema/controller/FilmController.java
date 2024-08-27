package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.service.FilmService;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 22:16
 */
@Controller
@RequestMapping("/films")
public class FilmController {
	private final FilmService filmService;

	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}

	@GetMapping("/{id}")
	public String getById(Model model, @PathVariable int id) {
		Optional<Film> filmOptional = filmService.getFilmById(id);
		if (filmOptional.isEmpty()) {
			model.addAttribute("message", "film with id " + id + " not found");
			return "errors/404";
		}
		model.addAttribute("films", filmOptional.get());
		return "films/view";
	}

	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("films", filmService.getAll());
		return "films/list";
	}

}
