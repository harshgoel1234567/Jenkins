package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import pageobjects.ProductPage;
import resources.Base;

public class ProductDetailsTest  extends Base{
	
	public WebDriver d;
	
	@Test(dataProvider="getProductDetails")
	public void ProductTest(String productName) throws FileNotFoundException, IOException, InterruptedException
	{

		LandingPage l=new LandingPage(d);
		  d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		l.searchBox().sendKeys(productName);
		l.searchButton().click();
		
		ProductPage p=new ProductPage(d);
		System.out.println(p.ProductName().getText());
		
		Assert.assertTrue(p.ProductName().isDisplayed());
	     
		
		
	}
	@BeforeMethod
	public void openApplication() throws FileNotFoundException, IOException
	{
		d=initializedriver();
		d.get(prop.getProperty("url"));
	}
	@AfterMethod
	public void closure()
	{
		d.close();
	
	}
	
	@DataProvider
	public Object[][] getProductDetails()
	{
		Object[][] data= {{"Laptop"}};
		return data;
	}
}
