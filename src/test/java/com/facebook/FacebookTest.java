package com.facebook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class FacebookTest {

	private LoginPage facebookLoginPage;
	private HomePage facebookHomePage;
	private CommonUtilities commonUtilities;
	static WebDriver driver;
	static Properties facebookProperties;

	private String loginUrl;
	private String username;
	private String password;
	private String pageTitleLogin;
	private String pageTitleHomePage;
	private String friendName;
	private String feelingName;
	private String emotionName;
	private String locationName;
	private String statusText;

	public FacebookTest() throws IOException {
		setPropertyFile();
		getPropertyValues();
		setDriver();
	}

	public void setPropertyFile() {
		try {
			FileInputStream inputStream = new FileInputStream("src\\test\\java\\com\\facebook\\facebook.properties");
			facebookProperties = new Properties();
			facebookProperties.load(inputStream);
		} catch (IOException e) {
			System.out.println("cannot load properties file.");
		}
		System.setProperty("webdriver.gecko.driver", "src\\test\\java\\drivers\\geckodriver.exe");
	}

	public void getPropertyValues() {
		loginUrl = facebookProperties.getProperty("url.login");
		username = facebookProperties.getProperty("kabeer.username");
		password = facebookProperties.getProperty("kabeer.password");
		pageTitleLogin = facebookProperties.getProperty("pagetitle.login");
		pageTitleHomePage = facebookProperties.getProperty("pagetitle.homepage");
		statusText = facebookProperties.getProperty("status.text");
		friendName = facebookProperties.getProperty("kabeer.friendName");
		feelingName = facebookProperties.getProperty("status.feeling");
		emotionName = facebookProperties.getProperty("status.emotion");
		locationName = facebookProperties.getProperty("status.location");
	}

	public void setDriver() {
		driver = new FirefoxDriver();
		facebookLoginPage = new LoginPage(driver);
		facebookHomePage = new HomePage(driver);
		commonUtilities = new CommonUtilities(driver);
	}

	@AfterClass()
	public static void tearDown() throws InterruptedException {
		Thread.sleep(10000);
		// driver.close();
	}

	@Test(priority = 0)
	public void verifyLoginPageIsVisible() throws Throwable {
		facebookLoginPage.goToLoginPage(loginUrl);
		// commonUtilities.pageTitleShouldBe(pageTitleLogin);
	}

	@Test(priority = 1)
	public void verifyLoginFunctionality() throws Throwable {
		facebookLoginPage.enterUserName(this.username);
		facebookLoginPage.enterPassword(this.password);
		facebookLoginPage.clickLoginButton();
		commonUtilities.pageTitleShouldBe(pageTitleHomePage);
	}
	
	@Test(priority = 2)
	public void verifyStatusUpdateFunctionality() throws Throwable {
		facebookHomePage.writeStatus(statusText);
		facebookHomePage.tagPeopleInStatus(friendName);
		facebookHomePage.addEmotions(feelingName, emotionName);
		facebookHomePage.checkInLocation(locationName);
		facebookHomePage.doPostStatus();
		facebookHomePage.verifyStatus();
	}
	
	@Test(priority = 3)
	public void verifyLogoutFunctionality() throws Throwable {
		facebookHomePage.doLogout();
		commonUtilities.pageTitleShouldBe(pageTitleLogin);
	}
	
	
	
}
