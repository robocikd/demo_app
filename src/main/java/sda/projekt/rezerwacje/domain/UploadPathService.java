package sda.projekt.rezerwacje.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;

@Service
@RequiredArgsConstructor
public class UploadPathService {

    private final ServletContext context;

    public File getFilePath(String modifiedFileName, String path) {
        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdir();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        File file = new File(modifiedFilePath);
        return file;
    }
}
