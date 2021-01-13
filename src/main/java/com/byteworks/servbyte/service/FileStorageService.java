package com.byteworks.servbyte.service;

import com.byteworks.servbyte.config.AppConfig;
import com.byteworks.servbyte.request.SignUpRequest;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;


@Service
@Data
public class FileStorageService {

    private final AppConfig config;


    private String storeAttachment(SignUpRequest vo, Function<Object, String> function)
            throws IllegalStateException, IOException {
        MultipartFile file = vo.getLogo();
        String filePath = null;
        if (file != null) {
            String attachmentDir = function.apply(vo.getOwnerPath());
            filePath = String.format("%s/%s", attachmentDir, file.getOriginalFilename());
            new File(attachmentDir).mkdirs();
            file.transferTo(new File(filePath));
        }
        return filePath;
    }


    public String storeFileToOwnerPath(SignUpRequest vo) throws IllegalStateException, IOException {
        return storeAttachment(vo, path -> String.format("%s/%s", config.getUploadConfig().getBase(), path));
    }

}
