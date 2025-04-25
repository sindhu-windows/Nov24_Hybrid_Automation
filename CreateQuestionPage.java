package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.ControlActions25;
import io.qameta.allure.Allure;

public class CreateQuestionPage extends ControlActions25 {
	
	@FindBy(xpath = "//p[text()='Choose Question Type']")
	private WebElement chooseQuestionTypeHeaderElement;

	@FindBys(
			@FindBy(xpath = "//div[p[contains(@class,'css-2dyhle')]]/button/following-sibling::p")
	)
	private List<WebElement> allQuestionTypeTextElement;
	
	@FindBy(xpath = "//div[contains(@class,'MuiBox-root css-rvp98e')]/p")
	private WebElement isCreateQuestPageLoadedElement;
	
	@FindBy(xpath = "//input[@id='questionTitle']")
	private WebElement problemTitleElement;
	
	@FindBy(xpath = "//div[contains(@class, 'sun-editor-editable')]")
	private WebElement problemStatementElement;
	
	@FindBy(xpath = "//button[contains(text(), 'Add Option')]")
	private WebElement addOptionElement;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Type a label')]")
	private WebElement labelElement;
	
	@FindBy(xpath = "//input[@id='skills']")
	private WebElement skillElement;
	
	@FindBy(xpath = "//button[text()='Publish']")
	private WebElement publishBtnElement;
	
	@FindBy(id = "score")
	private WebElement scoreInputElement;
	
	@FindBy(xpath = "//div[text()='Question published successfully']")
	private WebElement successMsgElement;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelBtnElement;
	
	@FindBy(xpath = "//span[text()='Library']")
	private WebElement libraryBtnElement;
	
	@FindBy(xpath = "//button[contains(text(),'My Library')]")
	private WebElement myLibraryBtnElement;
	
	@FindBy(xpath = "//input[@placeholder='Search Question']")
	private WebElement searchQuestionElement;
	
	@FindBy(xpath = "//p[contains(@class,'css-1p1e1fa')][1]")
	private WebElement actualTitleElement;
	
	@FindBy(xpath = "//p[contains(@class,'css-1p1e1fa')][1]/../../following-sibling::div[2]//p")
	private WebElement actualStatementElement;
	
	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement dashboardBtnElement;

	private String questionTypeLocator = "//p[text()='%s']/preceding-sibling::button";
	private String difficultyLevelLocator = "//p[text()='%s']";
	private String optionTextLocator = "//input[@placeholder='Option %d']";
	private String radioBtnLocator = "option-radio-%d";
	private String selectSkillLocator = "//li[text()='%s']";
	private String topicLocator = "//p[text()='%s']";
	
	public CreateQuestionPage() {
		PageFactory.initElements(driver, this);
	}
		
	public boolean chooseQuestionType() {
		return isElementDisplayed(chooseQuestionTypeHeaderElement);
	}

	public boolean isQuestionTypeBtnDisplayed(String questionType) {
		String questionTypeLocator = String.format(this.questionTypeLocator, questionType);
		boolean flag = isElementDisplayed("xpath", questionTypeLocator, true);
		Allure.step("Is Question type btn displayed: "+ flag);
		return flag;
	}

	public int countOfVisibleQuestionType() {
		int countOfQuestionType = getAllElementCount(allQuestionTypeTextElement, true);
		Allure.step("Counting all the question type: "+countOfQuestionType);
		return countOfQuestionType;
	}

	public List<String> getAllQuestionTypeText() {
		List<String> allQuestions = getAllElementText(allQuestionTypeTextElement, true);
		Allure.step("All Questions: "+allQuestions);
		return allQuestions;
	}

	public void clickOnQuestionType(String questionType) {
		String questionTypeLocator = String.format(this.questionTypeLocator, questionType);
		super.clickOnElement("xpath", questionTypeLocator, false);
		Allure.step("Click on question type: "+questionType);
	}

	public boolean isCreateQuestPageLoaded() {
		return isElementDisplayed(isCreateQuestPageLoadedElement);
	}

	public void setProblemTitle(String textToEnter) {
		setText(problemTitleElement, true, textToEnter);	
		Allure.step("Setting problem title: "+textToEnter);
	}

	public void setProblemStatement(String textToEnter) {
		setText(problemStatementElement, true, " ");
		setText(problemStatementElement, true, textToEnter);
		Allure.step("Setting problem statment: "+textToEnter);
	}

	public void maxScore(int numToEnter) {
		setText(scoreInputElement, false, String.valueOf(numToEnter));
		Allure.step("Setting Max Score as: "+numToEnter);
	}

	public void maxScore(String numToEnter) {
		setText(scoreInputElement, false, numToEnter);
		Allure.step("Setting Max Score as: "+numToEnter);
	}

	public void selectDifficultyLevel(String diffLevel) {
		String difficultyLevelLocator = String.format(this.difficultyLevelLocator, diffLevel);
		super.clickOnElement("xpath", difficultyLevelLocator, true);
		Allure.step("Setting difficulty level as: "+diffLevel);
	}

	public void setOptionAtIndex(int index, String optionText) {
		String choiceLocator = String.format(optionTextLocator, index);
		super.clickOnElement("xpath", choiceLocator, true);
		setText("xpath", choiceLocator, true, optionText);
		Allure.step("Setting option at index: "+index +" and option is: "+optionText);
	}

	public void clickOnAddOption() {
		super.clickOnElement(addOptionElement);
	}

	protected void clickOnElementusingJs(String locatorType, String locatorValue, boolean isWaitReq) {
		je.executeScript("document.getElementById('" + locatorValue + "').click();");
	}
	
	@Override
	protected void clickOnElement(WebElement e) {
	    wait.until(ExpectedConditions.visibilityOf(e));
	    je.executeScript("arguments[0].click();", e);
	}

	public void selectCorrectAnsAtIndex(int index) {
		String locator = String.format(radioBtnLocator, index);
		clickOnElementusingJs("id", locator, true);
		Allure.step("Select correct answer at index : "+index);
	}

	public String getOptionAtIndex(int index) {
		String locator = String.format(optionTextLocator, index);
		return getInputElementText("XPATH", locator, false);
	}

	public void setLabel(String labelText) {
		setText(labelElement, false, labelText);
		Allure.step("Setting label : "+labelText);
	}

	public void setSkill(String skillName) {
		setText(skillElement, false, skillName);
		String locator = String.format(selectSkillLocator, skillName);
		super.clickOnElement("xpath", locator, true);
		Allure.step("Setting Skills : "+skillName);
	}

	public void selectTopics(String option1) {
		String topicLocator = String.format(this.topicLocator, option1);
		super.clickOnElement("xpath", topicLocator, true);
		Allure.step("Setting Topics : "+option1);
	}

	public void clickOnPublishbtn() {
		super.clickOnElement(publishBtnElement);
	}

	public boolean successMsgValidation() {
		return isElementDisplayed(successMsgElement);
	}

	public void clickOnCancelBtn() {
		super.clickOnElement(cancelBtnElement);
	}

	/*
	 * public void clickOnLibraryBtn() { super.clickOnElement(libraryBtnElement); }
	 * 
	 * public String libraryUrlCheck() { return getCurrentUrl(); }
	 * 
	 * public void clickOnMyLibraryBtn() {
	 * 
	 * try { clickOnElement(myLibraryBtnElement); } catch
	 * (StaleElementReferenceException e) { clickOnElement(myLibraryBtnElement); }
	 * 
	 * }
	 * 
	 * public void setTextInSearchboxLibrary(String title) {
	 * setText(searchQuestionElement,true,title); }
	 * 
	 * public boolean verifyIsQuestionDisplayed(String title, String statement) {
	 * boolean flag=false;
	 * 
	 * String actualTitle = getElementText(actualTitleElement,true); String
	 * actualStatement = getElementText(actualStatementElement,true);
	 * if(actualTitle.trim().equals(title.trim()) &&
	 * actualStatement.trim().equals(statement.trim())) { flag = true; } return
	 * flag; }
	 * 
	 * public void clickOnDashboardBtn() {
	 * super.clickOnElement(dashboardBtnElement); }
	 */

}
