package Service;

import java.io.*;
import java.util.*;

public class DataBuilder {

    public static int getNumberOfLanguages(String address){
        return new File(address).list().length;
    }

    public static List<String> getListOfLanguages(String address){
        return Arrays.asList(new File(address).list());
    }

    public static List<String> getListOfFiles(String address){
        return Arrays.asList(new File(address).list());
    }

    public static StringBuilder getStringBuilderOfFile(String fileAddress) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress));
        String line;
        while ((line= bufferedReader.readLine())!=null)
            stringBuilder.append(line);
        bufferedReader.close();
        return stringBuilder;
    }

    public static List<StringBuilder> getListOfStringBuilderOfAllFiles(String directoryAddress) throws IOException {
        List<StringBuilder> list = new ArrayList<>();
        List<String> fileAddresses = getListOfFiles(directoryAddress);
        for (String fileAddress : fileAddresses)
            list.add(getStringBuilderOfFile(directoryAddress+"\\"+fileAddress));
        return list;
    }

    public static List<Map<Character, Long>> getListOfMapOfProportions(List<StringBuilder> stringBuilderList){
        List<Map<Character,Long>> mapList = new ArrayList<>();
        for (StringBuilder stringBuilder : stringBuilderList) {
            Map<Character, Long> map = new HashMap<>();
            long totalNumberOfNonAlphabeticalCharacters = stringBuilder.length();
            for (int i = 'a'; i <= 'z'; i++) {
                char finalCharacter = (char) i;
                long count = stringBuilder.chars().filter(character -> ((character == finalCharacter) || (character == (finalCharacter - 32)))).count();
                totalNumberOfNonAlphabeticalCharacters -= count;
                map.put((char) i, count);
            }
            map.put('!', totalNumberOfNonAlphabeticalCharacters); // non-alphabetical
            map.put('@', (long) stringBuilder.length()); // all characters
            mapList.add(map);
        }
        return mapList;
    }
}
