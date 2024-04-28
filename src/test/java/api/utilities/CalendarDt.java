package api.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;


public class CalendarDt {
	
 
	public static String getCurrentDate() {
		Calendar calender = Calendar.getInstance();
		
		System.out.println(java.time.LocalDate.now());
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMMyyyy_hhmmss");
		System.out.println("parsedDate + " + dateFormat.format(calender.getTime()));
		///System.out.println("parsedDate New + " + dateFormat.format(java.time.LocalDate.now()));
		return dateFormat.format(calender.getTime());

	}
	/*
	 * public static void selectDate(String tarDate, WebDriver driver) throws
	 * Exception {
	 * 
	 * Calendar calender = Calendar.getInstance();
	 * 
	 * WebElement month =
	 * driver.findElement(By.cssSelector(".ui-datepicker-month")); WebElement year =
	 * driver.findElement(By.cssSelector(".ui-datepicker-year"));
	 * 
	 * Select scMonth = new Select(month); Select scYear = new Select(year);
	 * 
	 * try { SimpleDateFormat tarDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	 * tarDateFormat.setLenient(false); Date parsedDate =
	 * tarDateFormat.parse(tarDate); System.out.println(parsedDate);
	 * calender.setTime(parsedDate);
	 * 
	 * int targateMonth = calender.get(Calendar.MONTH); int targateYear =
	 * calender.get(Calendar.YEAR); int targateDate = calender.get(Calendar.DATE);
	 * 
	 * new Helper().waitForPageToload(driver); int actMonth =
	 * Integer.valueOf(scMonth.getFirstSelectedOption().getAttribute("value")); int
	 * actYear =
	 * Integer.valueOf(scYear.getFirstSelectedOption().getAttribute("value"));
	 * 
	 * if (actMonth != targateMonth || actYear != targateYear) {
	 * 
	 * if (actMonth != targateMonth) {
	 * 
	 * scMonth.selectByValue(Integer.toString(targateMonth)); }
	 * 
	 * if (actYear != targateYear) {
	 * 
	 * try { scYear.selectByValue(Integer.toString(targateYear)); } catch (Exception
	 * e) {
	 * 
	 * WebElement years = driver.findElement(By.cssSelector(".ui-datepicker-year"));
	 * Select scYears = new Select(years);
	 * scYears.selectByValue(Integer.toString(targateYear)); } }
	 * 
	 * driver.findElement(By.xpath("//a[text()=" + targateDate + "]")).click(); }
	 * 
	 * } catch (ParseException e) {
	 * 
	 * throw new
	 * Exception("\"Invalid date is provided. or Please check the Date format: dd-MMM-yyyy.\"."
	 * ); }
	 * 
	 * }
	 * 
	 */
}
