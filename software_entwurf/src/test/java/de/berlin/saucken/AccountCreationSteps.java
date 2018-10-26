package de.berlin.saucken;

import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AccountCreationSteps {

    private String nicknameTaken = "already_taken";
    private String emailTaken = "already_taken@gmail.com";
    private String badPassword = "badpassword";

    private boolean buttonNewAccountClicked;
    private boolean buttonSendFormClicked;
    private boolean linkConfirmClicked;

    @When("^I click on the button create new account$")
    public void i_click_on_the_button_create_new_account() throws Exception {
        this.buttonNewAccountClicked = true;

    }

    @When("^I enter new good email as \"([^\"]*)\"$")
    public void i_enter_new_good_email_as(String arg1) throws Exception {
        assertNotEquals(this.emailTaken, arg1);
    }

    @When("^I enter new good nickname as \"([^\"]*)\"$")
    public void i_enter_new_good_nickname_as(String arg1) throws Exception {
        assertNotEquals(this.nicknameTaken, arg1);
    }

    @When("^I enter new bad nickname as \"([^\"]*)\"$")
    public void i_enter_new_bad_nickname_as(String arg1) throws Exception {
        assertEquals(this.nicknameTaken, arg1);
    }

    @When("^I enter new bad email as \"([^\"]*)\"$")
    public void i_enter_new_bad_email_as(String arg1) throws Exception {
        assertEquals(this.emailTaken, arg1);
    }

    @When("^I choose good password as \"([^\"]*)\"$")
    public void i_choose_good_password_as(String arg1) throws Exception {
        assertNotEquals(this.badPassword, arg1);

    }

    @When("^I click on the button send creation account form$")
    public void i_click_on_the_button_send_creation_account_form() throws Exception {
        this.buttonSendFormClicked = true;

    }

    @When("^I click on the link confirm account creation$")
    public void i_click_on_the_link_confirm_account_creation() throws Exception {
        this.linkConfirmClicked = true;

    }

    @When("^I choose bad password as \"([^\"]*)\"$")
    public void i_choose_bad_password_as(String arg1) throws Exception {
        assertEquals(this.badPassword, arg1);

    }


}
