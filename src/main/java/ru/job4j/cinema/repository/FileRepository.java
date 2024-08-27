package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.File;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 20:29
 */
public interface FileRepository {
	File save(File file);

	Optional<File> findById(int id);

	boolean deleteById(int id);
}
