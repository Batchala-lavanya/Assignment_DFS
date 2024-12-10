Feature: User CucumberTest

  Scenario: Successfully creating a new user
    Given I have the following user details:
      | userId | name      | email                 | password  |
      | 1      | lavanya13 | lavanya13@gmail.com    | lavanya13 |
      | 2      | sravani21 | sravani@gmail.com      | sravani21 |
      | 3      | hymavathi | hyma@gmail.com         | hyma1234  |

    When I call the createUser method
    Then I should create a user with details {int} {string} {string} {string}

  Examples:
    | userId | name      | email                 | password  |
    | 1      | lavanya13 | lavanya13@gmail.com    | lavanya13 |
    | 2      | sravani21 | sravani@gmail.com      | sravani21 |
    | 3      | hymavathi | hyma@gmail.com         | hyma1234  |
	
	

  Scenario: Getting User By Id
    Given I have UserId {int}
    When I call getByUserId method
    Then I should retrieve the user with Id {int}

	  
	  
	
	  
	 
	  
	  
	  
	  