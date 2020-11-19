package com.zee5.ApplicasterPages;

import org.openqa.selenium.By;

//-- Dev by Kushal

public class AMDProfileScreen {
	
	//Back Icon
	public static By objBackBtn = By.xpath("//*[@id='icon_back']");
		
	public static By objProfileImg= By.xpath("//*[@id='icon_user_image']");
		
	//Get Profile Name
	public static By objUserNameTxt = By.xpath("//*[@id='txt_profile_user_name']");

	//Get Profile Email-ID 
	public static By objProfileEmailId = By.xpath("//*[@id='txt_profile_user_email_id']");
	
	//Edit Button
	public static By objEditBtn = By.xpath("//*[@id='txt_edit_profile']");
	
	//Change Password Button
	public static By objChangePwdBtn = By.xpath("//*[@id='txt_change_password']");
	
	//Details Button
	public static By objDetailsBtn = By.xpath("//*[@id='txt_pack_details']");
		
	//Renew Now Button
	public static By objRenewNowBtn = By.xpath("//*[@id='txt_pack_renew']");
		
	//Get Subscription Plan Name
	public static By objSubsPlanNameTxt = By.xpath("//*[@id='txt_subscription_plan_name']");
	
	//Get Subscription Plan Price
	public static By objSubsPlanPriceTxt = By.xpath("//*[@id='txt_subscription_plan_price']");
	
	//Pack Status Label
	public static By objPackStatusLabel = By.xpath("//*[@id='txt_pack_status_label']");
	
	//Get Pack Status
	public static By objPackStatusTxt = By.xpath("//*[@id='txt_pack_status']");
	
}
