package de.berlin.saucken.aufgabe3;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Starter {

    public static void main(String[] args) {

        // VALIDATION OF XML AGAINGST XSD
        validateAllXml();

        // PRETTY-PRINT OF XML
        printXmlWithoutTags();

        // PRETTY-PRINT OF JSON
        printJsonWithoutTags();

    }

    public static void validateAllXml() {

        final Stopwatch swValidations = Stopwatch.createStarted();

        for (int i = 1; i < 15; i++) {

            final Stopwatch sw = Stopwatch.createStarted();
            System.out.println("####### Validation Nr. " + i + " #######");

            final String xsdLocation = "src/main/resources/" + i + ".xsd";

            final String xmlLocation = "src/main/resources/" + i + ".xml";

            if (SchemaValidator.validateXsd(xsdLocation, xmlLocation)) {
                System.out.println("Validation succeed. Time elapsed: " + sw.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");
            } else {
                System.out.println("Valdation failed. Time elapsed: " + sw.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");
            }

            System.out.println("--------------------------------------------");
        }

        System.out.println("Validations completed. Time elapsed: " + swValidations.elapsed(TimeUnit.MILLISECONDS) + " milliseconds");
    }

    public static void printXmlWithoutTags() {
        final Stopwatch swPrettyPrintXML = Stopwatch.createStarted();

        for (int i = 1; i < 15; i++) {

            final Stopwatch sw = Stopwatch.createStarted();
            System.out.println("####### Print Nr. " + i + " #######");

            final String xmlLocation = "src/main/resources/" + i + ".xml";

            xml_json_parser.prettyPrint(xmlLocation);

            System.out.println("--------------------------------------------");
        }

        System.out.println("Pretty-Print XML completed. Time elapsed: " + swPrettyPrintXML.elapsed(TimeUnit.MILLISECONDS));
    }

    public static void printJsonWithoutTags() {
        final Stopwatch swPrettyPrintJson = Stopwatch.createStarted();

        for (int i = 1; i < 15; i++) {

            final Stopwatch sw = Stopwatch.createStarted();
            System.out.println("####### Print Nr. " + i + " #######");

            final String jsonLocation = "src/main/resources/" + i + ".json";

            xml_json_parser.prettyPrint(jsonLocation);

            System.out.println("--------------------------------------------");
        }

        System.out.println("Pretty-Print JSON completed. Time elapsed: " + swPrettyPrintJson.elapsed(TimeUnit.MILLISECONDS));
    }
}
