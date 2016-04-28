package cn.edu.tongji;

/**
 * Created by wangdechang on 2016/4/28.
 */
public class RealCharge {
    private final static double costPerMinute = 0.15;//每分钟通话费用
    private int num;          //不按时缴费次数
    private double callMinutes;// 本月通话时长
    private double cost;      //本月实际通话费用

    public RealCharge() {
    }

    public RealCharge(int num, float callMinutes) {
        this.num = num;
        this.callMinutes = callMinutes;
    }

    //计算折扣后的实际通话费用
    private void calculateCost() {
        if(num< 0 || callMinutes <0){
            cost = -1;
            return;
        }
        int minutes = (int) Math.ceil(callMinutes);


        cost = minutes * costPerMinute;  //折扣之前的通话费用
        if (minutes <= 60) {
            if (num <= 1) {
                cost *= 0.99; //60min内折扣后的费用
            }
        } else if (minutes <= 120) {
            if (num <= 2) {
                cost *= 0.985; //120min内折扣后的费用
            }
        } else if (minutes <= 180) {
            if (num <= 3) {
                cost *= 0.98;    //180min内折扣后的费用
            }
        } else if (minutes <= 300) {
            if (num <= 3) {
                cost *= 0.975;   //300min内折扣后的费用
            }
        } else {
            if (num <= 6) {
                cost *= 0.97;    //超过300min折扣后的费用
            }
        }

    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setCallMinutes(double callMinutes) {
        this.callMinutes = callMinutes;
    }

    public double getCost() {
        calculateCost();
        return cost;
    }
}
