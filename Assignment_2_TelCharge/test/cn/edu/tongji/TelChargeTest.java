package cn.edu.tongji;


import junit.framework.TestCase;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdechang on 2016/4/28.
 */
public class TelChargeTest extends TestCase {
    private static final int SIZE = 8;
    private Workbook book;
    RealCharge realCharge;
    TotalCharge totalCharge;

    private List<String> resultList;
    private List<Double> realOutputList;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        realCharge = new RealCharge();
        totalCharge = new TotalCharge();
        resultList = new ArrayList<>();
        realOutputList = new ArrayList<>();
        book = Workbook.getWorkbook(new File("testTelCharge.xls"));
        //Sheet sheet = book.getSheet(0);
    }

    public void testTelCharge() {
        Sheet sheet = book.getSheet(0);
        for (int i = 2; i < SIZE; i++) {
            Cell cell = sheet.getCell(1, i);
            int unpayNum = Integer.parseInt(cell.getContents());
            cell = sheet.getCell(2, i);
            int callMinutes = Integer.parseInt(cell.getContents());
            cell = sheet.getCell(3, i);
            double unpaymentLastYear = Double.parseDouble(cell.getContents());
            cell = sheet.getCell(4, i);
            double expectedRes = Double.parseDouble(cell.getContents());
            realCharge.setNum(unpayNum);
            realCharge.setCallMinutes(callMinutes);
            double realCost = realCharge.getCost();
            totalCharge.setRealCost(realCost);
            totalCharge.setUnpaymentLastYear(unpaymentLastYear);
            realOutputList.add(totalCharge.getTotalCost());
            if (totalCharge.getTotalCost() == expectedRes) {
                resultList.add("true");
            } else {
                resultList.add("false");
            }

        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        WritableWorkbook bookWrite = Workbook.createWorkbook(new File(
                "testTelCharge.xls"), book);
        WritableSheet sheet = bookWrite.getSheet(0);// createSheet("µÚÒ»Ò³", 0);

        for (int i = 0; i < resultList.size(); i++) {
            sheet.addCell(new Label(5, i + 2, realOutputList.get(i) + ""));
            sheet.addCell(new Label(6, i + 2, resultList.get(i)));
        }
        bookWrite.write();
        bookWrite.close();
    }
}