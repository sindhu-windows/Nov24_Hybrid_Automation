package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ControlActions25;

public class DashboardPage extends ControlActions25{
	
	@FindBy(xpath = "//p[text()='Create Question']")
	private WebElement createQuestElement;
	
	@FindBy(xpath = "//div[text()='Hello, Welcome Back!!']")
	private WebElement welcomeMsgElement;
	
	@FindBy(xpath = "//p[text()='Total Assessments']")
	private WebElement totalAssessmentsElement;
	
	@FindBy(xpath = "//p[text()='Total Assessments']//following-sibling::h6")
	private WebElement totalAssessmentsCountElement;

	@FindBy(xpath = "//p[text()='Total Appeared']")
	private WebElement totalAppearedElement;
	
	@FindBy(xpath = "//p[text()='Total Appeared']//following-sibling::h6")
	private WebElement totalAppearedCountElement;
	
	@FindBy(xpath = "//p[text()='Total Qualified']")
	private WebElement totalQualifiedElement;
	
	@FindBy(xpath = "//p[text()='Total Qualified']//following-sibling::h6")
	private WebElement totalQualifiedCountElement;
	
	@FindBy(xpath = "//p[text()='View Template']")
	private WebElement viewTemplateElement;
	
	@FindBy(xpath = "//p[text()='Create Assessment']")
	private WebElement createAssessmentElement;
	
	private String leftPanelMenuLocator = "//span[text()='%s']";
	
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean waitForPageLoad() {
		return urlContainsString("dashboard");
	}
		
	public String getCurrentUrl() { 
		return super.getCurrentUrl(); 
	}
			
	public boolean isWelcomMsgDisplayed() {
		return isElementDisplayed(welcomeMsgElement);
	}
	
	public boolean totalAssessmentVisibility() {
		return isElementDisplayed(totalAssessmentsElement);
	}
	
	public void totalAssessmentCount() {
		//System.out.println("Verify - Total Assessment count is visible.");
		String countEle = getElementText(totalAssessmentsCountElement,false);
		int count = Integer.parseInt(countEle);
		Assert.assertTrue(count >= 0);
	}
	
	public boolean totalAppearedVisibility() {
		//System.out.println("Verify - Total Appeared is visible.");
		return isElementDisplayed(totalAppearedElement);
	}
	
	public void totalAppearedCount() {
		//System.out.println("Verify - Total Appeared count is visible.");
		String countEle = getElementText(totalAppearedCountElement,false);
		int count = Integer.parseInt(countEle);
		Assert.assertTrue(count >= 0);
	}
	
	public boolean totalQualifiedVisibility() {
		//System.out.println("Verify - Total Qualified is visible.");
		return isElementDisplayed(totalQualifiedElement);
	}
	
	public void totalQualifiedCount() {
		//System.out.println("Verify - Total Qualified count is visible.");
		String qualifiedStr = getElementText(totalQualifiedCountElement,false);
		int count = Integer.parseInt(qualifiedStr.replace("%", ""));
		Assert.assertTrue(qualifiedStr.endsWith("%"));
		Assert.assertTrue(count >= 0);
	}
		
	public boolean viewTemplateButtonVisibility() {
		//System.out.println("Verify - View Template button is visible.");
		return isElementDisplayed(viewTemplateElement);
	}
	
	public boolean createQuestionButtonVisibility() {
		//System.out.println("Verify - Create Question button is visible.");
		return isElementDisplayed(createQuestElement);	
	}
	
	public CreateQuestionPage clickOnCreateQuestionBtn() {
		clickOnElement(createQuestElement);
		return new CreateQuestionPage();
	}

	public boolean createAssessmentButtonVisibility() {
		//System.out.println("Verify - Create Assessment button is visible.");
		return isElementDisplayed(createAssessmentElement);
	}
	
	public void navigateTo(String menu) {
		String menuLocator = String.format(leftPanelMenuLocator, menu);
		clickOnElement("xpath",menuLocator,true);
	}
}
