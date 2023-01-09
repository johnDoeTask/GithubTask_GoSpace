Feature: UI testing on gitHub
  @wip  @ui
  Scenario: Create a new repository and validate if it's created
    Given User navigates to login page of the GitHub application
    And User enters the credentials and clicks on SignIn Button
    When User opens the repository page with using repository link under profile avatar
    And User gets the repository list before create a new one
    When User clicks on the new button for creating a new repository
    And User chooses the owner and enters a name for new repository and click on create button
    And User opens the repository page again and gets the list of repository again
    Then Verify that the size of the repository list has been increased by 1
    Then Verify that the name of the newly created repository is in the repo list
