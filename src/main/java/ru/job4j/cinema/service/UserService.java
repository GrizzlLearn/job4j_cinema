package ru.job4j.cinema.service;

import ru.job4j.cinema.model.User;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 14:24
 */
public interface UserService {
	Optional<User> save(User user);

	Optional<User> findByEmailAndPassword(String email, String password);
}
