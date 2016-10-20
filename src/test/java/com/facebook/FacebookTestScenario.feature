Feature: Verify basic functionality of facebook 

#Background: 
#	Given: user opens web application 

Scenario: To verify login and logout functionality
	Given user opens web application
  Then the title of web page is "Facebook - Log In or Sign Up"
  When user logs in
  Then the title of web page is "Facebook"
  When user logs out
  Then the title of web page is "Facebook - Log In or Sign Up"

Scenario Outline: To verify status update functionality
  Given user logs in
  When user updates status with text "<status text>"
  And user tags his friend "<friend to tag>"
  And user adds a feeling "<feeling>" "<emotion>"
  And user checks in a location "<checkin location>"
  And user posts the status
  Then expected status should be displayed on wall
 
  Examples:
  |status text|friend to tag|feeling|emotion|checkin location|
  |Enjoying para glide|Sumit|Feeling|happy|Goa, India|
	 
	