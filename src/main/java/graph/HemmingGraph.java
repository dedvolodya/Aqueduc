package graph;

public class HemmingGraph extends Graph {
    private int bitRate;
    private int distance;

    public HemmingGraph(int bitRate, int distance) {
        this.bitRate = bitRate;
        this.distance = distance;
    }

    public int getBitRate() {
        return this.bitRate;
    }

    public int getDistance() {
        return this.distance;
    }
}
