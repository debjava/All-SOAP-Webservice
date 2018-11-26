import java.io.FileInputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;


class StaxCursorTest {
    public static void main(String[] args) throws Exception {

        String filename = "config/calender.xml";

        XMLInputFactory xmlif = null;

        xmlif = XMLInputFactory.newInstance();
        xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,Boolean.FALSE);
        xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);

        try {
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,new FileInputStream(filename));
            int eventType = xmlr.getEventType();
            printStartDocument(xmlr);
            while (xmlr.hasNext()) {
                eventType = xmlr.next();
                printStartElement(xmlr);
                printEndElement(xmlr);
                printText(xmlr);
                printPIData(xmlr);
                printComment(xmlr);
            }
        } catch (XMLStreamException ex) {
            System.out.println(ex.getMessage());
            if (ex.getNestedException() != null) {
                ex.getNestedException()
                  .printStackTrace();
            }
        }
    }

    public static final String getEventTypeString(int eventType) {
        switch (eventType) {
        case XMLEvent.START_ELEMENT:
            return "START_ELEMENT";

        case XMLEvent.END_ELEMENT:
            return "END_ELEMENT";

        case XMLEvent.PROCESSING_INSTRUCTION:
            return "PROCESSING_INSTRUCTION";

        case XMLEvent.CHARACTERS:
            return "CHARACTERS";

        case XMLEvent.COMMENT:
            return "COMMENT";

        case XMLEvent.START_DOCUMENT:
            return "START_DOCUMENT";

        case XMLEvent.END_DOCUMENT:
            return "END_DOCUMENT";

        case XMLEvent.ENTITY_REFERENCE:
            return "ENTITY_REFERENCE";

        case XMLEvent.ATTRIBUTE:
            return "ATTRIBUTE";

        case XMLEvent.DTD:
            return "DTD";

        case XMLEvent.CDATA:
            return "CDATA";

        case XMLEvent.SPACE:
            return "SPACE";
        }

        return "UNKNOWN_EVENT_TYPE , " + eventType;
    }

    private static void printEventType(int eventType) {
        System.out.println("EVENT TYPE(" + eventType + ") = " + getEventTypeString(eventType));
    }

    private static void printStartDocument(XMLStreamReader xmlr) {
        if (xmlr.START_DOCUMENT == xmlr.getEventType()) {
            System.out.println(
                    "<?xml version=\"" + xmlr.getVersion() + "\""
                    + " encoding=\"" + xmlr.getCharacterEncodingScheme() + "\""
                    + "?>");
        }
    }

    private static void printComment(XMLStreamReader xmlr) {
        if (xmlr.getEventType() == xmlr.COMMENT) {
            System.out.print("<!--" + xmlr.getText() + "-->");
        }
    }

    private static void printText(XMLStreamReader xmlr) {
        if (xmlr.hasText()) {
            System.out.print(xmlr.getText());
        }
    }

    private static void printPIData(XMLStreamReader xmlr) {
        if (xmlr.getEventType() == XMLEvent.PROCESSING_INSTRUCTION) {
            System.out.print(
                    "<?" + xmlr.getPITarget() + " " + xmlr.getPIData() + "?>");
        }
    }

    private static void printStartElement(XMLStreamReader xmlr) {
        if (xmlr.isStartElement()) {
            System.out.print("<" + xmlr.getName().toString());
            printAttributes(xmlr);
            System.out.print(">");
        }
    }

    private static void printEndElement(XMLStreamReader xmlr) {
        if (xmlr.isEndElement()) {
            System.out.print("</" + xmlr.getName().toString() + ">");
        }
    }

    private static void printAttributes(XMLStreamReader xmlr) {
        int count = xmlr.getAttributeCount();

        if (count > 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
                System.out.print(xmlr.getAttributeName(i).toString());
                System.out.print("=");
                System.out.print("\"");
                System.out.print(xmlr.getAttributeValue(i));
                System.out.print("\"");
            }
        }

        count = xmlr.getNamespaceCount();

        if (count > 0) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
                System.out.print("xmlns");

                if (xmlr.getNamespacePrefix(i) != null) {
                    System.out.print(":" + xmlr.getNamespacePrefix(i));
                }

                System.out.print("=");
                System.out.print("\"");
                System.out.print(xmlr.getNamespaceURI(i));
                System.out.print("\"");
            }
        }
    }
}