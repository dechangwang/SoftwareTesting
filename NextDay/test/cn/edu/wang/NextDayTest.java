package cn.edu.wang;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class NextDayTest extends TestCase {
	private List<Date> inputDateList;
	private List<Date> expectedDateList;
	private List<Date> realOutputList;
	private static final int SIZE = 87;
	private Workbook book;

	public NextDayTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		inputDateList = new ArrayList<Date>();
		expectedDateList = new ArrayList<Date>();
		realOutputList = new ArrayList<Date>();
		book = Workbook.getWorkbook(new File("textNextDay.xls"));
		Sheet sheet = book.getSheet(0);
		for (int i = 2; i < SIZE; i++) {
			Cell cell = sheet.getCell(1, i);
			int resY = Integer.parseInt(cell.getContents());
			cell = sheet.getCell(2, i);
			int resM = Integer.parseInt(cell.getContents());
			cell = sheet.getCell(3, i);
			int resD = Integer.parseInt(cell.getContents());
			inputDateList.add(new Date(resY, resM, resD));
			cell = sheet.getCell(4, i);
			String resExpec = cell.getContents();
			if (resExpec.equals("null")) {
				// System.out.println(resExpec);
				expectedDateList.add(null);
			} else {
				String[] res = resExpec.split(",");
				int y = Integer.parseInt(res[0]);
				int m = Integer.parseInt(res[1]);
				int d = Integer.parseInt(res[2]);
				expectedDateList.add(new Date(y, m, d));
			}
		}
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		WritableWorkbook bookWrite = Workbook.createWorkbook(new File(
				"textNextDay.xls"), book);
		WritableSheet sheet = bookWrite.getSheet(0);// createSheet("µÚÒ»Ò³", 0);

		for (int i = 0; i < inputDateList.size(); i++) {
			sheet.addCell(new Label(4, i + 2, expectedDateList.get(i) + ""));
			sheet.addCell(new Label(5, i + 2, realOutputList.get(i) + ""));
			sheet.addCell(new Label(6, i + 2, NextDay.isSameDay(
					expectedDateList.get(i), realOutputList.get(i)) + ""));
		}
		bookWrite.write();
		bookWrite.close();
	}
	public void testNextDay() {
		for (int i = 0; i < inputDateList.size(); i++) {
			realOutputList.add(new NextDay(inputDateList.get(i)).getNextDay());
		}
		
	}

}
