 /**
* Execution: java Caesar encrypt message key
*            java Caesar decrypt message key
*            java Caesar crack originalText englishText
*
* Program Description: This code will either encrypt, decrypt or crack a given text
* The encrypt program shifts values in a string in regards to a particular key and 
* decrypt does the opposite. The crack function will crack a given string and Output
* a set that's closest to the english language.
*/

public class Caesar {
    
    /*
    * Description: converts a string to a symbol array,
    *              where each element of the array is an
    *              integer encoding of the corresponding
    *              element of the string.
    * Input:  the message text to be converted
    * Output: integer encoding of the message
    */
    public static int[] stringToSymbolArray(String str) {
        // Turn the string to all capitals and declare a new array
        str = str.toUpperCase();
        int[] symbolArray = new int[str.length()]; // Length of string = array
        // Create for loop to turn all string values and convert to array elements
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            symbolArray[i] = (int) letter - 'A'; // Values are between 0-25
        }
        return symbolArray;
    }
    
    /*
    * Description: converts an array of symbols to a string,
    *              where each element of the array is an
    *              integer encoding of the corresponding
    *              element of the string.
    * Input:  integer encoding of the message
    * Output: the message text
    */
    public static String symbolArrayToString(int[] symbols) {
        // Declare output string variable 
        String str = "";
        // Create for loop for converting each element in array to a string value
        for (int i = 0; i < symbols.length; i++) {
            str += (char) (symbols[i] + 'A');
        }
        // System.out.println(str);
        return str;
    }
    
    /*
    * Description: converts an integar value to a shifted integar value using the 
                   integar value of offset, the only integars shifted are ones that 
                   correspond to letter values. 
    * Input:  integer value of letter and integar of how much to shift by
    * Output: integar value shifted
    */
    public static int shift(int symbol, int offset) {
        // Declare the output integar shift
        int shift = 0;
        // Create if statement to determine that the shift value should be
        if (!((symbol >= 0) && (symbol <= 25))) { // Non characters are not shifted
            shift = symbol;
          // If shift value is above Z then it wraps around back to A  
        } else if ((symbol + offset) > 25) { 
            shift = symbol + offset;
            shift = shift % 26;
        } else {
            shift = symbol + offset;
        }
        // System.out.println(shift);
        return shift;
    }
    
    /*
    * Description: converts an integar value to an unshifted integar value using the 
                   integar value of offset, the only integars unshifted are ones 
                   that correspond to letter values. 
    * Input:  integer value of letter and integar of how much to unshift by
    * Output: integar value unshifted
    */
    public static int unshift(int symbol, int offset) {
        // Declare the unshift integar output variable
        int unshift = 0;
        // Create if statement to determine what happens to the unshift value
        if (!((symbol >= 0) && (symbol <= 25))) { //Non characters are not unshifted
            unshift = symbol;
        } else if ((symbol - offset) < 0) { // Wrap around to Z if value is below A
            unshift = symbol - offset;
            unshift = unshift + 26;
        } else {
            unshift = symbol - offset;
        }
        // System.out.println(unshift);
        return unshift;
    }
    
    /*
    * Description: converts a string message to an encrypted message using the 
                   integar value of key, how much the encryption changes by depends
                   on the integar value of the key.
    * Input:  string message and integar of how much to shift by (key)
    * Output: string value that's encrypted
    */
    public static String encrypt(String message, int key) {
        // Declare and Initialize the encrypt output variable and convert message to 
        // an array 
        String encrypt = "";
        int[] numbersFromString = stringToSymbolArray(message);
        // Create for loop to determine each new shifted element in the array
        for (int i = 0; i < numbersFromString.length; i++) {
            numbersFromString[i] = shift(numbersFromString[i], key);
        }
        // System.out.println(encrypt);
        // Convert the array to a string and set equal to encrypt
        encrypt = symbolArrayToString(numbersFromString);
        return encrypt;
    }
    
    /*
    * Description: converts a string message to an decrypted message using the 
                   integar value of key, how much the decryption changes by depends
                   on the integar value of the key.
    * Input:  string cipher and integar of how much to unshift by (key)
    * Output: string decrypt that's decrypted
    */
    public static String decrypt(String cipher, int key) {
        // Declare and Initialize the string variable output and convert cipher 
        // string to array
        String decrypt = "";
        int[] numbersFromString = stringToSymbolArray(cipher);
        // Create for loop to determine each new unshifted element in the array
        for (int i = 0; i < numbersFromString.length; i++) {
            numbersFromString[i] = unshift(numbersFromString[i], key);
        }
        // System.out.println(decrypt);
        // Convert the array to a string and set equal to decrypt
        decrypt = symbolArrayToString(numbersFromString);
        return decrypt;
    }
    
    /*
    * Description: converts the frequency of letters in the english language, takes
                   the filename and sets a new double array equal to that file
    * Input:  String Filename
    * Output: Double Array of the frequencies
    */
    public static double[] getDictionaryFrequencies(String filename) {
        // Declare and set length for the frequency array
        double[] frequency = new double[26];
        In inStream = new In(filename); // Read values in filename
        // Create for loop for reading each value in the filename and converting
        // to an element in the array
        for (int i = 0; i < 26; i++) {
            frequency[i] = inStream.readDouble();
        }
        return frequency;
    }
    
    /*
    * Description: determines the frequency of integars in a given integar array
                   using for loops and if statements and sets a new double array
                   equal to those frequencies
    * Input:  Integar array symbols
    * Output: Double array of the frequency of each letter
    */
    public static double[] findFrequencies(int[] symbols) {
        // Declare and set length for the textFrequency array
        double [] textFrequency = new double[26];
        // Create for loop for running through each number which corresponds to a
        // letter
        for (int i = 0; i < symbols.length; i++) {
            // Create nested for loop to check number of times each number appears
            for (int j = 0; j < 26; j++) {
                // Create if statement to change textFrequency value if similar 
                // number is in the array
                if (symbols[i] == j) {
                    textFrequency[j] += 1;
                }
            }
        }
        
        // Divide each element in array by the length of original array
        for (int i = 0; i < 26; i++) {
            textFrequency[i] = textFrequency[i] / symbols.length;
        }
        return textFrequency;
    }
    
    /*
    * Description: finds score of the given double array in comparision to the
                   english array, the lower the score the most similar it is to
                   the english language
    * Input:  Double array english and currentFreqs
    * Output: Double array scoreFrequencies of the score for each element
    */
    public static double scoreFrequencies(double[] english, double[] currentFreqs) {
        // Declare and initialize the scoreFrequencies output
        double scoreFrequencies = 0.0;
        // Creates for loop to run through each element and find the score
        for (int i = 0; i < english.length; i++) {
            // The score should be positive so use absolute value
            scoreFrequencies += Math.abs(currentFreqs[i] - english[i]);
        }
        return scoreFrequencies;
    }
    
    /*
    * Description: converts the originalText to a new text that is most similar to
                   the english language, this is determined by the lowestScore
    * Input:  String originalText and String englishText
    * Output: String crack of the final text
    */
    public static String crack(String originalText, String englishText) {
        // Declare and Initialize the key, crack message, lowestScore, and english
        int key = 0;
        String crack = "";
        double lowestScore = 100.0;
        // Call getDictionaryFrequencies to get the values from the file
        double[] english = getDictionaryFrequencies(englishText);
        // Create a for loop to find and determine the lowest score
        for (int i = 0; i < 26; i++) {
            String decryptText = decrypt(originalText, i);
            int[] encryptArray = stringToSymbolArray(decryptText);
            double score = scoreFrequencies(english, findFrequencies(encryptArray));
            // Create an if statement to set the lowestScore equal to score and find
            // the key value if the score is the lowest
            if (score < lowestScore) {
                lowestScore = score;
                key = i;
            }
        }
        
        // Find crack by decrypting the message using the originalText and key stored
        crack = decrypt(originalText, key);
        return crack;
    }
    
    public static void main(String[] args) {
        // Note: these are all tests
        // symbolArrayToString(stringToSymbolArray("CONSUL"));
        // unshift(shift(25, 1), 1);
        // encrypt("ET TU, BRUTE?", 6);
        // decrypt("KZ ZA, HXAZK?", 6);

        // Declare the command line arguments
        String functionToRun = args[0];
        String filename = args[1];
        String keyCharacter = args[2];
        // Set the integar key value to the string converted - the value of A
        int key = keyCharacter.charAt(0) - 'A';
        // Read all values from the file
        In inStream = new In(filename);
        String message = inStream.readAll();

        // Create if statements for when to run each program
        if (functionToRun.equals("encrypt")) {
            String encryptedMessage = encrypt(message, key);
            System.out.println(encryptedMessage);
        } else if (functionToRun.equals("decrypt")) {
            // String cypher = message;
            String decryptedMessage = decrypt(message, key);
            System.out.println(decryptedMessage);
          // Note: these were all my tests
        } else if (functionToRun.equals("crack")) {
            // Set the third inputed value as the englishText String
            String englishText = args[2];
            String newMessage = crack(message, englishText);
            System.out.println(newMessage);
        }
        
    }
   
}
