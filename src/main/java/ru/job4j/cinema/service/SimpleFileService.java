package ru.job4j.cinema.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FileDto;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.repository.FileRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.UUID;

/**
 * @author dl
 * @date 27.08.2024 20:39
 */
@Service
public class SimpleFileService implements FileService {

	private final FileRepository fileRepository;

	private final String storageDirectory;

	public SimpleFileService(FileRepository sql2oFileRepository,
							 @Value("${file.directory}") String storageDirectory) {
		this.fileRepository = sql2oFileRepository;
		this.storageDirectory = storageDirectory;
		createStorageDirectory(storageDirectory);
	}

	private void createStorageDirectory(String path) {
		try {
			Files.createDirectories(Path.of(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getNewFilePath(String sourceName) {
		return storageDirectory + java.io.File.separator + UUID.randomUUID() + sourceName;
	}

	private void writeFileBytes(String path, byte[] content) {
		try {
			Files.write(Path.of(path), content);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private byte[] readFileAsBites(String path) {
		try {
			return Files.readAllBytes(Path.of(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void deleteFile(String path) {
		try {
			Files.deleteIfExists(Path.of(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public File save(FileDto fileDto) {
		String path = getNewFilePath(fileDto.getName());
		writeFileBytes(path, fileDto.getContent());
		return fileRepository.save(new File(fileDto.getName(), path));
	}

	@Override
	public Optional<FileDto> getFileId(int id) {
		var fileOptional = fileRepository.findById(id);
		if (fileOptional.isEmpty()) {
			return Optional.empty();
		}
		var content = readFileAsBites(fileOptional.get().getPath());
		return Optional.of(new FileDto(fileOptional.get().getName(), content));
	}

	@Override
	public boolean deleteBiId(int id) {
		boolean result = false;
		var fileOptional = fileRepository.findById(id);
		if (fileOptional.isPresent()) {
			deleteFile(fileOptional.get().getPath());
			result = fileRepository.deleteById(id);
		}
		return result;
	}
}
