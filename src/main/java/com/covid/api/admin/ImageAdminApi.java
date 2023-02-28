package com.covid.api.admin;


import com.covid.model.request.UploadDTO;
import com.covid.utils.ImageUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/images")
public class ImageAdminApi {

    ImageUtils imageUtils;

    public ImageAdminApi(ImageUtils imageUtils) {
        this.imageUtils = imageUtils;
    }

    @PostMapping("/upload")
    public String updateImage(@ModelAttribute UploadDTO model) {
        return imageUtils.save(model.getFile());
    }
}
