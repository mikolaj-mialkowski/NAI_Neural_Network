import Service.DataBuilder;

import java.io.IOException;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException {
        String address = "C:\\Users\\mjuni\\OneDrive\\Pulpit\\PJATEK\\NAI\\Neural network\\data";
        String directoryName = DataBuilder.getListOfFiles(address).get(2);
        System.out.println(directoryName);
        StringBuilder stringBuilder = DataBuilder.getStringBuilderOfAllFiles(address+"\\"+directoryName);


        Map<Character,Long> map = DataBuilder.getMapOfLettersInStringBuilder(stringBuilder);
        System.out.println(map.get('c'));


    }
}
