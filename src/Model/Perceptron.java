package Model;

import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    String trainedForLanguage;
   private List<Double> vectorW;
   private double thetaThreshold;
   private double alpha;

    public String getTrainedForLanguage() {
        return trainedForLanguage;
    }

    public Perceptron(int vectorSize, double alpha, String trainedForLanguage) {
        this.trainedForLanguage =trainedForLanguage;
        this.alpha = alpha;
        this.vectorW = new ArrayList<>();
        for (int i = 0; i < vectorSize ; i++) {
            this.vectorW.add(1.0);
        }
        this.thetaThreshold =1.0;
    }

    public List<Double> getVectorW() {
        return vectorW;
    }

    public void setVectorW(List<Double> vectorW) {
        this.vectorW = vectorW;
    }

    public double getThetaThreshold() {
        return thetaThreshold;
    }

    public void setThetaThreshold(double thetaThreshold) {
        this.thetaThreshold = thetaThreshold;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public void learn(Node node, int correctAnswer){
        double scalarProduct = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            scalarProduct += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        int y = (scalarProduct>=this.thetaThreshold?1:0);

        if (y != correctAnswer) { //Do learn
            //System.out.println("LEARN - " + trainedForLanguage);
            List<Double> vectorWPrime = new ArrayList<>(this.vectorW);
            for (int i = 0; i < node.getAttributesColumn().size(); i++) // W' = W + (Correct-Y) * Alpha * X
                vectorWPrime.set(i, (this.vectorW.get(i) + ((correctAnswer - y) * alpha * node.getAttributesColumn().get(i))));

            this.vectorW = vectorWPrime;
            this.thetaThreshold = thetaThreshold + (correctAnswer - y) * alpha * -1;
        }
    }

    public double evaluate(Node node){
        double scalarProduct = 0;
        for (int i = 0; i < node.getAttributesColumn().size() ; i++) // Calculate X * W
            scalarProduct += node.getAttributesColumn().get(i) * this.vectorW.get(i);

        return scalarProduct - thetaThreshold; //net
    }

    public void normalizePerceptron(){
        double sumOfSquares=0;

        for (double d : this.vectorW)
            sumOfSquares+=Math.pow(d,2);

        double length = Math.sqrt(sumOfSquares);

        List<Double>  newVectorW = new ArrayList<>();

        for (int i = 0; i < this.vectorW.size() ; i++)
            newVectorW.add(i,(this.vectorW.get(i)/length));

        this.thetaThreshold = thetaThreshold/length;
        this.vectorW = newVectorW;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "vectorW=" + vectorW +
                ", thetaThreshold=" + thetaThreshold +
                '}';
    }
}



