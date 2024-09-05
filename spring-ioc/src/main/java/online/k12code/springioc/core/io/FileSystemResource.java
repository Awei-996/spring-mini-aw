package online.k12code.springioc.core.io;

import online.k12code.springioc.BeansException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 文件资源
 *
 * @author Carl
 * @since 1.0.0
 */
public class FileSystemResource implements Resource {

    private final String filePath;

    public FileSystemResource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws IOException {

        Path path = new File(this.filePath).toPath();

        try {
            return Files.newInputStream(path);
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
