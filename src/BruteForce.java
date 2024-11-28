import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

/**
 * The {@code BruteForce} class provides methods to perform a brute force attack
 * on a Caesar cipher-encrypted text to attempt to decrypt it without knowing the key.
 */

public class BruteForce {

    private CaesarCipher cipher;
    private Set<String> dictionary;

    /**
     * Constructs a {@code BruteForce} object with the specified {@code CaesarCipher}.
     *
     * @param cipher the {@code CaesarCipher} object used for decryption
     */

    public BruteForce(CaesarCipher cipher) {
        this.cipher = cipher;
        this.dictionary = loadDictionary();
    }

    /**
     * Attempts to brute force decrypt the provided ciphertext using all possible keys.
     * The best decryption result is determined by scoring the decrypted text.
     *
     * @param ciphertext the encrypted text to be brute-forced
     * @return the best plaintext result after attempting decryption with all possible keys
     */
    public String bruteForce(String ciphertext) {
        String bestPlaintext = "";
        int bestKey = -1;
        int bestScore = -1;

        for (int key = 0; key < cipher.getAlphabetLength(); key++) {
            String decrypted = cipher.decrypt(ciphertext, key);
            int score = scoreText(decrypted);

            System.out.println("Key: " + key + ", Decrypted: " + decrypted + ", Score: " + score);

            if (score > bestScore) {
                bestScore = score;
                bestPlaintext = decrypted;
                bestKey = key;
            }
        }

        System.out.println("Best key found: " + bestKey + " with score: " + bestScore);
        return bestPlaintext;
    }

    /**
     * Scores the provided text based on the number of valid words found in the dictionary.
     * A higher score indicates a more readable and valid plaintext.
     *
     * @param text the text to be scored
     * @return the score of the text based on dictionary matches and structure
     */

    private int scoreText(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        int validWords = 0;
        int totalScore = 0;


        // Verificar estructura de oraciones
        if (text.matches(".*[A-Z][a-z]+.*") && // Comienza con mayúscula
                text.contains(".") &&  // Tiene puntos
                words.length > 3) {    // Tiene más de 3 palabras

        }


        for (String word : words) {
            // Limpiar caracteres especiales y convertir a minúsculas
            word = word.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ]", "").toLowerCase();

            if (!word.isEmpty()) {
                if (dictionary.contains(word) || COMMON_SHORT_WORDS.contains(word)) {
                    validWords++;

                    // Dar más puntos según la longitud de la palabra
                    if (dictionary.contains(word)) {
                        totalScore += word.length() * 2; // Más peso a palabras del diccionario
                    }

                    // Bonus adicional para palabras cortas comunes
                    if (COMMON_SHORT_WORDS.contains(word)) {
                        totalScore += 3; // Bonus para palabras cortas
                    }
                }
            }
        }

        return totalScore;
    }

    /**
     * Loads a dictionary of common words to be used for scoring decrypted text.
     *
     * @return a {@code Set} of strings containing common dictionary words
     */

    private Set<String> loadDictionary() {
        // Carga un diccionario más completo desde un archivo o base de datos
        // Por ejemplo, puedes utilizar el diccionario de la Real Academia Española (RAE)
        String[] words = {
                "de","del","la","el","un","una","los","las","y","o","no",
                "que","en","es","lo","lo","por","para","con","un","una",
                "yo","tu","el","ella","nosotros","ellos","ellas","me","te","lo","la","los","las","y","o","no",
                "este", "es",  "mensaje",  "prueba",
                "ser","tener","hacer","decir","ir","venir","ver","dar","tomar",
                "muy","bien","mal","poco","mucho","poca","mucha","pocos","muchos","poca","mucha",
                "rápido","lento","alto","bajo","grande","pequeño","grande","pequeño","grande","pequeño",
                "casa", "el", "perro", "la", "gata",
                "hombre","mujer","niño","niña","niños","niñas","hombres","mujeres","niños","niñas",
                "manzana","pera","uva","manzanas","peras","uvas","manzana","pera","uva","manzanas","peras",
                "mundo","cielo","tierra","agua","fuego","aire","viento","lluvia","nieve","sol","luna","estrellas","planetas",
                "rojo","azul","verde","amarillo","morado","rosa","negro","blanco","gris","naranja",
                "rápido","lento","alto","bajo","grande","pequeño",
                "aquí","allí","dónde","cuándo","porqué","cómo","cuánto","cuántos","cuántas","cuál",
                "ahora","entonces","también","así","también",
                "luego","siempre","estas",
                "hola","adios","buenos","días","noches","buenas","tardes","buenas","noches",
                "mensaje","prueba","cifrado","descifrado","criptografía","criptoanálisis","criptografía","criptoanálisis"
                
                


                // Agrega más palabras al diccionario
        };
        return new HashSet<>(Arrays.asList(words));
    }

    /**
     * A list of common short words to help score text, especially when analyzing small or common phrases.
     */

    private static final List<String> COMMON_SHORT_WORDS = Arrays.asList(
            "the", "be", "to", "of", "and", "a", "in", "that", "have", "i",
            "it", "for", "not", "on", "with", "he", "as", "you", "do", "at",
            "de", "la", "el", "un", "una", "los", "las", "y", "o", "no"
    );
}
