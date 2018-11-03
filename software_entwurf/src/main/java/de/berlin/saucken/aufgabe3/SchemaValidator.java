package de.berlin.saucken.aufgabe3;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;



public class SchemaValidator {

    public static boolean validateXsd(final String xsd, final String xml) {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xml)));
        } catch (IOException | SAXException e) {
            String lineNumber = "";

            System.out.println("Validation error: " + e.toString());
            return false;
        }
        return true;
    }
}
