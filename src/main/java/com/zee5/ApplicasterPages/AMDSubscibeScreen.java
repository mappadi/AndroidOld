package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Vinay

public class AMDSubscibeScreen {
	
	//Select pack
	public static By objSubscribeHeader = By.xpath("//*[@text='Subscribe']");
	
	public static By objSubscribePageBackButton = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_back']");
	public static By objSelectPackText  = By.xpath("//*[@text='Select Pack']");
	public static By objAccountInfoText1 = By.xpath("//*[@text='Account Info']");
	public static By objPaymentText = By.xpath("//*[@class='android.widget.TextView' and @text='Payment Options']");
	public static By objAdbanner = By.xpath("//*[@id='packSelectionCrousalImageView']");
	public static By objApplyPromoCodeTextbox = By.xpath("//*[@id='txtET_promocode_input']");
	
	public static By objCancelPromoCode = By.xpath("//*[@resource-id='com.graymatrix.did:id/icon_cancel_promo_code']");
	public static By objApplyPromoCodeappliedText = By.xpath("//*[@resource-id='com.graymatrix.did:id/textinput_helper_text']");
	public static By objPriceAfterPromoCode = By.xpath("//*[@resource-id='com.graymatrix.did:id/old_price_tv']");
	public static By objApplybuttonNotHighlighted = By.xpath("//*[@resource-id='com.graymatrix.did:id/apply_promocode' and @selected='false']");
	public static By objApplyPromoCodeText = By.xpath("//*[@id='txtET_promocode_input'] and @text='Have a Promo Code?");
	public static By objApply = By.xpath("//*[@resource-id='com.graymatrix.did:id/apply_promocode']");
	public static By objDescriptionText = By.xpath("//*[@resource-id='com.graymatrix.did:id/deviceslayout']");
	public static By objRSVODPremiumPack = By.xpath("(//*[@class='android.support.v7.app.ActionBar$b'])[2]");
	public static By objInvalidPromoCodeText = By.xpath("//*[@resource-id='com.graymatrix.did:id/textinput_error']");
	public static By objSelectYourPreminumPackText = By.xpath("//*[@id='packsViewTextView']");
	public static By objAllAccessTab = By.xpath("//*[@contentDescription='All Access']");
	public static By objKannadaTab = By.xpath("//*[@contentDescription='Kannada']");
	public static By obj30daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 99 for 30 days']]]");
	public static By objRadioBtn = By.xpath("//*[@id='selectionImageSelector']");
	public static By obj180daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 599 for 180 days']]]");
	public static By obj365daysPack = By.xpath("//*[@id='holder_price' and ./*[./*[@text='INR 999 for 365 days']]]");
	public static By objContinueBtn = By.xpath("//*[@id='btnContinue_PackSelection']");
	public static By objRSVODPack1 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/decription_tv'])[1]");
	public static By objRSVODPack2 = By.xpath("(//*[@resource-id='com.graymatrix.did:id/decription_tv'])[2]");
	
	//Account info
	public static By objSelectPack = By.xpath("//*[@id='pack_selection_title']");
	public static By objAllAccessPack = By.xpath("//*[@id='tv_selected_pack']");
	public static By objTvPackYear = By.xpath("//*[@id='tv_pack_year']");
	public static By objSelectedPackDesc = By.xpath("//*[@id='tv_selected_pack_description']");
	public static By objProceedButtonNothighlighted = By.xpath("//*[@resource-id='com.graymatrix.did:id/btnContinue_useraccountdetails' and @clickable='false']");
	public static By objORSeperator = By.xpath("//*[@class='android.widget.TextView' and @text='OR']");
	public static By objAccountInfoHeader = By.xpath("//*[@text='Account Info' and ./parent::*[@id='user_inputs_details_layout']]");
	public static By objEmailID = By.xpath("//*[@id='edit_email']");
	public static By objProceedBtn = By.xpath("//*[@id='btnContinue_useraccountdetails']");
	public static By objContinueWithText = By.xpath("//*[@text='Continue with:']");
	public static By objGoogleIcon = By.xpath("//*[@id='google_login']");
	public static By objFacebookIcon = By.xpath("//*[@id='fb_login']");
	public static By objTwitterIcon = By.xpath("//*[@id='twitter_login']");
	
	//Password screen 
	public static By objDrabBtn = By.xpath("//*[@id='dialog_divider']");
	public static By objEnterPassword = By.xpath("//*[@id='header_verify_pwd']");
	public static By objPasswordTextField  = By.xpath("//*[@id='input_password']");
	public static By objPasswordErrorMessage = By.xpath("//*[@resource-id='com.graymatrix.did:id/textinput_error']");
	public static By objShowIcon = By.xpath("//*[@id='text_input_password_toggle']");
	public static By objProceedPWDScreen = By.xpath("//*[@id='subscription_plan_validate_password_button']");
	public static By objVerifyOTPScreenProceed = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_verify_proceed']");
	public static By objForgotPassword = By.xpath("//*[@id='option_forgot_password']");
	
	public static By objVerifyOTPScreen = By.xpath("//*[@resource-id='com.graymatrix.did:id/txt_verify_mobile_otp_account_header']");
	public static By objGetOTP = By.xpath("//*[@resource-id='com.graymatrix.did:id/btn_get_otp']");
	public static By objTermsandPrivacyLink = By.xpath("//*[@text='By creating this account you agree to our Terms of Use and Privacy Policy.']");
	public static By objForgotPasswordPageHeader = By.xpath("//*[@resource-id='com.graymatrix.did:id/screen_title' and @text='Forgot Password']");
	
	public static By objPasswordHidden = By.xpath("//*[@resource-id='com.graymatrix.did:id/input_password' and @password='true']");
	public static By objPasswordDisplayed = By.xpath("//*[@resource-id='com.graymatrix.did:id/input_password' and @password='false']");
	//Payment Screen
	public static By objAccountInfoText = By.xpath("//*[@id='account_info_title']");
	public static By objUserEmailID = By.xpath("//*[@id='selectedUserDetailsName']");
	public static By objProfileIcon = By.xpath("//*[@id='iconSmile']");
	public static By objPaymentOption = By.xpath("//*[@text='Payment Options']");
	public static By PaymentOptionsRadioBtn = By.xpath("//*[@class='android.widget.RadioButton']");
	public static By objPaymentReccuringMsg = By.xpath("//*[@id='payment_recurring_msg']");
	public static By objContinueBtnPaymentScreen = By.xpath("//*[@id='btnContinue_paymentdetails']");
	
	
	

	
}
