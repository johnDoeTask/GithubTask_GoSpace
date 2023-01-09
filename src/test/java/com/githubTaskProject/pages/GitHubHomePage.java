package com.githubTaskProject.pages;

import com.githubTaskProject.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GitHubHomePage {
    public GitHubHomePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//div[@class='Header-item position-relative mr-0 d-none d-md-flex']//img")
    public WebElement smallAvatar;

    @FindBy(xpath = "//a[.='Your repositories']")
    public WebElement yourRepositoryLink;
}
