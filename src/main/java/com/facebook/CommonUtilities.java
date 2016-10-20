package com.facebook;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonUtilities {
	
	private WebDriver driver;
	
	public CommonUtilities(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void pageTitleShouldBe (String pageTitleString) throws InterruptedException{
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(), pageTitleString);
	}
}
