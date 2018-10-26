package de.berlin.saucken;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class LogoutSteps {

    private boolean isLogged = true;
    private boolean buttonLogoutClicked = true;
    private boolean buttonConfirm = true;

    @Given("^I am currently logged in with my credentials$")
    public void i_am_currently_logged_in_with_my_credentials() throws Exception {
        assertTrue(isLogged);
    }

    @When("^I click on the button logout$")
    public void i_click_on_the_button_logout() throws Exception {
            this.buttonLogoutClicked = true;
    }

    @When("^I confirm that I want to logout$")
    public void i_confirm_that_I_want_to_logout() throws Exception {
        this.buttonConfirm = true;
    }
}
