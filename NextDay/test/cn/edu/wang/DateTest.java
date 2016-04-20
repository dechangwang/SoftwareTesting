package cn.edu.wang;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.junit.BeforeClass;
import org.junit.Test;

public class DateTest {
	private static List<Date> inputDateList;
	private List<Date> expectedDateList;
	private static final int SIZE = 27;

	@BeforeClass
	public static void init(){
		inputDateList = new ArrayList<Date>();
		 try {
			Workbook book = Workbook.getWorkbook(new File("testNextDay.xls"));
			Sheet sheet = book.getSheet(0);  
//			Cell cell = sheet.getCell(1,2); 
//			String result = cell.getContents();  
//			System.out.println(result);
//			System.out.println(Integer.parseUnsignedInt(result)+1);
			for(int i = 2;i<SIZE;i++){
				Cell cell1 = sheet.getCell(1,i);
				int resY = Integer.parseInt(cell1.getContents());
				cell1 = sheet.getCell(2,i);
				int resM = Integer.parseInt(cell1.getContents());
				cell1 = sheet.getCell(3,i);
				int resD = Integer.parseInt(cell1.getContents());
				inputDateList.add(new Date(resY,resM,resD));
			}
			
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	@Test
	public void testGetNextDay() {
		System.out.println(inputDateList);
		//assertSame(new Date(2016,1,3), new Date(2016,1,2).getNextDay());
		
	}

}
