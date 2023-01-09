package com.githubTaskProject.step_defs;

import com.githubTaskProject.pages.GitHubHomePage;
import com.githubTaskProject.pages.GithubRepoPage;
import com.githubTaskProject.pages.LoginPage;
import com.githubTaskProject.utils.BrowserUtilities;
import com.githubTaskProject.utils.Driver;
import com.githubTaskProject.utils.Environment;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static io.restassured.RestAssured.given;

public class UI_Test_Stef_Definitions {
    LoginPage loginPage = new LoginPage();
    GitHubHomePage gitHubHomePage = new GitHubHomePage();
    GithubRepoPage githubRepoPage = new GithubRepoPage();
    Actions actions = new Actions(Driver.getDriver());
    String newRepo = "NewRepoTask";

    int numberOfReposBeforeCreate;

    @Given("User navigates to login page of the GitHub application")
    public void user_navigates_to_login_page_of_the_git_hub_application() {

        //User opens the login page of the github app, used Dirver class with singleton desing pattern,
        //and used Environment Structure with Configuration Reader utility. --> it's for utilizing the environment
        Driver.getDriver().get(Environment.GITHUB_LOGIN_URL);
    }





    @Given("User enters the credentials and clicks on SignIn Button")
    public void user_enters_the_credentials_and_clicks_sign_in_button() {

        //Web elements are located with using page object model design pattern and credentials stored and used from environment structure
        loginPage.inputBox_username.sendKeys(Environment.USERNAME);
        loginPage.inputBox_password.sendKeys(Environment.PASSWORD);

        //user click on the sign-in button
        loginPage.button_signIn.click();


    }

    @And("User opens the repository page with using repository link under profile avatar")
    public void user_opens_the_repository_page_with_using_repository_link_under_profile_avatar() {

        //I used a utilized explicit wait written using js code for sychronization
        BrowserUtilities.waitForPageToLoad(5);
        //int he html, webelement of the profile avatar's tag is not a interactable element so i used the Action class for using advanced mouse movement.
        actions.moveToElement(gitHubHomePage.smallAvatar).click().perform();
        //user clicks the repo link
        gitHubHomePage.yourRepositoryLink.click();


    }

    @And("User gets the repository list before create a new one")
    public void user_gets_the_repository_list_before_create_a_new_one() {

        System.out.println("Repositories Before Create : ");
        //for screen the size of the list of repo before created a new repository
        System.out.println("How Many : " + githubRepoPage.listOfRepositories.size());

        //for screen list of repo before crete a new repository
        for (WebElement eachRepository : githubRepoPage.listOfRepositories) {
            System.out.println(eachRepository.getText());
        }

        //we stored the size of the old list of repository for compare number of new and old list of repo after created a new repository
         numberOfReposBeforeCreate = githubRepoPage.listOfRepositories.size();


    }

    @When("User clicks on the new button for creating a new repository")
    public void user_clicks_on_the_new_button_for_creating_a_new_repository() {
        //user clicks the new button to start the process of creating a new repository
        githubRepoPage.newRepoButton.click();
    }

    @And("User chooses the owner and enters a name for new repository and click on create button")
    public void user_chooses_the_owner_and_enters_a_name_for_new_repository_and_click_on_create_button() {
        //user select the owner's name with the username
        githubRepoPage.selectOwnerDrop.click();
        githubRepoPage.getOwnerForSelection(Environment.USERNAME).click();

        //user enters a name for the new repository
        githubRepoPage.inputBox_newRepoName.sendKeys(newRepo);

        //user waits for be able to click the create button, used utilizied Explicit wait
        BrowserUtilities.waitForClickablility(githubRepoPage.button_createRepo, 5).click();
        System.out.println("New Repo is created");

    }

    @And("User opens the repository page again and gets the list of repository again")
    public void user_opens_the_repository_page_again_and_gets_the_list_of_repository_again() {

        //user goes to the rpository page again
        actions.moveToElement(gitHubHomePage.smallAvatar).click().perform();
        gitHubHomePage.yourRepositoryLink.click();

    }

    @Then("Verify that the size of the repository list has been increased by 1")
    public void verify_that_the_size_of_the_repository_list_has_been_increased_by() {

        //check if the size of new repo list is 1 more than the old repo list
        int numberOfReposAfterCreate = githubRepoPage.listOfRepositories.size();
        Assert.assertEquals(numberOfReposAfterCreate, (numberOfReposBeforeCreate + 1));
    }

    @Then("Verify that the name of the newly created repository is in the repo list")
    public void verify_that_the_name_of_the_newly_created_repository_is_in_the_repo_list() {


        boolean isCreated = false;
        System.out.println("\n\nRepositories After Create : ");
        //screen size of the new repo list
        System.out.println("How Many : " + githubRepoPage.listOfRepositories.size());

        for (WebElement eachRepository : githubRepoPage.listOfRepositories) {
            //screen the repo list after created a new repository
            System.out.println(eachRepository.getText());

            //if the loop converted 'isCreated variable' to true in the previous steps, we say to loop 'don't execute rest of the statement.'
            if(isCreated){
                continue;
            }

            //checking if new repo is in the list of repository after created it
            if(eachRepository.getText().equals(newRepo)){
                isCreated = true;
            }

        }


        //verifying isCreated true, i mean it's checking after whole these step could we create a new repository and is it in the list of the repository.
        Assert.assertTrue(isCreated);
        System.out.println("Validated that New repo is created");

    }


    //When the test is executed once, repo is created, for re-execution newly created repository should be deleted
    @After(value = "@ui")
    public void deleteNewlyCreatedRepo(){


        //click on the repoName
        githubRepoPage.listOfRepositories.get(0).click();

        //click on the settings
        githubRepoPage.settingsButton.click();

        //click on delete repository
        actions.moveToElement(githubRepoPage.deleteRepositoryButton).click().perform();

        //enter the needed confirmation text and press enter
        githubRepoPage.checkBoxForDeletion.sendKeys(Environment.USERNAME+"/NewRepoTask"+ Keys.ENTER);

        //newly created repo is deleted and account and repositories is ready same test.
        System.out.println("Deleted Newly created Repo");

    }
}


