package cn.edu.wang;

import java.util.ArrayList;
import java.util.List;

public class NextDay {
	private int year;
	private int month;
	private int day;
	private List<Integer> thirtyOneList;
	private List<Integer> thirtyList;

	public NextDay(Date date) {
		year = date.getYear();
		month = date.getMonth();
		day = date.getDay();
		thirtyOneList = new ArrayList<Integer>();
		thirtyOneList.add(1);
		thirtyOneList.add(3);
		thirtyOneList.add(5);
		thirtyOneList.add(7);
		thirtyOneList.add(8);
		thirtyOneList.add(10);
		
		thirtyList = new ArrayList<Integer>();
		thirtyList.add(4);
		thirtyList.add(6);
		thirtyList.add(9);
		thirtyList.add(11);
		
	}

	private boolean isValidate() {
		if (month <= 0 || month > 12 || day <= 0 || day > 31) {
			return false;
		}
		return true;
	}

	/* 判断是否为闰年 */
	private boolean isLeepYear() {
		if (year % 400 == 0 || year % 100 != 0 && year % 4 == 0)
			return true;
		else
			return false;
	}

	/* 返回下一天 */
	public Date getNextDay() {
		// 默认day+1作为下一天
		int yearNext = year, monthNext = month, dayNext = day + 1;
		if (!isValidate()) {
			System.out.println("your enter have error!!!");
			return null;
		}
		if (month == 2) {
			if (isLeepYear()) {
				if (day == 29) {
					monthNext = month + 1;
					dayNext = 1;
				}else if(day > 29){
					return null;
				}
			} else {
				if (day == 28) {
					monthNext = month + 1;
					dayNext = 1;
				}else if(day > 28){
					return null;
				}
			}
			return new Date(yearNext, monthNext, dayNext);
		}

		if (month == 12) {
			if (day == 31) {
				yearNext = year + 1;
				monthNext = 1;
				dayNext = 1;
			}
			return new Date(yearNext, monthNext, dayNext);
		}
		//1 3 5 7 8 10
		if (thirtyOneList.contains(month)) {
			if (day == 31) {

				monthNext = month + 1;
				dayNext = 1;
			}
			return new Date(yearNext, monthNext, dayNext);
		}
		//4 6 9 11 
		if (thirtyList.contains(month)) {
			if(day == 30){
				monthNext = month + 1;
				dayNext = 1;
			}else if(day > 30){
				return null;
			}
			
		}
		return new Date(yearNext, monthNext, dayNext);
	}

	public static boolean isSameDay(Date d1, Date d2) {
		if (d1 == null && d2 == null) {
			return true;
		}
		// if(d1 == null && d2 != null || d1 != null && d2 == null){
		// return false;
		// }
		if (d1 != null && d2 != null)
			if (d1.getDay() == d2.getDay() && d1.getMonth() == d2.getMonth()
					&& d1.getYear() == d2.getYear()) {
				return true;
			}
		return false;
	}
}
