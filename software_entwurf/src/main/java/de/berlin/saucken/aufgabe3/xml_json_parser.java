package de.berlin.saucken.aufgabe3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class xml_json_parser {

    public static void prettyPrint(String filename) {

        String fileextension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            fileextension = filename.substring(i + 1);
        }

        if (fileextension.equals("xml") || fileextension.equals("json"))
            try {
                File file = new File(filename);
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuffer stringBuffer = new StringBuffer();
                String line;
                boolean firstlines = true;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer = new StringBuffer();
                    stringBuffer.append(line);
                    if (!firstlines)
                        if (fileextension.equals("xml"))
                            System.out.println(cleanxml(stringBuffer.toString()));
                        else
                            System.out.println(cleanjson(stringBuffer.toString()));
                    firstlines = false;
                }
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        else
            System.out.println("wrong file type - use with xml or json only");
    }

    private static String cleanjson(String line) {
        String result = "";

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            switch (c) {
                case '{':
                    break;
                case '}':
                    break;
                case '"':
                    break;
                case ',':
                    break;
                default:
                    result += c;
            }
        }

        return result;
    }

    private static String cleanxml(String line) {
        String result = "";
        String newline = System.getProperty("line.separator");
        boolean opentag = true;
        boolean closetag = false;
        boolean tagopen = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            switch (c) {
                case '<':
                    if (!opentag || line.charAt(i + 1) == '/') closetag = true;
                    tagopen = true;
                    break;
                case '>':
                    if (opentag && !closetag && (i + 1) < line.length()) result += ": ";
                    opentag = false;
                    tagopen = false;
                    break;
                case ' ':
                    if (tagopen) result = result + newline + "  ";
                    else result += c;
                    break;
                default:
                    if (!closetag) result += c;
            }
        }
        return result;
    }
}
