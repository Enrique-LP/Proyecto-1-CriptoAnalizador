import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The {@code Validator} class provides utility methods to validate keys, check file existence,
 * and validate file paths.
 */

public class Validator {

    /**
     * Checks if the provided key is within a valid range based on the alphabet size.
     *
     * @param key the key to validate
     * @param alphabetSize the size of the alphabet that the key should be validated against
     * @return {@code true} if the key is within the valid range, {@code false} otherwise
     */

    public boolean isValidKey(int key, int alphabetSize) {
        return key >= 0 && key < alphabetSize;
    }

    /**
     * Checks if the specified file exists and is not a directory.
     *
     * @param filePath the path to the file to check
     * @return {@code true} if the file exists and is not a directory, {@code false} otherwise
     */

    public boolean isFileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path) && !Files.isDirectory(path);
    }

    /**
     * Validates if the given file path follows a standard naming pattern and is not null or empty.
     *
     * @param filePath the file path to validate
     * @return {@code true} if the file path is valid, {@code false} otherwise
     */
    public boolean isFileValid(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        String fileName = Paths.get(filePath).getFileName().toString();
        String pattern = "^[a-zA-Z0-9._\\- ]+$";
        return fileName.matches(pattern);
    }
}
