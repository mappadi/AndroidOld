package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

public class AMDEditProfileScreen {

	public static By objBackButton = By.xpath("//*[@id='icon_back']");
	public static By objEditProfileTitle = By.xpath("//*[@text='Edit Profile']");
	public static By objFirstNameField = By.xpath("//*[@id='first_name_input']");
	public static By objLastNameField = By.xpath("//*[@id='last_name_input']");
    public static By objEmailIdTextLabel = By.xpath("//*[@id='txt_email_label']");
	public static By objEmailIDField = By.xpath("//*[@id='txt_email']");
	public static By objMobileNoField = By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='country_container']]");
    public static By objMobileNumberField = By.xpath("//*[@id='edit_email']");
	public static By objMobileNoCountryDropDown = By.xpath("//*[@id='countryDropDownIcon']");
    public static By objMobileNOCountry = By.xpath("//*[@id='tv_countryDropDown']");
    public static By objDifferentCountries(int index) {
    	return By.xpath("(//*[@id='selector_content'])["+index+"]");
    }
    public static By objClosedropDownBtn = By.xpath("//*[@resource-id='com.graymatrix.did:id/selector_selection']");
    public static By objDOBTxtField = By.xpath("//*[@id='dobTextView']");
    public static By objMonthField = By.xpath("(//*[@id='numberpicker_input'])[1]");
    public static By objDayField = By.xpath("(//*[@id='numberpicker_input'])[2]");
    public static By objYearField = By.xpath("(//*[@id='numberpicker_input'])[3]");
    public static By objOkBtnCalender = By.xpath("//*[@resource-id='android:id/button1']");
    public static By objCalenderPopUp = By.xpath("//*[@resource-id='android:id/datePicker']");
	public static By objDOBCalenderBtn = By.xpath("//*[@id='dobClalander']");
	public static By objGederTxtField = By.xpath("//*[@resource-id='com.graymatrix.did:id/genderTextView']");
    public static By objGenderDropdown = By.xpath("//*[@id='genderDropDown']");
    public static By objMale = By.xpath("//*[@text='Male']");
	public static By objFemale = By.xpath("//*[@text='Female']");
    public static By objSelectGenderTitle = By.xpath("//*[@text='Select your gender']");
    public static By objSelecteGender = By.xpath("//*[@id='selectionImageSelector']");
    public static By objSaveChanges = By.xpath("//*[@id='btn_edit_save']");
    public static By objToastMessage = By.xpath("//*[@resource-id='android:id/message']");
    public static By objEmailFieldForEmailLogin = By.xpath("//*[@resource-id='com.graymatrix.did:id/edit_email']");
}
