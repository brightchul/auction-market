package io.youngwon.app.web;

;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/file")
public class FilesApiController {

    @GetMapping(value="{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] downloadFile(
            @PathVariable String filename,
            HttpServletRequest request,
            HttpServletResponse response) {

        try {
            return java.nio.file.Files.readAllBytes(Paths.get("uploads", filename));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(filename);
        return null;

    }


}
