package spn.services.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import spn.services.task.numlex.LoginFakeUserTask;
import spn.services.task.numlex.LoginTask;
import spn.services.task.numlex.LogoutTask;
import spn.services.task.numlex.SolicitudesTask;
import spn.services.task.*;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import net.serenitybdd.screenplay.questions.Text;

import static org.hamcrest.Matchers.equalTo;
import static spn.services.ui.numlex.NipUi.TXT_CELLPHONE;
import static spn.services.ui.numlex.NipUi.TXT_NIP;
import static org.hamcrest.Matchers.containsString;

public class CancellationAlimAceptDonorAbdStepDefinition {

    @Before
    public void setStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("SoapUser")
                .whoCan(CallAnApi.at("http://10.80.54.49:3980"));
    }

    @When("I send a portability request using the service {string} with the cellphone {string}")
    public void i_send_a_portability_request_using_the_service_with_the_cellphone(String spn_alim_requestNip, String msisdn) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                spnAlimRequestNipTask.whithcellphone(msisdn)
        );
    }

    @Then("I should receive the response code for Nip {string}")
    public void i_should_receive_the_response_code_for_nip(String errorCode) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("The SOAP error code", SuccessfulResponse.value(), equalTo(errorCode))
        );
    }

    @Given("I am on the Numlex login page test credentials")
    public void i_am_on_the_numlex_login_page_test_credentials() {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                Open.url("https://10.187.66.114/login"),
                NoPrivatePageMessageTask.on()
        );
    }

    @When("the user enters valid credentials for test user")
    public void the_user_enters_valid_credentials_for_test_user(io.cucumber.datatable.DataTable dataTable) {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                LoginTask.on()
        );
    }

    @Then("I should see the NIP {string} and the cellphone {string} displayed")
    public void i_should_see_the_nip_and_the_cellphone_displayed(String expectedNip, String expectedPhone) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.wasAbleTo(
                SolicitudesTask.on()  // Primero navega o hace clic en la sección donde aparecen los datos
        );

        String nip = Text.of(TXT_NIP).answeredBy(actor);
        String phone = Text.of(TXT_CELLPHONE).answeredBy(actor);
        System.out.println("NIP capturado: " + nip);
        System.out.println("Celular capturado: " + phone);
        actor.remember("nip", nip);
        actor.remember("phone", phone);

    }

    @Then("I log out for test user")
    public void i_log_out_for_test_user() {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                LogoutTask.on()
        );
    }

    @When("I send a portability request using the service {string} with cellphone {string}, NIP {string}, and date {string}")
    public void i_send_a_portability_request_using_the_service_with_cellphone_nip_and_date(String service, String cellphone, String nip, String date) {
        Actor actor = OnStage.theActorInTheSpotlight();
        String nipB = actor.recall("nip");
        String phone = actor.recall("phone");
        OnStage.theActorInTheSpotlight().attemptsTo(
                spn_alim_requestPortabilityTask.withUserData(phone, nipB, date)
        );
    }

    @Then("I should receive the response code {string}")
    public void iShouldReceiveTheResponseCode(String errorCode) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("The SOAP error code", SuccessfulResponse.value(), equalTo(errorCode))
        );
    }
    @Given("I am on the Numlex login page fake credentials")
    public void i_am_on_the_numlex_login_page_fake_credentials() {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                Open.url("https://10.187.66.114/login")

        );
    }
    @When("the user enters valid credentials for fake user")
    public void theUserEntersValidCredentialsForFakeUser(io.cucumber.datatable.DataTable dataTable) {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                LoginFakeUserTask.on()
        );
    }
    @Then("I accept the portability request")
    public void iAcceptThePortabilityRequest() {
    }

    @Then("I log out for fake user")
    public void i_log_out_for_fake_user() {
    }

    @Given("I am on the SPM login page")
    public void i_am_on_the_spm_login_page() {
    }

    @When("I log in to see aceptation status")
    public void i_log_in_to_see_aceptation_status(io.cucumber.datatable.DataTable dataTable) {
    }

    @When("I search for the cellphone {string}")
    public void i_search_for_the_cellphone(String string) {
    }

    @Then("I should see the request state as acepted {string}")
    public void i_should_see_the_request_state_as_acepted(String string) {
    }

    @Given("I am connected to the SPM database")
    public void i_am_connected_to_the_spm_database() {
    }

    @When("I search for the request using the query {string}")
    public void i_search_for_the_request_using_the_query(String string) {
    }

    @Then("I should see the requestId stored in the database")
    public void i_should_see_the_request_id_stored_in_the_database() {
    }

    @Given("I am on the Numlex login page")
    public void i_am_on_the_numlex_login_page() {
    }

    @When("I log in with username fake to cancel the request")
    public void i_log_in_with_username_fake_to_cancel_the_request(io.cucumber.datatable.DataTable dataTable) {
    }


    @When("I click the cancel button")
    public void i_click_the_cancel_button() {
    }

    @When("I complete the cancellation form")
    public void i_complete_the_cancellation_form() {
    }

    @When("I click the submit button")
    public void i_click_the_submit_button() {
    }

    @Then("I should see the cellphone {string} with status {string}")
    public void i_should_see_the_cellphone_with_status(String string, String string2) {
    }

    @Then("I log out")
    public void i_log_out() {
    }

    @When("I log in SPN to see the status")
    public void i_log_in_spn_to_see_the_status(io.cucumber.datatable.DataTable dataTable) {
    }
    @When("I search for the cellphone to see the status canceled {string}")
    public void i_search_for_the_cellphone_to_see_the_status_canceled(String string) {
    }
    @Then("I should see the request state as canceled\"canceled by donor\"")
    public void i_should_see_the_request_state_as_canceled_canceled_by_donor() {
    }

    @Then("the result should be empty")
    public void the_result_should_be_empty() {
    }
}
