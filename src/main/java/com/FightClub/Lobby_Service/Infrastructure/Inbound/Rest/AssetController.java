package com.FightClub.Lobby_Service.Infrastructure.Inbound.Rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/assets")
public class AssetController {

    @GetMapping("/character/{fileName:.+}")
    public ResponseEntity<Resource> getCharacterAsset(@PathVariable String fileName) throws IOException {
        if (!isValidAssetFile(fileName)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Resource resource = new ClassPathResource("assets/" + fileName);
            
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            MediaType mediaType = getMediaType(fileName);
            
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private boolean isValidAssetFile(String fileName) {
        return fileName.equals("IDLE.png") ||
               fileName.equals("RUN.png") ||
               fileName.equals("ATTACK 1.png") ||
               fileName.equals("HURT.png");
    }

    private MediaType getMediaType(String fileName) {
        if (fileName.endsWith(".png")) {
            return MediaType.IMAGE_PNG;
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return MediaType.IMAGE_JPEG;
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }
}
