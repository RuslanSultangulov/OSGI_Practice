package ru.sultangulov.task5.command.implementation;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import ru.sultangulov.task5.command.TitleExplorer;
import ru.sultangulov.task5.util.HandlersUtility;
import ru.sultangulov.task5.util.TitleHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(
        property = {
                "osgi.command.scope=news",
                "osgi.command.function=stats"
        },
        service= TitleExplorer.class
)
public class TitleExplorerImpl implements TitleExplorer {

    private final List<TitleHandler> parsersList = new ArrayList<>();
    private final List<String> nameList = new ArrayList<>();

    public TitleExplorerImpl() {

    }

    @Reference(cardinality = ReferenceCardinality.MULTIPLE, service = TitleHandler.class)
    public void setHandler(TitleHandler handler) {
        parsersList.add(handler);
        nameList.add(handler.getSiteName());
    }


    public void stats() {
        System.out.println("Web Sites available for parsing:");
        for (String name: nameList) {
            System.out.println(name);
        }
        if (nameList.size() >= 2) {
            System.out.println("all");
        }
    }


    public void stats(String option) {
        if ((option.equals("all")) && (nameList.size() > 1)) {
            System.out.println("10 most common words in titles of available websites with length greater than "
                    + HandlersUtility.WORDS_LENGTH_LOWER_LIMIT + " characters");
            parseAll();
            return;
        }
        int parserIndex = nameList.indexOf(option);
        if (parserIndex != -1) {
            System.out.println("10 most common words in title of a Web Site " + option + " with length greater than "
                    + HandlersUtility.WORDS_LENGTH_LOWER_LIMIT + " characters");
            HandlersUtility.sortPrint(parsersList.get(parserIndex).parse());
        } else {
            System.out.println("Invalid argument.");
        }
    }

    void parseAll() {
        List<Map<String, Integer>> listOfMaps = new ArrayList();
        for (TitleHandler currParser: parsersList) {
            listOfMaps.add(currParser.parse());
        }
        HandlersUtility.sortPrint(HandlersUtility.mergeMaps(listOfMaps));
    }

}
