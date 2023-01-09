package com.githubTaskProject.pages;

import com.githubTaskProject.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "login_field")
    public WebElement inputBox_username;

    @FindBy(id = "password")
    public WebElement inputBox_password;


    @FindBy(xpath = "//input[@value='Sign in']")
    public WebElement button_signIn;







}
