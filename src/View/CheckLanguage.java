package View;

import Model.Node;
import Model.Perceptron;
import Service.DataBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CheckLanguage extends JFrame {

    List<Perceptron> perceptronList;
    JTextArea exampleLanguageTextArea;
    JButton checkLanguageButton;
    JLabel answerLabel;

    public CheckLanguage(List<Perceptron> perceptronList){
        this.perceptronList = perceptronList;
        this.setTitle("Check Language!");
        this.setLayout(new BorderLayout(100,100));
        exampleLanguageTextArea = new JTextArea();
        checkLanguageButton = new JButton("Check Language!");
        checkLanguageButton.setPreferredSize(new Dimension(100,100));
        answerLabel = new JLabel("answer");
        checkLanguageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!exampleLanguageTextArea.getText().equals("")){
                    List<StringBuilder> stringBuilderList = new ArrayList<>();
                    stringBuilderList.add(new StringBuilder(exampleLanguageTextArea.getText()));
                    Map<Character,Long> map = DataBuilder.getListOfMapOfProportions(stringBuilderList).get(0);
                    answerLabel.setText(String.valueOf(map.get('a')));
                    List<Node> nodes = new ArrayList<>();
                    List<Double> attributesColumn = new ArrayList<>();
                    for (int i = 'a'; i <= 'z'; i++)
                        attributesColumn.add(map.get((char) i) / (double)((map.get('@') - map.get('!'))));

                    nodes.add(new Node(attributesColumn,"BLANK"));

                    double maxScore=-1;
                    String language="";
                    for (Node node : nodes) {
                        node.normalizeNode();
                        for (Perceptron perceptron : perceptronList) {
                            if (perceptron.evaluate(node)>maxScore) {
                                maxScore = perceptron.evaluate(node);
                                language= perceptron.getTrainedForLanguage();
                            }
                        }
                    }
                    answerLabel.setText(language);
                }

            }
        });


        getContentPane().add(exampleLanguageTextArea,BorderLayout.CENTER);
        getContentPane().add(checkLanguageButton,BorderLayout.SOUTH);
        getContentPane().add(answerLabel,BorderLayout.NORTH);

        setSize(1000,600);
        setLocation(600,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
