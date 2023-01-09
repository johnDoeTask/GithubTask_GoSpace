
# Github Api and UI Automation Task


This project is for automating the api testing of creating getting and deleting a repository, and UI testing of creating a new repository for a github account.


## API Task

#### Scenario 1 : Create a new repository in a GitHub organization

```
  o Validate the response
```

 
#### Scenario 2 : Get the list of repositories in a GitHub organization

```
  o Validate the response
  o Validate that the created repository in step #1 exist in the list
```



#### Scenario 3 : Delete a repository in a GitHub organization


```
  o Could be the same repository that was created in step #1
  o Validate the response
```


## UI Task

#### Scenario : Create a new repository in a GitHub organization

```
  - GitHub user sign in scenario
  - Getting the list of user’s repositories
  - Creating a new repository and validating the creation by revisiting the list of repositories
after adding a new one into the list.
```
  
## Tools Used in The Project

**Framework:** ________Cucumber BDD

**Design Patterns:** ____Page Object Model, Singleton

**Structures:** _________ConfigurationReader, Environment, Driver utility

## Directory structure:

![Uygulama Ekran Görüntüsü](https://iili.io/H5pXTRS.png')


## Steps to run at your system:


- Clone the repository
- Change the credentials and token with valid datas from qa1.properties file
```
file path : src/test/resources/Environments/qa1.properties
```

- 'Run' button in the 'CukesRunner' class can be used, it's under the 'runner' package.
- or just Run the code below
- ```bash
  mvn test
```

  