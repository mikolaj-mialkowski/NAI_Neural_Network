import Model.Language;
import Service.DataBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException {
        String address = "C:\\Users\\mjuni\\OneDrive\\Pulpit\\PJATEK\\NAI\\Neural network\\data";


        /*String LANLIST = DataBuilder.getListOfLanguages(address).get(2); // polska
        System.out.println("NAME = " + LANLIST);
        List<StringBuilder> stringBuilderList = DataBuilder.getListOfStringBuilderOfAllFiles(address+"\\"+LANLIST);

        System.out.println(stringBuilderList.get(1));*/


        /*List<Map<Character,Long>> listOfMapOfProportions = DataBuilder.getListOfMapOfProportions(stringBuilderList);
        System.out.println(listOfMapOfProportions.get(9).get('@'));*/

        /*long tmp=0;

        for (Map<Character,Long> map :listOfMapOfProportions) {
            tmp+=map.get('@');
        }
        System.out.println(tmp);*/

        List<Language> languageList = new ArrayList<>();

        for (String name : DataBuilder.getListOfLanguages(address)) {
            List<StringBuilder> stringBuilderList = DataBuilder.getListOfStringBuilderOfAllFiles(address+"\\"+name);
            List<Map<Character,Long>> listOfMapOfProportions = DataBuilder.getListOfMapOfProportions(stringBuilderList);
            languageList.add(new Language(name,listOfMapOfProportions));
        }












    }
}
