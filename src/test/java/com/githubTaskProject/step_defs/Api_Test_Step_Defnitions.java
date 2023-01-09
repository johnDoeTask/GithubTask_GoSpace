package com.githubTaskProject.step_defs;

import com.githubTaskProject.utils.Environment;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Api_Test_Step_Defnitions {

    Response createResponse;

    Response getResponse;

    Response deleteResponse;
    String organization = Environment.ORG_NAME;

    String owner = Environment.ORG_NAME;

    String token = Environment.TOKEN;

    String nameOfNewRepo = "NewRepo";

    String description = "A New Repo Created With API Post Request";




    //api base url is for every snippet in this class



    //Scenario 1 : Create
    @When("Send a Post HTTP request for creating a new repository in a GitHub Organization")
    public void send_a_post_http_request_for_creating_a_new_repository_in_a_git_hub_organization() {

        //For be able to give a name and description to the created Repository,
        //We are adding them as a request body in Json format
        String body = "{\n" +
                "  \"name\": \"" + nameOfNewRepo + "\",\n" +
                "  \"description\": \"" + description + "\",\n" +
                "  \"homepage\": \"https://github.com\",\n" +
                "  \"private\": false,\n" +
                "  \"has_issues\": false,\n" +
                "  \"has_projects\": false,\n" +
                "  \"has_wiki\": false\n" +
                "}";


        //Request of the post method, we store that response in a response class object.
        createResponse =
                given().accept("application/json")
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)             //Authorization
                        .pathParam("ORG", organization)             //we pass the organization name as a path parameter
                        .body(body)                                    //we added the body
                        .log().all()                                   //For screening whole request
                        .when()                                        //syntactic sugar
                        .post("orgs/{ORG}/repos")           //https request and endPoint
                        .then()
//                      .statusCode(200)
                        .log().all()                            //For screening whole response
                        .extract().response();                  //we use the extract method for be able to store the response in a response object after use 'then part'


    }

    @Then("Verify the status code is {int} for creating a repo")
    public void verify_the_status_code_is_for_creating_a_repo(int expectedStatusCode) {
        int actualStatusCode = createResponse.statusCode();

        //Asserted the status code if it is 201
        assertThat(actualStatusCode, is(expectedStatusCode));

    }

    @Then("Verify the response body for creating a repo")
    public void verify_the_response_body_for_creating_a_repo() {
        JsonPath jsonPath = createResponse.jsonPath();

        //Check if the new repo's name is correct
        assertThat(jsonPath.getString("name"), is(nameOfNewRepo));

        //check if the organization's name is correct
        assertThat(jsonPath.getString("organization.login"), is(organization));

        //check if the description is correct
        assertThat(jsonPath.getString("description"), is(description));


    }

    @Then("verify the headers for creating a repo")
    public void verify_the_headers_for_creating_a_repo() {

        //check if the content-Type is json
        assertThat(createResponse.getHeader("Content-Type"), is("application/json; charset=utf-8"));

        //Validating a random header
        assertThat(createResponse.getHeader("Server"), is("GitHub.com"));


    }





//Scenario 2 : Get

    @When("Send a Get HTTP request for getting the list of the repositories in the given GitHub Organization")
    public void send_a_get_http_request_for_getting_the_list_of_the_repositories_in_the_given_git_hub_organization() {
        getResponse =
                given().accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
//                .baseUri("https://api.github.com/")
                        .header("Authorization", token)
                        .pathParam("org", organization)
                        .log().all()
                        .when()
                        .get("orgs/{org}/repos")       //end point and get method
                        .then()
//                        .statusCode(200)
                        .log().all()
                        .extract().response();

    }

    @Then("Verify the status code is {int}")
    public void verify_the_status_code_is(Integer expectedStatusCode) {
        int actualStatucCode = getResponse.getStatusCode();

        //check if the status code is 200
        assertThat(actualStatucCode, equalTo(expectedStatusCode));

    }

    @Then("Verify the response body")
    public void verify_the_response_body() {

        //check is the new repo in the list of repository
        assertThat(getResponse.path("name"), hasItem(nameOfNewRepo));



    }

    @Then("Verifty the headers")
    public void verifty_the_headers() {

        //verify if the code is in the json format
        assertThat(getResponse.getHeader("Content-Type"), is("application/json; charset=utf-8"));

        //verify any random header from response
        assertThat(getResponse.getHeader("Server"), is("GitHub.com"));


    }





    //Scenario 3 : Delete
    @Given("Send a Delete HTTP request for deleting a repository in the given GitHub Organization")
    public void send_a_delete_http_request_for_deleting_a_repository_in_the_given_git_hub_organization() {
        deleteResponse =
                given().accept(ContentType.JSON)
                        .header("Authorization", token)
                        .pathParam("OWNER",owner)           //owner as path parameter
                        .pathParam("REPO",nameOfNewRepo)       //new repo name created previously as path parameter
                        .log().all()
                        .when()
                        .delete("repos/{OWNER}/{REPO}") //end point with the delete http request method
                        .then()
                        .log().all()
                        .extract().response();


    }
    @Then("Verify the status code {int}")
    public void verify_the_status_code(Integer expectedStatusCode) {
        int actualStatusCode = deleteResponse.statusCode();

        //validating the status code is 204
        assertThat(actualStatusCode,is(expectedStatusCode));

    }

    @Then("Verify the headers for deleting")
    public void verify_the_headers_for_deleting() {

        //delete method has not a response body, so theere is no a content type header, this code fragment is for verifying this
        Assert.assertFalse(deleteResponse.headers().hasHeaderWithName("Content-Type"));

        //Verify a random header
        assertThat(deleteResponse.getHeader("Server"),is("GitHub.com"));

    }




}
