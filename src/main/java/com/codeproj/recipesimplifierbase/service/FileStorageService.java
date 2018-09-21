package com.codeproj.recipesimplifierbase.service;

import com.codeproj.recipesimplifierbase.common.RecipeImageFileStorageProperties;

import com.codeproj.recipesimplifierbase.dto.RecipeImageDto;
import com.codeproj.recipesimplifierbase.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    public FileStorageService(RecipeImageFileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getRecipeImages())
                .normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, Long recipeId) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Files.createDirectories(this.fileStorageLocation.resolve(recipeId.toString() + "/"));
            Path targetLocation = this.fileStorageLocation.resolve(recipeId.toString() + "/" + fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public RecipeImageDto loadRecipeImage(
            String fileName, Long recipeId)
            throws IOException {

        Path filePath = getRecipePath(fileName, recipeId);
        byte[] media = Files.readAllBytes(filePath);
        MediaType mimeTypeObj = null;
        File tempFile = new File(filePath.toString());
        String mimeType = new MimetypesFileTypeMap().getContentType(tempFile);
        if (mimeType == null || "".equals(mimeType)) {
            mimeTypeObj = MediaType.APPLICATION_OCTET_STREAM;
        } else if ("image/jpeg".equals(mimeType)) {
            mimeTypeObj = MediaType.IMAGE_JPEG;
        } else if ("image/png".equals(mimeType)) {
            mimeTypeObj = MediaType.IMAGE_PNG;
        } else {
            mimeTypeObj = MediaType.APPLICATION_OCTET_STREAM;
        }

        return new RecipeImageDto(media, mimeTypeObj);
    }

    public boolean deleteRecipeImage(String fileName, Long recipeId) {
        Path filePath = getRecipePath(fileName, recipeId);
        try {
            Files.delete(filePath);
            return true;
        } catch (IOException e) {
            logger.error("Couldn't delete file on the following path:" + filePath);
            return false;
        }
    }

    private Path getRecipePath(String fileName, Long recipeId) {
        return this.fileStorageLocation.resolve(recipeId.toString() + "/" + fileName).normalize();
    }

}