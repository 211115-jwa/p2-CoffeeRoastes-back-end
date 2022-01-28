package p2CoffeeRoastesvanquishbackend.Step;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepImpl {
	
	private static Page page;
	private static WebDriver driver;

	@BeforeAll
	public static void setupDriver() {
		File file =new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
		page= new Page();
	}
	
	/*
	 * @When("the user enters the username and password") public void
	 * the_user_enters_the_username_and_password() {
	 * page.submitLogin("standard_user_id", "secret_passwd"); }
	 */
	@Then("the user should be taken to a different page")
	public void the_user_should_be_taken_to_a_different_page() {
		assertNotEquals("http://localhost:8080",driver.getCurrentUrl());
	}

	/*
	 * @When("the user enters an incorrect username and password") public void
	 * the_user_enters_an_incorrect_username_and_password() {
	 * page.submitLogin("incorrect", "incorrect"); }
	 * 
	 * @Then("the appropriate error message should appear") public void
	 * the_appropriate_error_message_should_appear() { String errorMsg =
	 * page.getErrorMessage();
	 * 
	 * assertTrue(errorMsg.contains("Username and password")); }
	 */
	
	/*
	 * @Given("the user is on the CofferoastersApp home page") public void
	 * the_user_is_on_the_cofferoasters_app_home_page() { // Write code here that
	 * turns the phrase above into concrete actions page;
	 * 
	 * throw new io.cucumber.java.PendingException(); }
	 */
	/*
	 * @Given("the user clicks the Log In link") public void
	 * the_user_clicks_the_log_in_link() { // Write code here that turns the phrase
	 * above into concrete actions
	 * 
	 * submitLogin.click(); throw new io.cucumber.java.PendingException(); }
	 * 
	 * @When("the user enters {string} and {string} to sign in") public void
	 * the_user_enters_and_to_sign_in(String string, String string2) { // Write code
	 * here that turns the phrase above into concrete actions
	 * 
	 * 
	 * throw new io.cucumber.java.PendingException(); }
	 */

	@When("some other action")
	public void some_other_action() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@Then("the navbar says {string}")
	public void the_navbar_says(String username) {
	    // Write code here that turns the phrase above into concrete actions
		
		
		
	    throw new io.cucumber.java.PendingException();
	}
	@Then("the page says Incorrect Credentials")
	public void the_page_says_incorrect_credentials() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("the user clicks the Sign In link")
	public void the_user_clicks_the_sign_in_link() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement loginBtn = driver.findElement(By.id("loginBtn"));
		loginBtn.click();
		
	    throw new io.cucumber.java.PendingException();
	}
	
	

}
