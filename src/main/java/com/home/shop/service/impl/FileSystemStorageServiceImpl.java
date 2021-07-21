package com.home.shop.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.home.shop.config.StorageProperties;
import com.home.shop.exception.StorageException;
import com.home.shop.exception.StorageFileNotFoundException;
import com.home.shop.service.StorageService;

@Service
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path rootLocation;

	
	@Override
	public String getStoredFileName(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "i" + id + "." + ext;
	}

	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	
	@Override
	public void store(MultipartFile file, String storedfileName) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("failed to store empty file!");
			}
			Path destinationFile = this.rootLocation.resolve(Paths.get(storedfileName)).normalize().toAbsolutePath();

			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				throw new StorageException("cannot store file outside curent directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new StorageException("Failed to store file", e);
		}
	}

	
	@Override
	public Resource LoadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileNotFoundException("Could not read file " + filename);
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file " + filename);
		}
	}

	
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	
	@Override
	public void delete(String storedFileName) throws IOException {
		Path destinationFile = rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();
		Files.delete(destinationFile);
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

}
