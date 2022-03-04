@CreatePool
Feature: As a qa I want to check displayed views for each user role
Scenario: Login on Competency and check displayed views for each user role
  Given navigate to the Doodle web app and start Login
  When user login on Doodle UI with username "doodle.test.2022@gmail.com" and pass "test2022"
  And user create pull with title "Football" on location "ŠTARK ARENA, Belgrade, Serbia" in time "18-19h Sunday" with max "14" participants
  Then validate created invite title "Football" and location "ŠTARK ARENA, Belgrade, Serbia"
  Then delete created group invite
