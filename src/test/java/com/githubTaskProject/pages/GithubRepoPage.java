package com.githubTaskProject.pages;

import com.githubTaskProject.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GithubRepoPage {

    public GithubRepoPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//ul[@data-filterable-for='your-repos-filter']//a")
    public List<WebElement> listOfRepositories;

    @FindBy(xpath = "//a[@class='text-center btn btn-primary ml-3']")
    public WebElement newRepoButton;

    @FindBy(xpath = "//summary[@id='repository-owner']")
    public WebElement selectOwnerDrop;




    public WebElement getOwnerForSelection(String ownerUserName){

        String locator = "//div[text()[contains(.,'"+ownerUserName+"')]]";

        return Driver.getDriver().findElement(By.xpath(locator));

    }


    @FindBy(xpath = "//input[@id='repository_name']")
    public WebElement inputBox_newRepoName;

    @FindBy(xpath = "//button[@data-disable-with='Creating repository&hellip;']")
    public WebElement button_createRepo;


    @FindBy(id = "settings-tab")
    public WebElement settingsButton;


    @FindBy(xpath = "//details[@class='details-reset details-overlay details-overlay-dark flex-md-order-1 flex-order-2']/summary")
    public WebElement deleteRepositoryButton;


    @FindBy(xpath = "//form[@action='/johnDoeTask/NewRepoTask/settings/delete']/p/input")
    public WebElement checkBoxForDeletion;




}