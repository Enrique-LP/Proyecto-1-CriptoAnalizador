
/**
 * The {@code CaesarCipher} class provides methods to encrypt and decrypt text using
 * the Caesar Cipher technique. The Caesar Cipher is a substitution cipher where each
 * character in the plaintext is shifted a certain number of places down or up the alphabet.
 */

public class CaesarCipher {
   
    private int shift;

    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                                             'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                                              ' ', '.', ',', ':', ';', '!', '?', '"', '\''};


    /**
     * Constructs a {@code CaesarCipher} object with the specified shift value.
     *
     * @param shift the number of positions to shift the characters
     */

    public CaesarCipher(int shift   ) {
        this.shift = shift;
    }

    /**
     * Finds the index of a character in the {@code ALPHABET} array.
     *
     * @param c the character to find
     * @return the index of the character if found, or {@code -1} if the character is not in the alphabet
     */

    public static int findIndex(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Encrypts a plaintext string using the Caesar cipher algorithm.
     *
     * @param plaintext the text to be encrypted
     * @param key the number of positions to shift the characters
     * @return the encrypted string
     */

    public static String encrypt(String plaintext, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : plaintext.toCharArray()) {
            int index = findIndex(character);
            if (index != -1) {
                // Aplicar el desplazamiento
                int encryptedIndex = (index + key) % ALPHABET.length;
                result.append(ALPHABET[encryptedIndex]);
            } else {
                // Manejar caracteres que no están en el alfabeto
                result.append(character);
            }
        }
        return result.toString();
    }


    /**
     * Decrypts a ciphertext string using the Caesar cipher algorithm.
     *
     * @param ciphertext the text to be decrypted
     * @param key the number of positions used in the original encryption
     * @return the decrypted string
     */
    public static String decrypt(String ciphertext, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : ciphertext.toCharArray()) {
            int index = findIndex(character);
            if (index != -1) {
                int decryptindex = (index - key + ALPHABET.length)%ALPHABET.length;
                result.append(ALPHABET[decryptindex]);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    /**
     * Gets the length of the {@code ALPHABET} array.
     *
     * @return the length of the alphabet
     */
    public int getAlphabetLength() {
        return ALPHABET.length;  // Cambiado de 26 a ALPHABET.length
    }

    /**
     * Gets the {@code ALPHABET} array.
     *
     * @return the alphabet array
     */
    public char[] getAlphabet() {
        return ALPHABET;
    }

    /**
     * Sets the shift value for the Caesar cipher.
     *
     * @param shift the number of positions to shift the characters
     */

    public void setShift(int shift) {
        this.shift = shift;
    }

}