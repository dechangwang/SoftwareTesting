package cn.edu.tongji;

import java.util.Scanner;

/**
 * Created by wangdechang on 2016/4/28.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int callMinutes, unpayNum;
        double unpaymentLastYear;
        System.out.print("本月通话时长：");
        callMinutes = in.nextInt();
        System.out.print("本年度未按时缴费次数：");
        unpayNum = in.nextInt();
        System.out.print("跨年未交费总额：");
        unpaymentLastYear = in.nextDouble();
        if (callMinutes <= 0 || unpayNum < 0 || unpayNum > 11) {
            System.out.println("输入有错");
        } else {
            RealCharge realCharge = new RealCharge(unpayNum, callMinutes);
            double realCost = realCharge.getCost();
            TotalCharge totalCharge = new TotalCharge(unpaymentLastYear, realCost);
            double totalCost = totalCharge.getTotalCost();

            System.out.println("总的通话费用为：" + totalCost);
        }

    }
}
