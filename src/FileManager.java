import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * The {@code FileManager} class provides methods to read from and write to files,
 * using a {@code Validator} to ensure file validity, and a {@code Scanner} for user interaction.
 */

public class FileManager {
    private Validator validator;
    private Scanner scanner;

    /**
     * Constructs a {@code FileManager} object with the specified {@code Validator}.
     * Uses a default {@code Scanner} for user interaction.
     *
     * @param validator the {@code Validator} object used to validate file paths
     */

    public FileManager(Validator validator) {
        this(validator, new Scanner(System.in));
    }

    /**
     * Constructs a {@code FileManager} object with the specified {@code Validator} and {@code Scanner}.
     *
     * @param validator the {@code Validator} object used to validate file paths
     * @param scanner the {@code Scanner} object used for user input
     */
    public FileManager(Validator validator, Scanner scanner) {
        this.validator = validator;
        this.scanner = scanner;
    }

    /**
     * Writes content to a file at the specified file path. If the file does not exist,
     * prompts the user for permission to create it.
     *
     * @param filePath the path of the file to write to
     * @param content the content to be written to the file
     * @throws IOException if an I/O error occurs while writing to the file
     * @throws IllegalArgumentException if the file path is not valid
     */
    public void writeFile(String filePath, String content) throws IOException {
        if (validator.isFileValid(filePath)) {
            if (!validator.isFileExists(filePath)) {
                System.out.println("El archivo no existe. ¿Desea crearlo? (s/n)");
                String response = scanner.nextLine().trim().toLowerCase();
                if (response.equals("s")) {
                    Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                    System.out.println("Archivo creado y escrito correctamente.");
                } else {
                    System.out.println("El archivo no existe y no se ha creado.");
                    return;
                }
            } else {
                // Si el archivo ya existe, sobrescríbelo
                Files.write(Paths.get(filePath), content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Archivo sobrescrito correctamente.");
            }
        } else {
            throw new IllegalArgumentException("El nombre del archivo no es válido");
        }
    }

    /**
     * Reads the content of a file at the specified file path. If the file does not exist,
     * prompts the user for permission to create it.
     *
     * @param filePath the path of the file to read
     * @return the content of the file as a string
     * @throws IOException if an I/O error occurs while reading the file
     * @throws IllegalArgumentException if the file path is not valid or the file does not exist
     */

    public String readFile(String filePath) throws IOException {
        // Validar si el nombre del archivo es válido
        if (!validator.isFileValid(filePath)) {
            throw new IllegalArgumentException("El nombre del archivo no es válido");
        }
        // Validar si el archivo existe, y si no, preguntar al usuario si desea crearlo
        if (!validator.isFileExists(filePath)) {
            System.out.println("El archivo no existe. ¿Desea crearlo? (s/n)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("s")) {
                Files.write(Paths.get(filePath), "".getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
                System.out.println("Archivo creado, pero está vacío.");
                return ""; // Retornar vacío para que la clase Main lo maneje si es necesario
            } else {
                throw new IllegalArgumentException("El archivo no existe y no se ha creado.");
            }
        }
        // Leer el contenido del archivo
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
        if (fileBytes.length == 0) {
            System.out.println("El archivo está vacío. Por favor, agregue contenido antes de continuar.");
            return "";
        }
        return new String(fileBytes, StandardCharsets.UTF_8);
    }

    /**
     * Closes the {@code Scanner} used for user interaction.
     * Note: The scanner should not be closed here if it is shared with other classes.
     */

    public void closeScanner() {
        // El scanner no debe ser cerrado aquí ya que es compartido con MainApp
    }
}
