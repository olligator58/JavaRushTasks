package com.javarush.task.task33.task3309;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        StringWriter writer = new StringWriter();
        StringBuilder result = new StringBuilder();
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, writer);
            String tag = String.format("<%s>", tagName);
            String emptyTag = String.format("<%s/>", tagName);
            String commentText = String.format("<!--%s-->", comment);
            try (BufferedReader reader = new BufferedReader(new StringReader(writer.toString()));) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!result.toString().isEmpty()) {
                        result.append("\n");
                    }
                    if (line.trim().startsWith(tag) || line.trim().startsWith(emptyTag)) {
                        result.append(commentText);
                        result.append("\n");
                    }
                    result.append(line.trim());
                }
            } catch (IOException ignore) {
            }
        } catch (JAXBException ignore) {
        }
        return result.toString();
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.print(toXmlWithComment(a, "second", "it's a comment"));
    }

    @XmlRootElement(name = "first")
    public static class A {
        @XmlElement(name = "second")
        public List<String> list = new ArrayList<>(Arrays.asList("some string", "some string", "<![CDATA[need CDATA because of < and >]]>", ""));
    }
}
