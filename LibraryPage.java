package pages;

import base.ControlActions25;

public class LibraryPage extends ControlActions25{
	
	private String libraryOptionLocator = "//button[starts-with(text(),'%s')]";
	
	public boolean waitForPageLoad() {
		return urlContainsString("library");
	}
	
	public int getQuestionCount(String libraryOption) {
		String optionLocator = String.format(libraryOptionLocator, libraryOption);
		String questCountText = getElementText("xpath",optionLocator,true); // All (2256)
		String temp = questCountText.split("\\(")[1]; //(2256)
		int count = Integer.parseInt(temp.substring(0, temp.length()-1));
		return count;
	}
}
