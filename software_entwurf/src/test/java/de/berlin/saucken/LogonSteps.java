package de.berlin.saucken;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LogonSteps {

    private boolean connectionOpen;
    private boolean resetButtonClicked;

    private boolean resetLinkClicked;

    private boolean loginButtonClicked;

    private String goodEmail = "test@gmail.com";
    private String goodPassword = "abc123";


    // Methoden die von mehreren Scenarios benutzt werden
    @Given("^a connection to the server is open$")
    public void a_connection_to_the_server_is_open() throws Exception {
        this.connectionOpen = true;
    }

    @When("^I click on the button login$")
    public void i_click_on_the_button_login() throws Exception {
        this.loginButtonClicked = true;
    }

    @Then("^I should see a message saying \"([^\"]*)\"$")
    public void i_should_see_a_message_saying(String arg1) throws Exception {
        System.out.println(arg1);
    }

    // ENTER GOOD/BAD CREDENTIALS

    @When("^I enter good email as \"([^\"]*)\"$")
    public void i_enter_good_email_as(String arg1) throws Exception {
        assertEquals(this.goodEmail, arg1);
    }

    @When("^I enter bad email as \"([^\"]*)\"$")
    public void i_enter_bad_email_as(String arg1) throws Exception {
        assertNotEquals(this.goodEmail, arg1);
    }

    @When("^I enter good password as \"([^\"]*)\"$")
    public void i_enter_good_password_as(String arg1) throws Exception {
        assertEquals(this.goodPassword, arg1);
    }

    @When("^I enter bad password as \"([^\"]*)\"$")
    public void i_enter_bad_password_as(String arg1) throws Exception {
        assertNotEquals(this.goodPassword, arg1);
    }

    // REQUEST NEW PASSWORD

    @When("^I click on the button reset password$")
    public void i_click_on_the_button_reset_password() throws Exception {
        this.resetButtonClicked = true;
    }


    @When("^I click on the link reset password$")
    public void i_click_on_the_link_reset_password() throws Exception {
        this.resetLinkClicked = true;
    }


    @When("^I choose a good password as \"([^\"]*)\"$")
    public void i_choose_a_good_password_as(String arg1) throws Exception {
        assertEquals(this.goodPassword, arg1);
    }

    @When("^I retype good password as \"([^\"]*)\"$")
    public void i_retype_good_password_as(String arg1) throws Exception {
        assertEquals(this.goodPassword, arg1);
    }

    @When("^I choose a bad password as \"([^\"]*)\"$")
    public void i_choose_a_bad_password_as(String arg1) throws Exception {
        assertNotEquals(this.goodPassword, arg1);
    }

    @When("^I retype bad password as \"([^\"]*)\"$")
    public void i_retype_bad_password_as(String arg1) throws Exception {
        assertNotEquals(this.goodPassword, arg1);
    }




}
