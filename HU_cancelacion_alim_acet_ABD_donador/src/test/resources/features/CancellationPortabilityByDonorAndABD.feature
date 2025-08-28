Feature: Cancellation of portability when it was accepted by the donor and the ABD

  @cancellationFlow
  Scenario Outline: Create portability, accept it, cancel it, and verify state in SPM and database
    When I send a portability request using the service "<spn_alim_requestNip>" with the cellphone "<cellphone>"
    Then I should receive the response code for Nip "<errorCode>"

    Given I am on the Numlex login page test credentials
    When the user enters valid credentials for test user
      | username | password   |
      | <test>   | <password> |
    Then I should see the NIP "<NIP>" and the cellphone "<cellphone>" displayed
    And I log out for test user

    When I send a portability request using the service "<spn_alim_requestPortability>" with cellphone "<cellphone>", NIP "<NIP>", and date "<date>"
    Then I should receive the response code "<errorCode>"

    Given I am on the Numlex login page fake credentials
    When the user enters valid credentials for fake user
      | username | password   |
      | <fake>   | <password> |
    Then I accept the portability request
    And I log out for fake user

    Given I am on the SPM login page
    When I log in to see aceptation status
      | username  | password      |
      | <spmUser> | <spmPassword> |
    And I search for the cellphone "<cellphone>"
    Then I should see the request state as acepted "<stateRequest>"

    Given I am connected to the SPM database
    When I search for the request using the query "<query>"
    Then I should see the requestId stored in the database

    Given I am on the Numlex login page
    When I log in with username fake to cancel the request
      | username | password   |
      | <fake>   | <password> |
    And I click the cancel button
    And I complete the cancellation form
    And I click the submit button
    Then I should see the cellphone "<cellphone>" with status "<StatusRequest>"
    And I log out

    Given I am on the SPM login page
    When I log in SPN to see the status
      | username | password   |
      | <spmUser>   | <spmPassword> |
    And I search for the cellphone to see the status canceled "<cellphone>"
    Then I should see the request state as canceled"<stateRequestCanceled>"

    Given I am connected to the SPM database
    When I search for the request using the query "<query>"
    Then the result should be empty

    Examples:
      | spn_alim_requestNip | cellphone | errorCode | test      | password     | NIP    | date                | spn_alim_requestPortability | fake     | spmUser | spmPassword | stateRequest              | query             | StatusRequest | stateRequestCanceled |
      | requestNipService   |           | success   | test00004 | TD3vD$9*dYN* | 998877 | 2025-07-16T00:07:00 | spn_alim_requestPortability | fakeUser | spm     | 12345       | accepted by ABD and Donor | SELECT * FROM spm | canceled      | canceled by donor    |
