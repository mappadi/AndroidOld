package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWASubscriptionPages {

	public static By objApply = By.xpath("//div[text()='APPLY']");

	// Selected Subscription Plan Type
	public static By objSelectedSubscriptionPlanType = By.xpath(
			"//*[contains(@class, 'planDescription') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class, 'noSelect subscriptionPlanCard active')]]");

	// Continue Btn
	public static By objContinueBtn = By.xpath("//span[contains(text(), 'Continue')]");

	// Account Info Page

	// Account Info tab Highlighted
	public static By objAccountInfoHighlighted = By.xpath("//div[contains(text(),'Account Info')]");

	// Selected Pack Text
	public static By objSelectedPackText = By
			.xpath("//div[contains(@class, 'selectPackDetails')]/p[contains(text(), 'Selected Pack')]");

	// Selected Pack Name
	public static By objSelectedPackName = By.xpath("//div[contains(@class, 'packName')]/p");

	// Selected Pack Description
	public static By objSelectedPackDescription = By
			.xpath("//div[contains(@class, 'packName')]/span[contains(@class, 'description')]");

	// Selected Pack Duration
	public static By objSelectedPackDuration = By.xpath("//div[contains(@class, 'packDetails')]//*[@class='period']");

	// Proceed Btn Disabled
	public static By objProceedBtnNotHighlighted = By.xpath(
			"//button[contains(@class, 'noSelect buttonGradient') and @disabled]/span[contains(text(),'PROCEED')]");

	// Enter Email ID OR Mobile Number Error message
	public static By objEnterEmailIDErrorMsg = By.xpath("//div[contains(text(), 'Enter Email ID OR Mobile Number')]");

	// Password Field Visible Text
	public static By objPasswordFieldVisible = By
			.xpath("//input[contains(@name, 'inputPassword') and contains(@type, 'text')]");

	// Password Eye Icon disabled
	public static By objPasswordEyeIconDisabled = By
			.xpath("/span[contains(@class, 'noSelect eyeIcon iconNavi-ic_visibility')])");

	// Password Eye Icon Enabled
	public static By objPasswordEyeIconEnabled = By
			.xpath("/span[contains(@class, 'noSelect eyeIcon iconNavi-ic_visibility openEye')]");

	// Password Minimum characters error message
	public static By objPasswordMinimumCharErrorMsg = By
			.xpath("//span[contains(@class, 'error') and contains(text(), 'Minimum 6 characters')]");

	// Password incorrect error message
	public static By objPasswordWrongErrorMsg = By.xpath(
			"//span[contains(@class, 'error') and contains(text(), 'The email address and password combination was wrong during login')]");

	// Forgot Password Link
	public static By objForgotPasswordLink = By
			.xpath("//span[contains(@class, 'forgotPassword') and contains(text(), 'Forgot Password')]");

	public static By objProceedBtn = By.xpath("//div[@class='popupBtn']");

	// Payment Page

	// Payment tab Highlighted
	public static By objPaymentHighlighted = By.xpath("//li[@class='active']/div[contains(text(),'Payment')]");

	// Account Info Text
	public static By objAccountInfoText = By
			.xpath("//div[contains(@class,'accountInfoPlanCard')]//p[@class='autoPopulatedDetail']");

	// Selected Pack Name
	public static By objAccountInfoDetails = By.xpath(
			"//div[contains(@class, 'accountInfoPlanCard')]/div[contains(@class, 'details')]/p[contains(@class, 'autoPopulatedDetail')]");

	// Credit Card radio Btn
	public static By objCreditCardRadioBtn = By.xpath(
			"//label[contains(@for, 'Credit')]/span[contains(text(),'Credit Card') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'radioStyle')]]");

	// Credit Card radio Btn
	public static By objDebitCardRadioBtn = By.xpath(
			"//label[contains(@for, 'Debit')]/span[contains(text(),'Debit Card') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'radioStyle')]]");

	// PayTM radio Btn
	public static By objPayTMRadioBtn = By.xpath(
			"//label[contains(@for, 'PayTM')]/span[contains(@class,'paytmImg') and (./preceding-sibling::* | ./following-sibling::*)[contains(@class,'radioStyle')]]/img[contains(@alt,'PayTM')]");

	// Recurrence Message
	public static By objRecurrenceMessage = By.xpath(
			"//div[contains(@class,'recurrenceNote')]/p[contains(text(), 'You will be charged INR 99 every billing cycle until you cancel.')]");

	// Continue Btn Disabled
	public static By objContinueBtnDisabled = By.xpath(
			"//div[contains(@class,'continueButton')]//button[contains(@class, 'noSelect buttonGradient null') and contains(@disabled,'')]");

	// Continue Btn Enabled
	public static By objContinueBtnEnabled = By.xpath(
			"//div[contains(@class,'continueButton')]//button[contains(@class, 'noSelect buttonGradient null') and contains(@enabled, '')]");

	// PayTM Page

	// PayTm Wallet Radio Btn
	public static By objPaytmWalletOption = By.xpath("//img[contains(@alt, 'Paytm')]");

	// Default Selected Pack
	public static By objDefaultSelectedPack = By
			.xpath("//div[contains(@class, 'upgradeCard active')]//span[contains(@class, 'price')]");

	// Enter Password Popup
	public static By objGetPremiumCTAInPlater = By.xpath("//div[contains(@class,'teaser-grid2')]//button");

	public static By objSubscribepopup = By
			.xpath("//div[contains(@class,'ReactModal__Content ReactModal__Content--after-open popupModal')]");

	public static By objGetPremiumPopupPlan = By
			.xpath("//div[contains(@class,'upgradeCard active')]//p[contains(@class,'planDescription')]");

	public static By objGetPremiumPopipProceed = By.xpath("//div[contains(text(),'PROCEED')]");

	public static By objadhocPopup = By.xpath("//*[@class='__ADORIC__1 __ADORIC__  ']");

	public static By objadhocPopupRegestrationScreen = By.xpath("//h1[contains(text(),'Sign up for FREE')]");

	public static By objadhocPopupbn = By.xpath("//*[@nodeName='H1']");

	public static By objadhocPopupCloseBtn = By.xpath(".//div[@title='close lightbox']");
	public static By objadhocPopupArea = By
			.xpath(".//div[@class='element-shape closeLightboxButton editing adoric_element']");
	public static By objadhocPopupSignUpBtn = By.xpath("//button[contains(text(),'Button')]");
	public static By adoricCloseBtn = By.xpath("//div[contains(@class,'closeLightboxButton')]");

//ZEE5 Logo
	// public static By objZEE5Logo = By.xpath("//div[contains(@class,'zeeLogo
	// noSelect')]/img[contains(@title,'ZEE5 Logo')]");
	public static By objZEE5Logo = By.xpath("//*[contains(@class,'zeeLogo')]//img[@title=\"ZEE5 Logo\"]");
// Popup Close Button
	public static By objPopupCloseButton = By.xpath("//div[contains(@class,'noSelect closePupup')]");

//Code Applied Successfully message
	public static By objAppliedSuccessfullyMessage = By.xpath("//div[contains(text(),'Applied Successfully')]");

	public static By objSearchResultPremiumContent = By.xpath("//div[@class='cardPremiumContent']/parent::*");

	public static By objPopup99Plan = By
			.xpath("//p[contains(@class, 'planDescription')]//span[contains(@class, 'currency')]/span[text()='99']");

	// Get Premium popup close button
	public static By objGetPremiumPopupCloseButton = By.xpath(
			"//div[@class='noSelect closePupup iconInitialLoad-ic_close' and (./preceding-sibling: | ./following-sibling::*)[contains(@class,'popupContent upgradePopupContent')]/h2[contains(text(),'Get premium')]]");

//	=====================================================================================================

	public static By objSybscribeNowOnPlayerBtn = By.xpath("//SPAN[@text='Subscribe Now']");

	public static By objPackTypes = By.xpath("(//div[@class='categoryContainer']//child::*[@class='noSelect'])");

	public static By objPaymentFailure = By.xpath("h2[.='Payment Failure']");

	public static By objPaymentFailureCloseBtn = By
			.xpath("//div[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objPackTitle = By.xpath("(//div[@class='planDescription'])");

	public static By objPackAmount2 = By.xpath("(//p[@class='currency'])[2]");

	public static By objPackAmount = By.xpath("(//p[@class='currency'])");

	public static By objPackAmount1 = By.xpath("(//p[@class='currency'])[1]");

//	MANASA
	public static By objPackType(int i) {
		return By.xpath("(//div[@class='categoryContainer']//child::*[@class='noSelect'])[" + i + "]");
	}

	// ZEE5 Subscription Text
	public static By objZEE5Subscription = By
			.xpath("//*[contains(text(),'ZEE5 Subscription') and contains(@class,'pageTitleSmall')]");

	public static By objSubscribeBtnTopHeader = By
			.xpath("//*[contains(@class,'subscribeBtn noSelect')]//span[contains(text(), 'Subscribe')]");

	public static By objSelectPackHighlighted = By.xpath("//*[@class='active']/div[contains(text(),'Select Pack')]");

	public static By objPackCategoryTabSelected = By
			.xpath("//*[contains(@class,'active')]/*[contains(@class, 'noSelect')]");

	public static By objPackDescription = By.xpath("//*[@class='featureDesc']");

	public static By objSelectedSubscriptionPlanAmount = By
			.xpath("//*[contains(@class, 'noSelect subscriptionPlanCard active')]//*/*[contains(@class, 'currency')]");

	public static By objSelectedSubscriptionPlanDuration = By
			.xpath("//*[contains(@class, 'noSelect subscriptionPlanCard active')]/*/*[contains(@class, 'duration')]");

	public static By objHaveACode = By.xpath("//*[contains(@placeholder,'Have a Code?')]");

	public static By objApplyBtn = By
			.xpath("//*[contains(@class, 'noSelect applyPromo') and contains(text(), 'APPLY')]");

	public static By objAppliedCodeFailureMessage = By.xpath("//*[@class='applyPromo applyFailure']");

	public static By objEmailIDTextField = By.xpath("//*[@id='textField' and @name='userName']");

	public static By objProceedBtnHighlighted = By
			.xpath("//*[contains(@class, 'noSelect buttonGradient')]/span[contains(text(),'PROCEED')]");

	public static By objEnterPasswordPopupTitle = By.xpath("//*[contains(@class, 'popupDesc bigTitle')]");

	public static By objProceedBtnDisabled = By
			.xpath("//*[contains(@class, 'popupBtn disableButton') and contains(text(), 'PROCEED')]");

	public static By objPasswordFieldHidden = By
			.xpath("//*[contains(@name, 'inputPassword') and contains(@type, 'password')]");

	public static By objProceedBtnEnabled = By
			.xpath("//*[contains(@class, 'popupBtn') and contains(text(), 'PROCEED')]");

	public static By objCreditAndDebitCardBtn = By.xpath(
			"//*[@class='linearLayout sidebarItem_object ']//following-sibling::*//article[contains(text(),'Credit / Debit Card')]");
	public static By objWallets = By.xpath(
			"//div[@class='linearLayout sidebarItemWrap sidebarItemWrap_wallet ']//article[contains(text(),'Wallets')]");
	public static By objPaytmWallet = By.xpath(
			"//*[@class='linearLayout sidebarItem_object ']//following-sibling::*//article[contains(text(),'Paytm')]");
	public static By objPaymentPageProceedBtn = By
			.xpath("//button[contains(@class, 'noSelect buttonGradient ')]/span[contains(text(),'PROCEED')]");
	public static By objEnterCreditAndDebitCardDetails = By
			.xpath("//*[contains(@class,'editText') and contains(@placeholder,'Enter card number here')]");
	public static By objJusPayIframe = By.xpath("//iframe[@id='juspay_iframe']");
	public static By objLinkPaytm = By.xpath("//article[contains(text(), 'Link PAYTM Wallet')]");
	public static By objNetbanking = By.xpath("//article[contains(text(),'Netbanking')]");
	public static By objAmazonPay = By.xpath("//article[contains(text(),'Amazon Pay')]");
	public static By objMobikwik = By.xpath("//article[contains(text(),'Mobikwik')]");
	public static By objBanks = By.xpath("(//div[@class='horizontalScrollView ']//div)//div//child::*[2]//article");
	public static By objAmazonPayProceedToPay = By
			.xpath("//div[@id='305']//article[contains(text(), 'Proceed to pay')]");
	public static By objMobikwikProceedToPay = By
			.xpath("//div[@id='328']//article[contains(text(), 'Proceed to pay')]");
	public static By objCardNumber = By.xpath("//article[contains(text(),'Card Number')]");
	public static By objCVV = By.xpath("//article[contains(text(),'CVV')]");
	public static By objExpiry = By.xpath("//article[contains(text(),'Expiry')]");
	public static By objProceedToPay = By.xpath("(//article[contains(text(), 'Proceed to pay')])[6]");

	public static By objProceedBtnInSubscriptionPage = By.xpath("//*[@class='noSelect buttonGradient ']");

	public static By objLoginLinkInPlayer = By.xpath("(//*[@class='login-link'])[2]");

	public static By objPasswordPopupInSubscriptionPage = By.xpath("//*[@class='popupContent parentalControlPopup']");
	public static By objPasswordField = By.xpath("//*[@type='password']");
	public static By objProceedButtonInPassword = By.xpath("//*[@class='popupBtn']");
	public static By objAccountDetailInSubscription = By.xpath("//*[@class='autoPopulatedDetail']");

	public static By objPaytmProceedToPay = By.xpath("(//article[contains(text(), 'Proceed to pay')])[2]");
	public static By objCreditDebitProceedToPay = By
			.xpath("//div[@class='textView ']//article[contains(text(),'Proceed to pay')]");

	public static By objarrowbtn = By.xpath("//div[@class='linearLayout sidebarItem_object ']");


	public static By objLoginSectionInPopup = By.xpath("//div[contains(@class,'loginContainer')]");

	public static By objLoginButtonInPopup = By.xpath("//div[contains(@class,'popupBtn accentBtn')]");

	public static By objMobileCreditDebitCardOption = By.xpath("//*[@text='Credit / Debit Card']");
	public static By objMobileCreditDebitCardRecurrenceMessage = By
			.xpath("//*[@text='You will be charged every billing cycle until you cancel']");
	public static By objMobileAddCardText = By.xpath("//*[@text='Add Card']");
	public static By objMobileCardNumberText = By.xpath("//*[@text='Card Number']");
	public static By objMobileCardNumberEditBox = By.xpath("//*[@resource-id='987654321']");
	public static By objMobileExpiryText = By.xpath("//*[@text='Expiry']");
	public static By objMobileCVVText = By.xpath("//*[@text='CVV']");
	public static By objMobileProceedToPayButton = By.xpath("//*[@text='Proceed to pay']");
	public static By objMobileWalletsOption = By.xpath("//*[@text='Wallets']");
	public static By objMobilePaytmOption = By.xpath("//*[@text='Paytm']");
	public static By objMobileLinkPaytmOption = By.xpath("//*[@text='Link PAYTM Wallet']");
	public static By objMobilePaytmNumberField = By.xpath("//*[@class='android.widget.EditText']");
	public static By objMobilePaytmSendOTP = By.xpath("//*[@text='SEND OTP']");
	public static By objPackType = By.xpath("//div[@class='categoryContainer']//child::*[@class='noSelect']");

	public static By objMobilePayTMRecurrenceMessage = By
			.xpath("//*[@text='You will be charged every billing cycle until you cancel']");

	// Get Premium Popup Title
	public static By objGetPremiumPopupTitle = By
			.xpath("//h2[contains(@class,'popupTitle bigTitle')]//p[text()='Subscribe']");

	// Popup Proceed Btn
	public static By objPopupProceedBtn = By.xpath("//div[contains(text(),'PROCEED') and contains(@class,'popupBtn')]");
	
	// Get Subscribe Pop Up Title
	public static By objSubscribePopupTitle = By.xpath("//div[contains(@class,'popup')]//*[text()='Subscribe']");
	
	public static By objHaveACodeCloseBtn = By.xpath("//div[contains(@class,'applyPromo')]//following-sibling::span[contains(@class,'close')]");
}
