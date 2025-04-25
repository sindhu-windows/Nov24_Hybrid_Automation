package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ControlActions25;
import io.qameta.allure.Step;

public class LoginPage extends ControlActions25{
	
	//"//span[text()='Welcome to Hire360']")
	@FindBy(xpath = "//div[contains(@class,'jss4 MuiBox-root css-0')]/span[contains(text(),'Welcome')]")
	WebElement welcomeMsgEle;
	
	@FindBy(xpath = "//input[@id='outlined-adornment-email']")
	WebElement emailEle;
	
	@FindBy(xpath = "//input[@id='outlined-adornment-password']")
	WebElement pwdEle;
	
	@FindBy(xpath="//button[text()='Login']")
	WebElement loginBtnEle;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@Step("Waiting for Page Load")
	public boolean waitForLoginPageLoad() {
		return isElementDisplayed(welcomeMsgEle);
				//("xpath","//div[contains(@class,'jss4 MuiBox-root css-0')]/span[contains(text(),'Welcome')]",true)		
	}
		
	@Step("User entered Username as{0}")
	public void enterEmail(String email) {
		setText(emailEle,false,email);
	}
	
	@Step("User entered Password as{0}")
	public void enterPassword(String pwd) {
		setText(pwdEle,false,pwd);
	}
	
	@Step("User clicked on Login button")
	public void clickOnLoginBtn() {
		clickOnElement(loginBtnEle);
	}
	
	
	@Step("User cheking is dashboard page is loaded or not")
	public DashboardPage callLoginPage(String email, String pwd) {
		waitForLoginPageLoad();
		enterEmail(email);
		enterPassword(pwd);
		clickOnLoginBtn();
		return new DashboardPage();
	}
}
