package framework.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class FileUtils {
    private static final Path REPORT_ROOT = Path.of("reports");

    private FileUtils() {
    }

    public static Path createReportPath(String directory, String fileName) {
        Path targetDir = REPORT_ROOT.resolve(directory);
        try {
            Files.createDirectories(targetDir);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create report directory: " + targetDir, e);
        }
        return targetDir.resolve(fileName);
    }

    public static void copyFile(Path source, Path target) throws IOException {
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }
}

