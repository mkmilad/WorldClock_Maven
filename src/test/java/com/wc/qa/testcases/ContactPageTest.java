package com.wc.qa.testcases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wc.qa.base.TestBase;

public class ContactPageTest extends TestBase {
	
	@FindBy(id = "fullname")
	WebElement fullName;
	
	@FindBy(id = "email")
	WebElement emailAddress;
	
	@FindBy(id = "feedtype")
	WebElement selectTopic;
	

	@FindBy(id = "body")
	WebElement messageTextArea;
	
	@FindBy(id = "submitFeedback")
	WebElement SubmitFeedbackButton;
	
	@FindBy(css = "button[class='ladda-button']")
	WebElement submitFeedback;
	
	public ContactPageTest() {
		
		PageFactory.initElements(driver, this);
		
	}	
		public ContactPageTest fillContactForm(String fullname, String email, String topic, String body) {
			
			fullName.sendKeys(fullname);
			emailAddress.sendKeys(email);
			
			Select select = new Select(selectTopic);
			select.selectByVisibleText(topic);
			
			messageTextArea.sendKeys(body);
			
			return this;
			
		}
		
		public void submitMessage() {
			
			SubmitFeedbackButton.click();
			
		}
	
	
	public String getMessage() {
		return submitFeedback.getText();
	}
	
}
