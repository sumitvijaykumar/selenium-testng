package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class HomePage {	
	
	private String checkInLocationString;
	private String emotionCategoryString;
	private String emotionNameString;
	private String statusTextString;
	private String peopleToTagString;
	
	@FindBy(id="logoutMenu")
	private WebElement settingsMenuButton;
	
	@FindBy(xpath=".//*[@id='BLUE_BAR_ID_DO_NOT_USE']//li[contains(@data-gt,'menu_logout')]")
	private WebElement logoutButton;
	
	@FindBy(xpath=".//textarea[contains(@title, 'on your mind?')]")
	private WebElement statusTextArea;
	
	@FindBy(xpath=".//div[@id='contentArea']//div[@id='pagelet_composer']//div[contains(@id,'placeholder')]")
	private WebElement statusTextAreaAfterClick;

	@FindBy(xpath=".//div[@id='pagelet_composer']//a[@data-tooltip-content='Check In']/div")
	private WebElement checkInButton;
	
	@FindBy(xpath=".//input[@placeholder='Where are you?']")
	private WebElement locationTextArea;
	
	@FindBy(xpath=".//div[contains(@class,'uiContextualLayer')]//div[@class='uiScrollableAreaContent']/ul/li[1]")
	private WebElement firstResultLocationSearch;
	
	@FindBy(xpath=".//div[@id='contentArea']//div[@id='pagelet_composer']//a[contains(@data-tooltip-content,'feeling')]/div")
	private WebElement addEmotionButton;
	
	@FindBy(xpath=".//input[@placeholder='What are you doing?']")
	private WebElement emotionTextArea;
	
	@FindBy(xpath=".//div[@id='contentArea']//div[@id='pagelet_composer']//a[contains(@data-tooltip-content,'Tag people in your post')]/div")
	private WebElement tagPeople;
	
	@FindBy(xpath=".//input[@placeholder='Who are you with?']")
	private WebElement tagPeopleTextArea;
	
	@FindBy(xpath=".//div[contains(@class,'uiContextualLayer')]//ul[@role='listbox']/li[1]")
	private WebElement firstResultAutoComplete;
	
	private WebElement emotionCategory;
	
	private WebElement emotionName;
	
	@FindBy(xpath=".//div[@id='pagelet_composer']//div[@display='block']//span[text()='Post']")
	private WebElement postButton;
	
	@FindBy(xpath=".//span[text()='Just now']/../../../../../../h5/span")
	private WebElement statusView;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private WebDriver driver;
	
	public void doLogout() throws InterruptedException{
		Thread.sleep(10000);
		settingsMenuButton.click();
		Thread.sleep(1500);
		logoutButton.click();
	}
	
	public void writeStatus(String statusText) throws InterruptedException{
		this.statusTextString = statusText;
		Thread.sleep(2000);
		statusTextArea.click();
		Thread.sleep(5000);
		statusTextAreaAfterClick.click();
		Thread.sleep(2000);
		statusTextAreaAfterClick.sendKeys(statusText);
	}
	
	public void tagPeopleInStatus(String peopleToTag) throws InterruptedException{
		this.peopleToTagString = peopleToTag;
		Thread.sleep(1000);
		tagPeople.click();
		Thread.sleep(1000);
		tagPeopleTextArea.sendKeys(peopleToTag);
		Thread.sleep(1000);
		firstResultAutoComplete.click();
		Thread.sleep(1000);
	}
	
	public void addEmotions(String category, String emotion) throws InterruptedException{
		this.emotionCategoryString = category;
		this.emotionNameString = emotion;
		Thread.sleep(2000);
		addEmotionButton.click();
		Thread.sleep(1000);
		emotionTextArea.click();
		emotionCategory = driver.findElement(By.xpath(".//div[@class='uiScrollableAreaBody']//ul[@role='listbox']//div[contains(text(),'"+category+"')]/ancestor::li"));
		emotionCategory.click();
		Thread.sleep(1000);
		emotionName = driver.findElement(By.xpath(".//div[@class='uiScrollableAreaBody']//ul[@role='listbox']//div[contains(text(),'"+emotion+"')]/ancestor::li"));
		emotionName.click();
		Thread.sleep(1000);
	}
	
	public void checkInLocation(String locationName) throws InterruptedException{
		this.checkInLocationString = locationName;
		checkInButton.click();
		locationTextArea.sendKeys(locationName);
		Thread.sleep(3000);
		firstResultLocationSearch.click();
	}
	
	public void doPostStatus() throws InterruptedException{
		Thread.sleep(1000);
		postButton.click();
	}
	
	public void verifyStatus() throws InterruptedException{
		Thread.sleep(6000);
		String statusText = statusView.getText();
		Assert.assertTrue(statusText.toLowerCase().contains(checkInLocationString.toLowerCase()));
		Assert.assertTrue(statusText.toLowerCase().contains(emotionCategoryString.toLowerCase()));
		Assert.assertTrue(statusText.toLowerCase().contains(emotionNameString.toLowerCase()));
		Assert.assertTrue(statusText.toLowerCase().contains(peopleToTagString.toLowerCase()));
	}
}
