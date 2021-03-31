package pojo;

public class ProfitWeightRatio {
    private String profit;
    private String weight;
    private Double ratio;
    private Integer index;

    public ProfitWeightRatio(String profit, String weight, Double ratio, Integer index) {
        this.profit = profit;
        this.weight = weight;
        this.ratio = ratio;
        this.index=index;
    }

    public Integer getIndex() {
        return index;
    }

    public String getProfit() {
        return profit;
    }

    public String getWeight() {
        return weight;
    }

    public Double getRatio() {
        return ratio;
    }


    @Override
    public String toString() {
        return "ProfitWeightRatio{" +
                "profit='" + profit + '\'' +
                ", weight='" + weight + '\'' +
                ", ratio=" + ratio +
                ", index=" + index +
                '}';
    }
}
