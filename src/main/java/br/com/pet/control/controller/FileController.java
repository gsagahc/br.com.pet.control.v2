package br.com.pet.control.controller;

import br.com.pet.control.dto.UploadFileResponseDTO;
import br.com.pet.control.exceptions.MyFileNotFoundException;
import br.com.pet.control.services.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileController {
    private Logger logger = Logger.getLogger(FileController.class.getName());
    @Autowired
    private FileStorageService fileStorageService;
    @PostMapping ("/uploadFile")
    @Tag(name="Upload", description="File upload endpoint")
    public UploadFileResponseDTO uploadFile(@RequestParam("file")MultipartFile file){
        var fileName = fileStorageService.storeFile(file);
        logger.info("Storing file to disk");
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadfile")
                .path(fileName)
                .toUriString();
        return new UploadFileResponseDTO(fileName,fileDownloadUri,file.getContentType(),file.getSize());
    }
    @PostMapping ("/uploadFiles")
    @Tag(name="Upload", description="Multiple File upload endpoint")
    public List<UploadFileResponseDTO> uploadMultipleFile(@RequestParam("files")MultipartFile[] files){

        logger.info("Storing files to disk");
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }
    @GetMapping ("/downloadFile/{fileName:.+}")
    @Tag(name="Download", description="File download endpoint")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        logger.info("reading a file from disk");
        Resource resource = this.fileStorageService.loadFileAsResource(fileName);
        String contentType = "";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            logger.info("Could not determine file type");
        }
        if (contentType.isEmpty()) contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\""
                        + resource.getFilename() + "\"")
                .body(resource);

    }


}
