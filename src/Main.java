import java.util.Scanner;
import java.io.IOException;

/**
 * The {@code Main} class provides the main user interface to interact with the Caesar Cipher, Brute Force,
 * and FileManager classes. It allows users to encrypt, decrypt, perform brute force decryption, and statistical analysis
 * on encrypted text.
 */

public class Main {
    private Scanner scanner;
    private Validator validator;
    private FileManager fileManager;
    private CaesarCipher caesarCipher;
    private BruteForce bruteForce;


    /**
     * Constructs a {@code Main} object and initializes all the components required for encryption and decryption operations.
     */

    public Main() {
        scanner = new Scanner(System.in);
        validator = new Validator();
        fileManager = new FileManager(validator, scanner);
        caesarCipher = new CaesarCipher(3);
        bruteForce = new BruteForce(caesarCipher);

    }

    /**
     * The main run method that handles user input and calls appropriate methods for different operations.
     * The user is given options to encrypt, decrypt, perform brute force decryption, or exit the application.
     */
    public void run() {
        try {
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine();
            String outputFilePath;
            int key;

            switch (option) {
                case 1:
                    System.out.println("Ingrese el nombre del archivo a cifrar:");
                    String inputFilePath = scanner.nextLine();
                    boolean validContent = false;
                    String plaintextContent = "";
                    while (!validContent) {
                        try {
                            plaintextContent = fileManager.readFile(inputFilePath);
                            if (plaintextContent.trim().isEmpty()) {
                                System.out.println("El archivo está vacío. Por favor, agregue contenido antes de continuar y presione Enter.");
                                scanner.nextLine();
                            } else {
                                validContent = true;
                            }
                        } catch (IOException e) {
                            System.out.println("Error de E/S: " + e.getMessage());
                            return;
                        }
                    }

                    System.out.println("Introduce la clave:");
                    while (true) {
                        try {
                            key = Integer.parseInt(scanner.nextLine().trim());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Clave inválida. Introduzca un número entero:");
                        }
                    }

                    System.out.println("Introduce el nombre del archivo de salida:");
                    outputFilePath = scanner.nextLine();
                    String encryptedText = caesarCipher.encrypt(plaintextContent, key);
                    try {
                        fileManager.writeFile(outputFilePath, encryptedText);
                        System.out.println("Archivo cifrado y guardado en " + outputFilePath);
                        createPropertiesFile(inputFilePath, outputFilePath, key);
                    } catch (Exception e) {
                        System.out.println("Error al escribir el archivo de salida: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Ingrese el nombre del archivo cifrado:");
                    String encryptedFilePath = scanner.nextLine();
                    try {
                        String encryptedContent = fileManager.readFile(encryptedFilePath);
                        System.out.println("Introduce la clave:");
                        while (true) {
                            try {
                                key = Integer.parseInt(scanner.nextLine().trim());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Clave inválida. Introduzca un número entero:");
                            }
                        }
                        System.out.println("Introduce el nombre del archivo de salida:");
                        outputFilePath = scanner.nextLine();
                        String decryptedContent = caesarCipher.decrypt(encryptedContent, key);
                        fileManager.writeFile(outputFilePath, decryptedContent);
                        System.out.println("Archivo descifrado y guardado en " + outputFilePath);
                    } catch (IOException e) {
                        System.out.println("Error de E/S: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al descifrar el archivo: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Ingrese el nombre del archivo cifrado:");
                    String ciphertext = scanner.nextLine();
                    try {
                        String encryptedContent = fileManager.readFile(ciphertext);
                        System.out.println("Ingrese el nombre del archivo de salida:");
                        outputFilePath = scanner.nextLine();
                        String decryptedContent = bruteForce.bruteForce(encryptedContent);
                        fileManager.writeFile(outputFilePath, decryptedContent);
                        System.out.println("Archivo descifrado por fuerza bruta y guardado en " + outputFilePath);
                    } catch (IOException e) {
                        System.out.println("Error de E/S: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } finally {
            scanner.close();
        }
    }

    /**
     * Creates a properties file with information about the input file, output file, and encryption key.
     *
     * @param inputFilePath the path of the input file
     * @param outputFilePath the path of the output file
     * @param key the encryption key used
     */

    private void createPropertiesFile(String inputFilePath, String outputFilePath, int key) {
        String propertiesContent = "Ruta del archivo de entrada: " + inputFilePath + "\n" +
                "Ruta del archivo de salida: " + outputFilePath + "\n" +
                "Clave: " + key;
        try {
            fileManager.writeFile("files/properties.txt", propertiesContent);
            System.out.println("Archivo de propiedades creado: files/properties.txt");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo de propiedades: " + e.getMessage());
        }
    }

    /**
     * Prints the menu options for the user to interact with.
     */
    private void printMenu() {
        System.out.println("\n===========================================");
        System.out.println("🔐  ANALIZADOR DE CIFRADO CÉSAR  🔐");
        System.out.println("===========================================");
        System.out.println("1. 📝 Cifrar archivo");
        System.out.println("2. 🔓 Descifrar archivo con clave");
        System.out.println("3. 🔨 Descifrar archivo por fuerza bruta");
        System.out.println("0. 🚪 Salir");
        System.out.println("===========================================");
        System.out.print("Seleccione una opción: ");
    }
}
