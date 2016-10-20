package com.facebook;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.junit.Assert;

@SuppressWarnings("deprecation")
public class LoginPage {
	
	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	private WebDriver driver;
	private Properties facebookProperties;
	
	@FindBy(id="email")
	private WebElement usernameTextBox;
	
	@FindBy(id="pass")
	private WebElement passwordTextBox;
	
	@FindBy(id="loginbutton")
	private WebElement loginButton;
	
	public void goToLoginPage(String url){
		driver.get(url);
	}
	
	public String enterUserName(String username){
		usernameTextBox.sendKeys(username);
		return username;
	}
	
	public String enterPassword(String password){
		passwordTextBox.sendKeys(password);
		return password;
	}
	
	public void clickLoginButton(){
		loginButton.click();
	}
}
