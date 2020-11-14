package vmk.project.model;

public class Record {
    private String id;
    private String name;
    private int votes;
    private int seen;
    private float straightAverage;
    private float weightedAverage;
    private float bayesianAverage;

    public Record() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public float getStraightAverage() {
        return straightAverage;
    }

    public void setStraightAverage(float straightAverage) {
        this.straightAverage = limitDecimalPlaces(straightAverage);
    }

    public float getWeightedAverage() {
        return weightedAverage;
    }

    public void setWeightedAverage(float weightedAverage) {
        this.weightedAverage = limitDecimalPlaces(weightedAverage);
    }

    public float getBayesianAverage() {
        return bayesianAverage;
    }

    public void setBayesianAverage(float bayesianAverage) {
        this.bayesianAverage = limitDecimalPlaces(bayesianAverage);
    }

    private float limitDecimalPlaces(float f){
        return (int) (f * 100) / 100.0F;
    }
}