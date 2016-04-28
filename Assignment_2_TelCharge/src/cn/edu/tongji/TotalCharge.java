package cn.edu.tongji;

/**
 * Created by wangdechang on 2016/4/28.
 */
public class TotalCharge {
    private final static int basicCost = 25;
    private double unpaymentLastYear;//跨年未交付的总额
    private double totalCost;
    private double realCost;

    public TotalCharge() {
    }

    public TotalCharge(double unpaymentLastYear,double realCost) {
        this.unpaymentLastYear = unpaymentLastYear;
        this.realCost = realCost;
    }
    public double getTotalCost() {
        totalCost = basicCost + realCost + unpaymentLastYear *0.05;
        totalCost = Double.parseDouble(String.format("%.2f", totalCost));
        return totalCost;
    }

    public void setUnpaymentLastYear(double unpaymentLastYear) {
        this.unpaymentLastYear = unpaymentLastYear;
    }

    public void setRealCost(double realCost) {
        this.realCost = realCost;
    }
}
