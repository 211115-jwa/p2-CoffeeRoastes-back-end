package p2CoffeeRoastesvanquishbackend.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {

	private WebDriver driver;

	@FindBy(id="usernameSignIn")
	private WebElement usernameSignIn;
	@FindBy(id="passwordSignIn")
	private WebElement passwordSignIn;
	@FindBy(id="submit-login")
	private WebElement submitLogin;

	
	@FindBy(id="User")
	WebElement submtBtn;

	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateTo() {
		driver.get("http://localhost:8080");
	}

	public void submitLogin(String username, String password) {
		usernameSignIn.sendKeys(username);
		passwordSignIn.sendKeys(password);
		submitLogin.click();
	}

	
	public String getErrorMessage() {
		WebElement signInAlert = driver.findElement(By.id(""));
		return signInAlert.getText();
	}

	public void SubmitLoginClick() {
		
		submtBtn.click();
	}}
	
