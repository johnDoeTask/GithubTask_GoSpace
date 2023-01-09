package com.githubTaskProject.step_defs;


import com.githubTaskProject.pages.GithubRepoPage;
import com.githubTaskProject.utils.Driver;
import com.githubTaskProject.utils.Environment;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import io.restassured.RestAssured;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;


public class Hooks {



	@Before(value = "@api")
	public void setupBaseURL() {
		RestAssured.baseURI = Environment.API_BASE_URL;
	}
	@After(value = "@ui")
	public void tearDown(Scenario scenario) {
		// only takes a screenshot if the scenario fails
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}

	}






	@After(value = "@ui")
	public void tearDownWebDriver(){

		Driver.closeDriver();
	}

	
	
	
	
}
