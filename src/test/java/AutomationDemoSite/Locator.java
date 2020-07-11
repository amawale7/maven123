package AutomationDemoSite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Locator 
{
	WebDriver driver;

	@FindBy(how=How.XPATH,using="/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a")
	WebElement submit;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"firstName\"]")
	WebElement firstName;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"lastName\"]")
	WebElement lastName;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"phone\"]")
	WebElement phone;
	
	@FindBy(how=How.XPATH,using="//input[@id=\"userName\"]")
	WebElement email;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"address1\"]")
	WebElement address1;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"address2\"]")
	WebElement address2;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"city\"]")
	WebElement city;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"state\"]")
	WebElement state;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"postalCode\"]")
	WebElement postalCode;
	
	@FindBy(how=How.XPATH,using="//select[@name=\"country\"]")
	WebElement country;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"email\"]")
	WebElement userName1;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"password\"]")
	WebElement password1;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"confirmPassword\"]")
	WebElement confirmPassword;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"register\"]")
	WebElement register;
	
	@FindBy(how=How.XPATH,using="//a[contains(text(),'SIGN-OFF')]")
	WebElement SIGNOFF;
	
	@FindBy(how=How.XPATH,using="//input[@name='userName']")
	WebElement userName;
	
	@FindBy(how=How.XPATH,using="//input[@name='password']")
	WebElement password;
	
	@FindBy(how=How.XPATH,using="//input[@name=\"login\"]")
	WebElement submitLogin;
	
	public Locator(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	public void register() throws IOException
	{
		FileInputStream file=new FileInputStream("C:\\Users\\Aditya\\Desktop\\ExcelSheet\\MercuryRegistrationData.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		int noOfRow=sheet.getLastRowNum();
		
		for(int row=1;row<=noOfRow;row++)
		{
			XSSFRow current_row=sheet.getRow(row);
			String firtName= current_row.getCell(0).getStringCellValue();
			String lstName= current_row.getCell(1).getStringCellValue();
			int phonee=(int) current_row.getCell(2).getNumericCellValue();
			String emaill= current_row.getCell(3).getStringCellValue();
			String address11= current_row.getCell(4).getStringCellValue();
			String address22= current_row.getCell(5).getStringCellValue();
			String cityy= current_row.getCell(6).getStringCellValue();
			String statee= current_row.getCell(7).getStringCellValue();
			int postalCodee=(int) current_row.getCell(8).getNumericCellValue();
			int countryy=(int) current_row.getCell(9).getNumericCellValue();
			String userName11= current_row.getCell(10).getStringCellValue();
			String password11= current_row.getCell(11).getStringCellValue();
			String userNamee= current_row.getCell(10).getStringCellValue();
			String passwordd= current_row.getCell(11).getStringCellValue();
			
			submit.click();
			firstName.sendKeys(firtName);
			lastName.sendKeys(lstName);
			phone.sendKeys(String.valueOf(phonee));
			email.sendKeys(emaill);
			address1.sendKeys(address11);
			address2.sendKeys(address22);
			city.sendKeys(cityy);
			state.sendKeys(statee);
			postalCode.sendKeys(String.valueOf(postalCodee));
			Select sel=new Select(country);
			sel.selectByValue(String.valueOf(countryy));
			userName1.sendKeys(userName11);
			password1.sendKeys(password11);
			register.click();
			
			String title=driver.getTitle();
			System.out.println(title);
			Assert.assertEquals("Register: Mercury Tours", title);
			
			SIGNOFF.click();
			userName.sendKeys(userNamee);
			password.sendKeys(passwordd);
			submitLogin.click();
			
			String title1=driver.getTitle();
			System.out.println(title1);
			
			Assert.assertEquals("Find a Flight: Mercury Tours:", title1);
			
			FileOutputStream ofile=new FileOutputStream("C:\\Users\\Aditya\\Desktop\\ExcelSheet\\MercuryRegistrationData.xlsx");
			workbook.write(ofile);
			
			if(title1.equalsIgnoreCase("Find a Flight: Mercury Tours:"))
			{
				current_row.createCell(12).setCellValue("pass");
			}
			
			else
			{
				current_row.createCell(12).setCellValue("Fail");
			}
			
			submitLogin.click();
			
		}
	}
}