package spn.services.task;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class SuccessfulResponse implements Question {
    @Override
    public Object answeredBy(Actor actor) {
        try {
            String response = SerenityRest.lastResponse().getBody().asString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(response.getBytes()));

            return doc.getElementsByTagName("errorCode").item(0).getTextContent();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SOAP response: " + e.getMessage(), e);
        }
    }
    public static Question<String> value() {
        return new SuccessfulResponse();
    }
}
