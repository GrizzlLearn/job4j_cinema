package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dl
 * @date 27.08.2024 14:18
 */
@Controller
public class IndexController {

	@GetMapping({"/", "/index"})
	public String getIndex() {
		return "index";
	}
}
