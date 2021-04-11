import Model.Language;
import Model.Node;
import Model.Perceptron;
import Service.DataBuilder;
import View.CheckLanguage;
import javax.swing.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        String address = "C:\\Users\\mjuni\\OneDrive\\Pulpit\\PJATEK\\NAI\\Neural network\\data";

        List<Language> languageList = new ArrayList<>();

        for (String name : DataBuilder.getListOfLanguages(address)) {
            List<StringBuilder> stringBuilderList = DataBuilder.getListOfStringBuilderOfAllFiles(address+"\\"+name);
            List<Map<Character,Long>> listOfMapOfProportions = DataBuilder.getListOfMapOfProportions(stringBuilderList);
            languageList.add(new Language(name,listOfMapOfProportions));
        }

        List<Perceptron> perceptronList = new ArrayList<>();

        for (Language language : languageList)
            perceptronList.add(new Perceptron(language.getMapList().get(0).size()-2,0.05,language.getName()));

        List<Node> trainList = new ArrayList<>();

        for (Language language : languageList) {
            for (Map<Character,Long> singleMap:language.getMapList()) {
                List<Double> attributesColumn = new ArrayList<>();
                for (int i = 'a'; i <= 'z'; i++)
                    attributesColumn.add((singleMap.get((char) i) / (double)((singleMap.get('@') - singleMap.get('!')))));
                trainList.add(new Node(attributesColumn,language.getName()));
            }
        }

        for (int i = 0; i < languageList.size() * 10000; i++) {
            Collections.shuffle(trainList);
            for (Node node : trainList)
                for (Perceptron perceptron : perceptronList)
                    perceptron.learn(node, (node.getNodeClassName().equals(perceptron.getTrainedForLanguage()) ? 1 : 0));
        }

        for (Perceptron perceptron : perceptronList)
            perceptron.normalizePerceptron();

        SwingUtilities.invokeLater(() -> new CheckLanguage(perceptronList));
    }
}
