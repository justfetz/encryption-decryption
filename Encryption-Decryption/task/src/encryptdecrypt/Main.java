package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

class Converter {
    private ConvertingStrategy strategy;

    public Converter(ConvertingStrategy strategy) {
        this.strategy = strategy;
    }

    public String getResult(String text, int key) {
        return this.strategy.getResult(text, key);
    }
}

interface ConvertingStrategy {
    String getResult(String text, int key);
}

class Encoder implements ConvertingStrategy {

    @Override
    public String getResult(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                char newChar = (char) ((ch + key - 65) % 26 + 65);
                result.append(newChar);
            } else if (Character.isLowerCase(ch)) {
                char newChar = (char) ((ch + key - 97) % 26 + 97);
                result.append(newChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}

class Decoder implements ConvertingStrategy {

    @Override
    public String getResult(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                char newChar = (char) ((ch - key - 65 + 26) % 26 + 65);
                result.append(newChar);
            } else if (Character.isLowerCase(ch)) {
                char newChar = (char) ((ch - key - 97 + 26) % 26 + 97);
                result.append(newChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}

class Encryption implements ConvertingStrategy {

    @Override
    public String getResult(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char newChar = (char) ((ch + key) % 128);
            result.append(newChar);
        }
        return result.toString();
    }
}

class Decryption implements ConvertingStrategy {

    @Override
    public String getResult(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            char newChar = (char) ((ch - key + 128) % 128);
            result.append(newChar);
        }
        return result.toString();
    }
}

public class Main {
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void writeFile(String fileName, String data) throws IOException {
        try (FileWriter writer = new FileWriter(new File(fileName))) {
            writer.write(data);
        }
    }

    public static void main(String[] args) {
        Map<String, String> arguments = parseArguments(args);
        String mode = arguments.getOrDefault("-mode", "enc");
        int key = Integer.parseInt(arguments.getOrDefault("-key", "0"));
        String alg = arguments.getOrDefault("-alg", "shift");
        String data = arguments.getOrDefault("-data", "");
        String inFile = arguments.get("-in");
        String outFile = arguments.get("-out");

        if (data.isEmpty() && inFile != null) {
            try {
                data = readFileAsString(inFile);
            } catch (IOException e) {
                System.out.println("Error reading input file: " + e.getMessage());
                return;
            }
        }

        Converter converter = null;
        if ("enc".equals(mode)) {
            if ("unicode".equals(alg)) {
                converter = new Converter(new Encryption());
            } else {
                converter = new Converter(new Encoder());
            }
        } else if ("dec".equals(mode)) {
            if ("unicode".equals(alg)) {
                converter = new Converter(new Decryption());
            } else {
                converter = new Converter(new Decoder());
            }
        }

        String result = converter.getResult(data, key);

        if (outFile != null) {
            try {
                writeFile(outFile, result);
            } catch (IOException e) {
                System.out.println("Error writing to output file: " + e.getMessage());
            }
        } else {
            System.out.println(result);
        }
    }

    private static Map<String, String> parseArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            arguments.put(args[i], args[i + 1]);
        }
        return arguments;
    }
}

/**
package encryptdecrypt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Converter {
    private ConvertingStrategy strategy;

    public Converter(ConvertingStrategy strategy) {
        this.strategy = strategy;
    }

    public StringBuffer getResult(String text, int s) {
        return this.strategy.getResult(text, s);
    }
}

interface ConvertingStrategy {
        StringBuffer getResult(String text, int s);
        }

class Encoder implements ConvertingStrategy {

    @Override
    public StringBuffer getResult(String text, int s)
    {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++)
        if(text.charAt(i) != ' ') {
            {
                if (Character.isUpperCase(text.charAt(i))) {
                    char ch = (char) (((int) text.charAt(i) +
                            s - 65) % 26 + 65);
                    result.append(ch);
                } else {
                    char ch = (char) (((int) text.charAt(i) +
                            s - 97) % 26 + 97);
                    result.append(ch);
                }
            }
        } else {
            result.append(text.charAt(i));
        }
        return result;
    }
}

class Decoder implements ConvertingStrategy {

    @Override

    //lower case and upper case
    public StringBuffer getResult(String text, int s) {
        final String alph = "abcdefghijklmnopqrstuvwxyz";
        final String upperCaseAlph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        text = text.toLowerCase();
        StringBuffer plainT = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                int mappingV = alph.indexOf(text.charAt(i));
                int deVal = (mappingV - s) % 26;
                if (deVal < 0)
                {
                    deVal = alph.length() + deVal;
                }
                char Val = alph.charAt(deVal);
                plainT.append(Val);
            } else {
                plainT.append(' ');
            }
        }
        return plainT;
    }
}
class Encryption implements ConvertingStrategy {

    @Override
    public StringBuffer getResult(String text, int s)
    {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++)

                {
                    if (Character.isUpperCase(text.charAt(i))) {
                        char ch = (char) (((int) text.charAt(i) +
                                s) % 128);
                        result.append(ch);
                    } else {
                        char ch = (char) (((int) text.charAt(i) +
                                s) % 128);
                        result.append(ch);
                    }
                }

        return result;
    }
}

class Decryption implements ConvertingStrategy {

    @Override
    public StringBuffer getResult(String text, int s)
    {
        StringBuffer result = new StringBuffer();

        for (int i=0; i<text.length(); i++)

                {
                    if (Character.isUpperCase(text.charAt(i))) {
                        char ch = (char) (((int) text.charAt(i) -
                                s) % 128);
                        result.append(ch);
                    } else {
                        char ch = (char) (((int) text.charAt(i) -
                                s) % 128);
                        result.append(ch);
                    }
                }

        return result;
    }
}

public class Main {
    /*Rules
    If there is no -mode, the program should work in enc mode.
    If there is no -key, the program should consider that key = 0.
    If there is no -data, the program should assume that the data is an empty string.
    Arguments: -mode enc -key 5 -in in.txt -out output.txt
    add -alg argument, can be 'shift' (5) or 'unicode'


    public static StringBuilder shift(String text, int s) {


        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + s) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result;
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static void main(String[] args) throws IOException {

        String type = "";
        int shift=0;
        String textToTranslate = "";
        String inLocation = "input.txt";
        String outLocation = "output.txt";
        String current = new java.io.File( "." ).getCanonicalPath();
        String alg = "";
        
        for(int i = 0; i<args.length; i++) {
            if(args[i].equals("-mode")) {
                type = args[i+1];
            }
        }

        for(int n = 0; n< args.length; n++) {
            if(args[n].equals("-alg")) {
                alg = args[n+1];
            }
        }

        for(int j =0;j<args.length;j++) {
            if(args[j].equals("-key")){
                shift = Integer.parseInt(args[j+1]);
            }
        }
        for(int k=0;k<args.length;k++) {
            if(args[k].equals("-data")) {
                textToTranslate = args[k+1];
            }
        }
        // file to read
        for(int l=0;l<args.length;l++) {
            if(args[l].equals("-in")) {
                inLocation = args[l+1];
            }
        }
        // file to write
        for(int m=0;m>args.length;m++) {
            if(args[m].equals("-out")) {
                outLocation = args[m+1];

            } else {
                outLocation = "console";
            }

        }

        textToTranslate = readFileAsString(inLocation);

        Converter converter = null;

        switch (type) {
            case "enc":
                if (alg.equals("unicode")) {
                    converter = new Converter(new Encryption());
                } else if (alg.equals("shift")) {
                    converter = new Converter(new Encoder());
                }
                break;
            case "dec":
                if (alg.equals("unicode")) {
                    converter = new Converter(new Decryption());
                } else if (alg.equals("shift")) {
                    converter = new Converter(new Decoder());
                }
                break;
            default:
                break;
        }

        //add logic for files
        if (alg.equals("unicode")) {
            if (type.equals("enc") && !outLocation.equals("console")) {
                File file = new File("output.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    System.out.println(converter.getResult(textToTranslate, shift).toString());
                    writer.write(converter.getResult(textToTranslate, shift).toString());
                    writer.close();
                } catch (IOException e) {
                    System.out.printf("An Error exception occurs %s", e.getMessage());
                }

                //System.out.println(encrypt(textToTranslate, shift));
            } else if (type.equals("dec") && !outLocation.equals("console")) {
                File file = new File(current + "\\" + "output.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(converter.getResult(textToTranslate, shift).toString());
                } catch (IOException e) {
                    System.out.printf("An Error exception occurs %s", e.getMessage());
                }
                //System.out.println(decrypt(textToTranslate, shift));
            }

        } else if (alg.equals("shift")) {
            if (type.equals("enc") && !outLocation.equals("console")) {
                File file = new File("output.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(converter.getResult(textToTranslate, shift).toString());
                    writer.close();
                } catch (IOException e) {
                    System.out.printf("An Error exception occurs %s", e.getMessage());
                }

                //System.out.println(encrypt(textToTranslate, shift));
            } else if (type.equals("dec") && !outLocation.equals("console")) {
                File file = new File(current + "\\" + "output.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(converter.getResult(textToTranslate, shift).toString());
                } catch (IOException e) {
                    System.out.printf("An Error exception occurs %s", e.getMessage());
                }
        //test notes
            }
        }

      }
    }
*/

