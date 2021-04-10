import Service.DataBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) throws IOException {
        String address = "C:\\Users\\mjuni\\OneDrive\\Pulpit\\PJATEK\\NAI\\Neural network\\data";
        String directoryName = DataBuilder.getListOfFiles(address).get(2);
        System.out.println(directoryName);
        List<StringBuilder> list = DataBuilder.getStringBuilderOfAllFiles(address+"\\"+directoryName);


        List<Map<Character,Long>> listOfMapOfProportions = DataBuilder.getListOfMapOfProportions(list);
        System.out.println(listOfMapOfProportions.get(6));

        long tmp=0;

        for (Map<Character,Long> map :listOfMapOfProportions) {
            tmp+=map.get('!');
        }
        System.out.println(tmp);


    }
}
