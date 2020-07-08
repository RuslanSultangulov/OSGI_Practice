package ru.sultangulov.task5.vedomosti_handler.implementation;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import ru.sultangulov.task5.util.HandlersUtility;
import ru.sultangulov.task5.util.TitleHandler;

@Component(name = "VedomostiXMLHandler", immediate = true,
        property = {
                "service.name=VedomostiXMLHandler",
        },
        service = TitleHandler.class
)
public class VedomostiXMLHandler implements TitleHandler {

    public VedomostiXMLHandler() {

    }

    public Map<String, Integer> parse() {
        Map<String, Integer> hashMapOfWords = new HashMap();
        try {
            URL url = new URL("https://www.vedomosti.ru/newspaper/out/rss.xml");
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            NodeList itemList = doc.getElementsByTagName("item");
            for (int i = 0; i < itemList.getLength(); i++) {
                Node item = itemList.item(i);
                Element element = (Element)item;
                String title = element
                        .getElementsByTagName("title")
                        .item(0)
                        .getTextContent();
                HandlersUtility.putWordsInMap(hashMapOfWords, title);
            }

        } catch (Exception e) {
            System.err.println("Exception at VedomostiXMLHandler.parse() with message: " + e.getMessage());
            e.printStackTrace();
        }
        return hashMapOfWords;
    }

    public String getSiteName() {
        return "Vedomosti";
    }
}
