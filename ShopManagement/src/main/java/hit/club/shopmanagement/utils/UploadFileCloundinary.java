package hit.club.shopmanagement.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Component
public class UploadFileCloundinary {

    private final Cloudinary cloudinary;

    public String getUrlFromFile(MultipartFile multipartFile) throws Exception {
        try {
            return cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap()).get("secure_url").toString();
        } catch (Exception e) {
            throw new Exception("The process get URL from file failed!");
        }
    }

    public String removeFileToUrl(String url) throws Exception {
        try {
            String publicId = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return "Remove file to url is successfully";
        } catch (Exception exception) {
            throw new Exception("Upload image failed");
        }
    }
}
