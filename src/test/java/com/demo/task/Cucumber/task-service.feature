Feature: User Creation

  # This scenario tests the successful creation of new users with specific details.
  Scenario Outline: Successfully creating a new user
    Given I have the following user details:
      | name      | email                  | password  |
      | <name>    | <email>                | <password> |

    When I call the createUser method
    Then I should create a user with details "<name>" "<email>" "<password>"

  # The Examples section is where we pass the actual test data
  Examples:
    | name       | email                   | password   |
    | lavanya13  | lavanya13@gmail.com      | lavanya13  |
    | sravani21  | sravani@gmail.com        | sravani21  |
    | hymavathi  | hyma@gmail.com           | hyma1234   |

  Scenario Outline: Successfully getting User By Id
    Given I have user details:
      | name      | email                  | password  |
      | <name>    | <email>                | <password> |
    When I call getUserById method <userId>
    Then I should get user details "<name>" "<email>" "<password>"
    
  Examples:
  |userId  	| name       | email                   | password   |
  |1  		| lavanya13  | lavanya13@gmail.com      | lavanya13  |
  |2  		| sravani21  | sravani@gmail.com        | sravani21  |
  |3 		| hymavathi  | hyma@gmail.com           | hyma1234   |
    
   
   Scenario Outline: Successfully getting All Users
    Given user details:
      | name      | email                  | password  |
      | <name>    | <email>                | <password> |
    When I call getAllUsers method
    Then I should get All users details "<name>" "<email>" "<password>"
    
  Examples:
   |userId	| name       | email                   | password   |
   |1 		| lavanya13  | lavanya13@gmail.com      | lavanya13  |
   |2 		| sravani21  | sravani@gmail.com        | sravani21  |
   |3 		| hymavathi  | hyma@gmail.com           | hyma1234   |
    