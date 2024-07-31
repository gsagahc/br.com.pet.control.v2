package br.com.pet.control.controller;

import br.com.pet.control.dto.UploadFileResponseDTO;
import br.com.pet.control.services.FileStorageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.logging.Logger;

@RestController
@RequestMapping("/file")
@Tag(name="Files", description="File endpoint")
public class FileController {
    private Logger logger = Logger.getLogger(FileController.class.getName());
    @Autowired
    private FileStorageService fileStorageService;
    @PostMapping ("/uploadFile")
    public UploadFileResponseDTO uploadFile(@RequestParam("file")MultipartFile file){
        var fileName = fileStorageService.storeFile(file);
        logger.info("Storing file to disk");
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/downloadfile")
                .path(fileName)
                .toUriString();
        UploadFileResponseDTO uploadFileResponseDTO =
                new UploadFileResponseDTO(fileName,fileDownloadUri,file.getContentType(),file.getSize());
        return  uploadFileResponseDTO;
    }
}
