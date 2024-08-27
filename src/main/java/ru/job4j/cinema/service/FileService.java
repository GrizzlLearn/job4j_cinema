package ru.job4j.cinema.service;

import ru.job4j.cinema.model.File;
import ru.job4j.cinema.dto.FileDto;
import java.util.Optional;

/**
 * @author dl
 * @date 27.08.2024 20:37
 */
public interface FileService {
	File save(FileDto fileDto);

	Optional<FileDto> getFileId(int id);

	boolean deleteBiId(int id);
}
