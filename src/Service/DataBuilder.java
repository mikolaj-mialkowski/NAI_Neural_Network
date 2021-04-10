package Service;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public static StringBuilder getStringBuilderOfAllFiles(String directoryAddress) throws IOException {
        List<String> fileAddresses = getListOfFiles(directoryAddress);
        StringBuilder stringBuilder = new StringBuilder();
        for (String fileAddress : fileAddresses) {
            stringBuilder.append(getStringBuilderOfFile(directoryAddress+"\\"+fileAddress));
        }
        return stringBuilder;
    }

    public static Map<Character, Long> getMapOfLettersInStringBuilder(StringBuilder stringBuilder){
        Map<Character, Long> map = new HashMap<>();
        long totalNumberOfNonAlphabeticalCharacters=stringBuilder.length();
        for (int i = 'a'; i <= 'z'; i++) {
            char finalCharacter = (char) i;
            //System.out.println(finalCharacter + " "+ (char) (finalCharacter-32));
            long count = stringBuilder.chars().filter(character -> ((character == finalCharacter) || (character == (finalCharacter-32)))).count();
            totalNumberOfNonAlphabeticalCharacters-=count;
            map.put((char)i,count);
        }
        map.put('!',totalNumberOfNonAlphabeticalCharacters);
        return map;
    }







}
