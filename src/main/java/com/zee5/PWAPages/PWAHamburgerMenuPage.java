package com.zee5.PWAPages;

import org.openqa.selenium.By;

public class PWAHamburgerMenuPage {

	/**
	 * Profile Page
	 */
//	Profile Page title
	public static By objProfilePageTitleTxt = By.xpath("//h1[@class='pageTitle']");

//	Profile Image Icon
	public static By objProfilePageIcon = By.xpath("//div[@class='userImg']");

//	Profile Name
	public static By objProfilePageNameTxt = By.xpath("//div[@class='userName ']");

//	Profile Email Id
	public static By objProfilePageUserIdTxt = By.xpath("//div[@class='userId']");

//	Profile Edit Profile Button
	public static By objProfileEditBtn = By.xpath("//div[@class='profileEdit noSelect']");

//	Subscription Button
	public static By objSubscritionBtn = By.xpath("//div[@class='gridWrap']");

//	Change Password Button
	public static By objChangePasswordBtn = By.xpath("//div[@class='noSelect btnContnet']");

//	Move Top Arrow Icon
	public static By objMoveTopArrowIcon = By.xpath("//div[@class='iconNavi-ic_arrow_back']");

//	Play Store Button
	public static By objPlayStoreBtn = By.xpath("//img[@alt='Google Play']");

//	App Store
	public static By objAppStoreBtn = By.xpath("//img[@alt='App Store']");

//	Social media Icon
	public static By objFcebookIcon(String SocialMediaIcon) {
		return By.xpath("//a[contains(.,'" + SocialMediaIcon + "')]");
	}

//	About Us, Help Center, Privacy Policy, Terms of use
	public static By objAboutHelpPrivacyTerms(String text) {
		return By.xpath("//div[@class='footerMenu']//div[.='" + text + "']");
	}

//	CopyRight Text
	public static By objCopyRightTxt = By.xpath("//div[@class='copyRightTxt']");

	/**
	 * Hamburger menu
	 */

//	Profile Icon
	public static By objProfileIcon = By.xpath("//div[@class='iconInitialLoad-ic_profile profilePic']");

//	Profile Name 
	public static By objProfileTxt = By.xpath("//div[@class='userEmail ']");

//	Profile Expander Icon
	public static By objProfileExpanderIcon = By.xpath("//div[@class='arrow iconInitialLoad-ic_viewall']");

//  Hamburger menu closed
	public static By objHamburgerClose = By.xpath("//button[contains(text(),'Close Menu')]");

//	Login Button
	public static By objLoginBtn = By.xpath("//span[@class='loginButton borderGradient ']//span[.='Login']");

//	SignUp for free button
	public static By objSignUpForFree = By.xpath("//span[@class='loginButton ']//span[.='Sign up for FREE']");

//	Home Button
	public static By objHomeBtn = By.xpath("//div[@class='noSelect menuItem  active']/div[.='Home']");

//	Explore button
	public static By objExploreBtn = By.xpath("//h5[.='Explore']");

//	Explore Down arrow icon
	public static By objDownArrow(String expanderName) {
		return By.xpath("//h5[.='" + expanderName + "']//span[contains(@class,'iconNavi-ic_expand_less')]");
	}

//	Check Explore down arrow icon is Active
	public static By objActiveDownArrow(String expanderName) {
		return By.xpath("//h5[.='" + expanderName + "']//span[contains(@class,'iconNavi-ic_expand_less active')]");
	}

//	Items of Explore button
	public static By objExploreItemBtn(String itemName) {
		return By.xpath("//*[@class='bm-item primaryMenu']//*[.='" + itemName + "']");
	}

//	Plans button
	public static By objInsideItemsBtn(String itemName) {
		return By.xpath("//div[.='" + itemName + "']");
	}

//	Info
	public static By objInsideItemsOfInfoBtn(String itemName) {
		return By.xpath("//div[@class='bm-item primaryMenu']//div[.='" + itemName + "']");
	}

//	Version Text
	public static By objVersionTxt = By.xpath(".//*[@class=\"versionText\"]");

//	Terms of Use / Privacy Policy
	public static By objTermsOfUse = By.xpath("//div[@class='termsPrivacyWrap']");

//	Close Icon
	public static By objCloseIcon = By.xpath("//button[.='Close Menu']");

//	Zee Logo
	public static By objZeeLogo = By.xpath("//div[@class='burgerZeeLogo']/img[@alt='ZEE5 Logo']");

	/**
	 * My Watchlist
	 */
//	Watchlist Icon
	public static By objWatchlistIcon = By.xpath("//div[@class='iconAlerts-ic_no_watchlist_expanded']");

//	Watchlist Text
	public static By objWatchlistTxt = By.xpath("//div[@class='textArea']");

//	Watchlist Tab Navigation
	public static By objTabNavigation(String tabName) {
		return By.xpath("//div[.='" + tabName + "' and //div[contains(@class,'noSelect tabMenuItem') ]]");
	}

//	content Title
	public static By objContentTitle = By.xpath("//h3[@class='cardTitle overflowEllipsis ']");

//	Episode Number
	public static By objEpisodeNumber = By.xpath("//div[@class='dateTime']");

//	Close Icon
	public static By objWatchlistCloseIcon = By.xpath("//span[@class='noSelect iconInitialLoad-ic_close']");

//	Remove All button
	public static By objRemoveAllBtn = By.xpath("//span[.='Remove All']");

//WATCHLIST
	public static By objMyAccountOptionsText(String option) {
		return By.xpath("//*[contains(@class,'pageTitle') and text()=\"" + option + "\"]");
	}

//Edit Profile Text
	public static By objEditProfileText = By.xpath("//*[@class='pageTitle editPageTitle']");

//Edit profile FirstName
	public static By objEditProfileFirstName = By.xpath("//*[@name='firstName']");

//Edit Profile Save changes
	public static By objEditProfileSavechangesBtn = By.xpath("//*[@class='noSelect buttonGradient ']");

//Edit  Profile Go back
	public static By objEditProfileGoBackBtn = By.xpath("//*[@class='noSelect btnContnet']");

	// Edit Profile Changes saved
	public static By objEditProfileChangesSaved = By.xpath(" @class='toastMessage']");

	// Change password Text
	public static By objChangePasswordText = By.xpath("//*[@class='pageTitle changePasswordTitle']");

	// Change Old password
	public static By objChangeOldPassword = By.xpath("//*[@name='oldPwd']");

	// Change passowrd
	public static By objNewPassword = By.xpath("//*[@name='newPwd']");

	// Change passowrd
	public static By objConfirmNewPassword = By.xpath("//*[@name='confirmPwd']");

	// UpdatePasswordBtnHighlighted
	public static By objUpdatePasswordBtnHighlighted = By.xpath("//*[@class='noSelect buttonGradient ']");

	// My plan text
	public static By objMyplanText = By.xpath("//h3[@class='planTitle']");

	// My active plan
	public static By objMyActivePlan = By.xpath("//div[@class='myPlanWrapper']");

	public static By objMYSubscriptionActiveStatus = By.xpath("//div[@class='metaItem']//p[2]");

	public static By objNoTransaction = By.xpath("//div[.='No Transaction']");
	public static By objMyTransactionDate = By.xpath("//p[@class='date']");

	public static By objMyTransactionPackStatus = By.xpath("//div[@class='billRow']//p[2]");

	// Buy Subscription Option
	public static By objBuySubscriptionOption = By
			.xpath("//*[contains(@class,'menuItem') and contains(text(),'Buy Subscription')]");

	public static By objProfileIconWEB = By.xpath("(//button[.='Open Menu'])[2]");
	public static By objMyAccountOption = By.xpath("//div[contains(@class,'menuForMyAccount')]");

	public static By objContTitleTextCarousel(String text) {
		return By.xpath(
				"//div[@class='slick-slide slick-active slick-current']//*[contains(@class,'legendTitle') and contains(text(),'"
						+ text + "')]");
	}

	public static By objAfterSelectedLanguage = By
			.xpath("//div[@class='checkboxWrap checkedHighlight']//label[@for='select_hi']");
	public static By objSelectLanguage = By.xpath("//label[@for='select_hi']");
	public static By objApplyButtonInContentLangugaePopup = By.xpath("//div[@class='popupBtn noSelect']");
	public static By objCancelButtonInContentLangugaePopup = By.xpath("//div[@class='popupBtn accentBtn noSelect']");
	public static By objAuthenticateDevice = By.xpath("//div[contains(text(),'Authenticate Device')]");
	public static By objParentControlPageTitle = By.xpath("//h2[contains(@class,'pageTitle')]");
	public static By objNoRestrictionSelected = By.xpath("//div[contains(text(),'No Restrictions')]//child::*");
	public static By objRestrictAll = By.xpath("//div[contains(text(),'Restrict All Content')]");
	public static By objParentalLockPin1 = By.xpath("//input[contains(@name,'pin1')]");
	public static By objParentalLockPin2 = By.xpath("//input[contains(@name,'pin2')]");
	public static By objParentalLockPin3 = By.xpath("//input[contains(@name,'pin3')]");
	public static By objParentalLockPin4 = By.xpath("//input[contains(@name,'pin4')]");
	public static By objParentalLockPopUpInPlayer = By.xpath("//div[@id='childDivId']");
	public static By objParentalLockPin1player = By.xpath("//input[@id='parentLockId1']");
	public static By objParentalLockPin2player = By.xpath("//input[@id='parentLockId2']");
	public static By objParentalLockPin3player = By.xpath("//input[@id='parentLockId3']");
	public static By objParentalLockPin4player = By.xpath("//input[@id='parentLockId4']");
	public static By objParentalLockNoRestrictionOption = By.xpath("//div[contains(text(),'No Restrictions')]");
	public static By objAuthenticationText = By.xpath("//h1[contains(@class,'heading')]");
	public static By objAuthenticationButtonNotHighlighted = By
			.xpath("//button[contains(@class,'noSelect buttonGradient null')]");
	public static By objAuthenticationField = By.xpath("//input[contains(@name,'code')]");
	public static By objCloseHamburgerMenu = By.xpath("//button[contains(text(),'Close Menu')]");
	public static By objPlanInHamburger = By.xpath("//div[contains(@class,'menuTitle noSelect menuForPlans')]");

	public static By objExploreOptions(String expanderName) {
		return By.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'"
				+ expanderName + "')]");
	}

	public static By objWEBMyAccount = By.xpath("(//h5[.='My Account'])[2]");
	public static By objMyTransactionAutoRenewalStatus = By.xpath("(//div[@class='billRow']//p[2])[5]");

	public static By objMyProfileOptionsWEB(String OptionName) {
		return By.xpath("//*[contains(@class,'noSelect menuItem')][contains(text(),'" + OptionName + "')]");
	}

	public static By objProfileIconInProfilePage = By.xpath(
			"//div[contains(@class,'bm-item profileMenuHeader')]//div[contains(@class,'iconInitialLoad-ic_profile profilePic')]");
	public static By objProfileTextWEB = By.xpath("//div[@class='noSelect pageLink' and text()='My Profile']");
	public static By objProfilePageNameTxtWEB = By
			.xpath("//div[contains(@class,'userDetails')]//div[contains(@class,'userName')]");
	public static By objEditProfileTextWEB = By.xpath("//h2[contains(@class,'pageSubTitle')]");
	public static By objChangePasswordTextWEB = By.xpath("//h2[contains(@class,'pageSubTitle')]");
	public static By objWebBuySubscriptionOption = By.xpath("//*[text()='Buy Subscription']");
	public static By objLanguageBtnWEB = By.xpath("//div[contains(@class,'languageBtn')]");
	public static By objContentLanguageWrapper = By.xpath("//div[@id='contentWrapLanguage']");
	// Display language btn
	public static By objDisplayLang = By.xpath("//div[contains(@class,'noSelect displaylanguageHeader')]");

	// Content language button
	public static By objContentLang = By.xpath("//div[contains(@class,'noSelect contentlangugaeHeader')]");

//		====================================================================================================

	public static By objKannadaSelectedLanguage = By
			.xpath("((//div[@class='checkboxWrap checkedHighlight'])[2])//child::*[@class='commonName']");

	public static By objSelectedContentLanguages = By
			.xpath("(//div[@class='checkboxWrap checkedHighlight'])//child::*[@class='commonName']");

	public static By objSubscriptionTeaserBanner = By.xpath("//div[@class='subscriptionBanner ']");

	public static By objEmptyStateScreen = By.xpath("//div[@class='iconAlerts-ic_no_transaction']");

	public static By objBrowseAllPacks = By.xpath("//div[contains(text(),'Browse All Packs')]");

	public static By objPackTitle = By.xpath("(//p[@class='packTitle'])");

	public static By objPackPrice = By.xpath("(//p[@class='packPrice'])");

	public static By objPackDuration = By.xpath("(//div[@class='billRow']//p[2])[3]");

	public static By objPackTitle1 = By.xpath("(//p[@class='packTitle'])[2]");

	public static By objPackPrice1 = By.xpath("(//p[@class='packPrice'])[2]");

	public static By objPackDuration1 = By.xpath("(//div[@class='billRow']//p[2])[8]");

	public static By objMyTransactionPackStatus1 = By.xpath("(//div[@class='billRow']//p[2])[6]");

	public static By objMyTransactionAutoRenewalStatus1 = By.xpath("(//div[@class='billRow']//p[2])[10]");

	public static By objZeeLogo1 = By.xpath("(//img[@alt='ZEE5 Logo'])[1]");

//	Shreenidhi -- PROFILE MODULE

	public static By objNoTranscationText = By.xpath("//div[@class='textArea']");

	public static By objSubscribitionPageActivePlan = By.xpath("//div[@class='subscriptionItem']");

	public static By objPasswordErrorText = By.xpath("//div[contains(text(),'Minimum 6 characters')]");

	public static By objEditProfileDOB = By.xpath("//span[@class='datePickerLabel']");

	public static By objEditProfileGender = By.xpath("//div[contains(text(),'Gender')]");

	public static By objEditProfileMobileNumber = By.xpath("//input[@name='userMobile']");

	public static By objEditProfileEmailField = By.xpath("//input[@name='userEmail']");

	public static By objTransactionPageGrid = By.xpath("//div[@class='transactionContents']");

	// About Us information
	public static By objAboutUsInfo = By.xpath("//div[@class='staticPageContainer']");

	// Hyperlink on About us screen
	public static By objHyperLink = By.xpath("//a[contains(text(),'www.zee5.com')]");

	// Help Center header in Help Center page
	public static By objHelpUsHeader = By.xpath("//div[contains(@class,'headerContent_h1')]");

	// categories of Help Center screen

	// Getting started category
	public static By objGettingStarted = By.xpath("//div[contains(@class,'zpicon-container')]//div[1]//div[1]//h3[1]");

	// My account category
	public static By objMyAccountInHelpCenter = By.xpath("//span[contains(text(),'My Account')]");

	// Quick links category
	public static By objQuickLinks = By.xpath("//div[contains(text(),'Quick Links')]");

	// FAQ under Getting started category
	public static By objFAQInGettingStarted = By
			.xpath("//*[@text='How do I watch ZEE5 on my television?  ' and @nodeName='A']");

	// Offer Terms and conditions
	public static By objOfferTermsAndConditions = By.xpath("//b[contains(text(),'Offer Terms & Conditions')]");

	// Terms and conditions in Terms of Use Screen
	public static By objTermsAndConditions = By.xpath("//body//ul[3]");

	// Write to us button in Help Center screen
	public static By objWritetous = By.xpath("//div[contains(@class,'feedback_cls')]//div[2]");

	// Contact Us page
	public static By objContactUs = By.xpath("//div[@class='headingTxt shiftLeft chgLeft']");

	// Select your country field
	public static By objSelectYourCountry = By.xpath("//span[@id='countrySpan']");

	// Auto filled country
	public static By objAutofilledcountry = By.xpath("//div[@class='dropdown selectedCountry']");

	// Drop down arrow
	public static By objDropDown = By.xpath("//button[@id='countryOptions']//span[@class='imgClass']");

	// Registered mobile number field
	public static By objRegisteredMobileNumber = By.xpath("//input[@id='userMobile']");

	// Auto filled country code
	public static By objCountryCode = By.xpath("//input[@id='countryCode']");

	// Email ID field
	public static By objEmailField = By.xpath("//input[@id='userEmail']");

	// Email Id Asterisk
	public static By objEmailIDAsterisk = By
			.xpath("//span[@class='email floating-label mobileMargin']//span[@class='asterisk'][contains(text(),'*')]");

	// Tell us more about your issue text
	public static By objText = By.xpath("//div[@class='cotentTxt alignElmDiv radioNextLine chgLeft']");

	// Content option
	public static By objContentOption = By.xpath("//span[@id='optImgRadio0']");
	// Product option
	public static By objProductOption = By.xpath("//span[@id='optImgRadio1']");

	// Enquiry option
	public static By objEnquiryOption = By.xpath("//label[@id='radioOpt2']");

	// Feedback option
	public static By objFeedbackOption = By.xpath("//label[@id='radioOpt3']");

	// Content as default option
	public static By objContentAsDefault = By.xpath("//input[@id='catOpt0']");

	// Select category field
	public static By objSelectCategory = By
			.xpath("//div[@class='dropdown selectedCategory removeBothPadding addpadding']");

	// Video not playing option as default
	public static By objVideoNotPlaying = By.xpath("//button[@id='options']");

	// Error message
	public static By objErrorMessage = By.xpath("//span[@class='error errorMsg']");

	// Error message field
	public static By objErrorMessageField = By.xpath("//input[@id='errMsg']");

	// Error message asterisk

	public static By objErrorMessageAsterisk = By
			.xpath("//span[@class='floating-label errorMargin']//span[@class='asterisk'][contains(text(),'*')]");

	// SUBMIT button
	public static By objSubmitButton = By.xpath("//button[@id='submitData']");

	// RESET button
	public static By objResetButton = By.xpath("//button[@id='resetData']");

	// Platform asterisk
	public static By objPlatformAsterisk = By
			.xpath("//span[@id='spanDyan0']//span[@class='asterisk'][contains(text(),'*')]");

	// SUBMIT button enabled
	public static By objSUBMITEnabled = By.xpath("//div[@class='btnSection']//div[3]");

	// FAQ's under My account category
	public static By FAQunderMyAcoount = By.xpath("//div[@id='layoutContainer']//div[2]//div[1]//div[1]");

	// FAQ's under Getting started
	public static By FAQunder = By.xpath("//div[contains(@class,'zpicon-container')]//div[1]//div[1]//div[1]//ul[1]");

	public static By objHelpSectioOptionsHeading(String text) {
		return By.xpath("//*[contains(text(),'" + text + "')]");
	}

	// *[@data-id='article_Title']
	public static By objArticleTitle = By.xpath("//*[@data-id='article_Title']");

	// About Us option in Kannada
	public static By objAboutUsinKannada = By.xpath("//a[text()='ನಮ್ಮ ಬಗ್ಗೆ']");
	// Terms of Use option in kannada
	public static By objTermsInKannada = By.xpath("//a[text()='ಬಳಕೆಯ ನಿಯಮಗಳು']");
	// Privacy Policy in Kannada
	public static By objPrivacyPolicyInKannadA = By.xpath("//a[text()='ಗೌಪ್ಯತೆ ನೀತಿ']");
	// English option
	public static By objEnglishOption = By.xpath("//input[@value='en']//parent::*//span");
	public static By objKannadaLanguage = By.xpath("//input[@value='kn']//parent::*//span");

	public static By objchooseAppToOpen = By.xpath("//*[@resource-id='android:id/resolver_page']");
	// *[@text='Chrome']

	public static By objSelectAppToOpen = By.xpath("//*[@text='Chrome']");

	// Cashback for payment by Amazon pay
	public static By objCashbackByAmazonPay = By
			.xpath("//p[contains(text(),'ZEE5 - Cashback for Payment by Amazon Pay upto INR')]");

	// 30 % Cashback on any bank card
	public static By objCashbackByAnyBankCard = By
			.xpath("//p[contains(text(),'ZEE5 - 30% Cashback on any Bank Credit')]");

	// Paytm 50% Cashback
	public static By objCashbackOnPaytm = By.xpath("//p[contains(text(),'ZEE5 Paytm 50% Cashback Offer T & C')]");
	// Offer duration in Terms of Use
	public static By objOfferDUration = By.xpath("//b[contains(text(),'Offer Duration')]");
	// Hyper link in Privacy Policy Screen
	public static By objLinkOnPrivacyPolicy = By.xpath("//a[contains(text(),'www.zee5.com')]");

//		NETWORK
	public static By objPlaybackErrorMessage = By.xpath("//div[@class='networkErrorContainer']");

	public static By objApplyBtn = By.xpath("//div[contains(text(),'APPLY')]");

	// My remainder option
	public static By objMyRemainder = By.xpath("//a[contains(text(),'My Reminders')]");

	public static By objkannadalanguage = By.xpath("//span[@class='commonName'][contains(text(),'Kannada')]");

	public static By objResetSettingsToDefault = By.xpath("//div[@class='noSelect settingleftSettings']");

	public static By objPlans = By.xpath("//*[text()='Plans']");

	public static By objBuySubscription = By.xpath("//*[.='Buy Subscription']");

	public static By objHaveAPrepaidCode = By.xpath("//a[.='Have a prepaid code ?']");

	public static By objMyAccount = By.xpath("//*[text()='My Account'] ");

	public static By objMySubscription = By.xpath("//*[.='My Subscription']");

	public static By objMyTransactions = By.xpath("//*[.='My Transactions']");

	public static By objMySubscriptionPage = By.xpath("//*[.='My Subscription']");

	public static By objNoActiveSubscription = By.xpath("//*[.='No Active Subscription']");

	public static By objMySubscriptionItem = By.xpath("//*[@class='subscriptionItem']");

	public static By objMySubscriptionPackName = By.xpath("//*[@class='packNameWrap']");

	public static By objHamburgerBtn = By.xpath("//*[contains(text(),'Open Menu')]");

	public static By objMyTransactionPackName = By.xpath("//*[@class='packInline']");

	public static By objGetPremiumCTAbelowPlayer = By
			.xpath("//*[@class='subscribe-teaser-button' and //*[.='GET PREMIUM']]");

	public static By objMyTransactionPage = By.xpath("//*[@class='pageTitle' and //*[text()='My Transactions']]");

	public static By objGetPremiumPopup = By
			.xpath("//h2[contains(@class,'popupTitle bigTitle')]//p[text()='Subscribe']");

	public static By objPopupClose = By.xpath("//*[@class='noSelect closePupup iconInitialLoad-ic_close']");

	public static By objSubscribeNowLink = By
			.xpath("(//span[contains(@class,'subscribe-link') and contains(text(),'Subscribe to')])[1]");

	public static By objLanguageBtn = By.xpath("//*[@class='noSelect menuItem  languageMenu ']");

	public static By objContentLanguageBtn = By
			.xpath("//*[@class='noSelect contentlangugaeHeader ' and contains(text(),'Content Language')]");

	public static By objSelectedLanguages = By
			.xpath("//*[@class='checkboxWrap checkedHighlight']//*[@class='innnerContentWrap']");

	public static By objCancelBtnOnLangPp = By.xpath("//*[@class='popupBtn accentBtn noSelect']");

//	public static By objContentLanguage = By.xpath("//div[contains(@class,'contentlangugaeHeader')]");
	// manas
	public static By objContentLanguage = By.xpath("//div[contains(text(),'Content Language')]");
	public static By objAboutUsOption = By.xpath("(//a[.='About Us'])[1]");

	public static By objHelpCenterOption = By
			.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Help Center')]");

	public static By objTermsOfUseOption = By
			.xpath("//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Terms of Use')]");

	public static By objPrivacyPolicy = By.xpath(
			"//*[contains(@class,'menuGroup active')]//*[contains(@class,'')][contains(text(),'Privacy Policy')]");

	public static By objPrivacyPolicyInfo = By.xpath("//*[@class='staticPageContainer']");

	public static By objSecurityInfo = By.xpath("//*[contains(text(),'Security and Compliance with Laws')]");

	public static By objBuildVersion = By.xpath("//*[@class='versionText']");

	public static By objCloseHamburger = By.xpath("//*[text()='Close Menu']");

	public static By objApply = By.xpath("//*[@class='popupBtn noSelect']");

	public static By objApplybutton = By.xpath("//*[@class='filterButtonContainer']");

	public static By objAboutUsTextInPage = By.xpath("//*[contains(text(),'About Us')]");

	public static By objTermsOfUseScreen = By.xpath("//h1[contains(text(),'Terms of Use')]");

	public static By objPrivacyPolicyScreen = By.xpath("//h1[contains(text(),'Privacy Policy')]");

	public static By objLanguage = By.xpath("//*[contains(@class,'languageMenu')]");

	public static By objMoreSettingInHamburger = By.xpath("//*[contains(text(),'More Settings')]");

	public static By objParentalControl = By.xpath("//*[contains(text(),'Parental Control') and @class]");

	public static By objAuthenticationOption = By.xpath("//*[contains(text(),'Authenticate Device')]");

	public static By objAuthenticationButtonHighlighted = By
			.xpath("//button[contains(@class,'noSelect buttonGradient')]");

	public static By objPlanInsideItemsBtn(String itemName) {
		return By.xpath("//*[contains(@class,'menuForPlans') and contains(text(),'" + itemName + "')]");
	}

	public static By objPopUpProceedButton = By
			.xpath("//*[@class='registerLoginContainer']//*[@class='noSelect buttonGradient ']");

	public static By objMoreSettingInKannada = By.xpath("//*[contains(text(),'ಹೆಚ್ಚಿನ ಸೆಟ್ಟಿಂಗ್ಸ್ ') and @class]");

	public static By objUpdatePasswordButton = By.xpath("//*[contains(@class,'noSelect buttonGradient')]");

//	public static By objNotNow =  By.xpath("//*[contains(@class,'addToHomeScreen')]//*[contains(text(),'Not now')]");
	// manas
	public static By objNotNow = By.xpath("//*[@class='btnWrap']//*[contains(text(),'Not now')]");

	public static By objSetParentalLockButton = By.xpath("//button[contains(@class,'noSelect buttonGradient')]");

	public static By objLanguageButtonWeb = By.xpath("//div[@id='languageBtn']");

	public static By objMyAccountOptionsText = By.xpath("//h1[contains(@class,'pageTitle')]");

	public static By objUserNameInMyProfileWeb = By
			.xpath("//div[@class='userDetails']//div[contains(@class,'userName')]");

	public static By objHomeInOpenMenuTab = By.xpath("//a[@class='noSelect menuItem  active']//div[.='Home']");

	public static By objProfileText = By.xpath("//div[@class='noSelect pageLink' and contains(text(),'My Profile')]");

	public static By objSettings = By.xpath("//*[text()='Settings']");
	public static By objLanguagePop = By.xpath("//div[@class='popupContent langFilterPopupWrapper']");
	public static By objInfo = By.xpath("//*[text()='Info']");

	// terms of use in Sign up screen
	public static By objTermsOfServicesInSignupScreen = By.xpath("//span[text()='Terms of Services']");

	// Privacy policy in Sign up screen
	public static By objPrivacyPolicyInSignupScreen = By.xpath("//span[text()='Privacy Policy']");

	public static By objDisplayLanguage = By
			.xpath("//*[contains(@class,'settingleft') and contains(text(),'Display Language')]");

	public static By carouselFirstDot = By.xpath("(//*[contains(@class,'carouselDots')])[1]");

	public static By objArticleTitle(String title) {
		return By.xpath("//*[contains(@text,'" + title + "')]");
	}

	public static By myAccount = By.xpath("//div[contains(@class,'menuTitle')]//*[.='My Account']");

	public static By objContinueButtonInVerifyAccount = By.xpath("//div[@class='noSelect popupBtn' and .='Continue']");

	public static By objTermsInKannada2 = By.xpath("//div[@class='menuGroup active'][2]//a[3]");

	public static By objPrivacyPolicyInKannadA2 = By.xpath("//div[@class='menuGroup active'][2]//a[4]");

	public static By objAboutUsinKannada2 = By.xpath("//div[@class='menuGroup active'][2]//a[1]");

	public static By objMyProfile = By.xpath("//div[contains(@class,'userEmail')]");
}
