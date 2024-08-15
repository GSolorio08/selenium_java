package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import config.constants;
import config.page_elements;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import helper_functions.Helper;


public class page_methods {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginBtn;
	private By sortingDropdownList;
	private By fields;
	private By spans;
	private By title;
	
	Wait<WebDriver> wait;
	WebElement log_btn;
	WebElement DropdownList;
	
	Helper helper_functions = new Helper();
	
	public page_methods(WebDriver driver, 
					 By userField, 
					 By passwordField, 
					 By loginBtn,
					 By sortingDropdownList) { //constructor del metodo, va a recibir
		this.driver = driver;
		this.userField = userField;
		this.passwordField = passwordField;
		this.sortingDropdownList = sortingDropdownList;
	    this.loginBtn = loginBtn;
	    this.sortingDropdownList = sortingDropdownList;
		this.wait = new WebDriverWait(driver, Duration.ofMillis(constants.TIMEOUT_ms)); //Declara y construye un wait ligado a un elemento en la pagina
		fields = By.tagName("input");
		spans = By.tagName("span");
	}

	public void login_method(String user_name, String input_password) {
		this.log_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
		driver.findElement(userField).sendKeys(user_name);
		driver.findElement(passwordField).sendKeys(input_password);
		driver.findElement(loginBtn).click();
		}
	
	public void selectDropdownByIndex(int element_number) {
		this.DropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(sortingDropdownList)); //Espera a que el elemento aparezca en la página
		Select selectSortingList = new Select (driver.findElement(sortingDropdownList));
		selectSortingList.selectByIndex(element_number);
		}
	
	public void selectDropdownByVisibleText(String visible_text) {
		this.DropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(sortingDropdownList)); //Espera a que el elemento aparezca en la página
		Select selectSortingList = new Select (driver.findElement(sortingDropdownList));
		selectSortingList.selectByVisibleText(visible_text);
		}
	
	public void selectDropdownByValue(String value) {
		this.DropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(sortingDropdownList)); //Espera a que el elemento aparezca en la página
		Select selectSortingList = new Select (driver.findElement(sortingDropdownList));
		selectSortingList.selectByValue(value);
		}
	//Obtener todos los input elements presentes en la página <WebElements>
	public void verifyFields() {
		List<WebElement> loginPageFields = driver.findElements(fields);
		System.out.println("Input elements in login page: " + loginPageFields.size() + "\n");
		}
	
	//Obtener todos los input y spans elements presentes en la página <WebElements>
	public void LoginByElementsFields() {
		this.log_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
		List<WebElement> loginPageFields = driver.findElements(fields);
		loginPageFields.get(0).sendKeys(constants.USER_NAME);
		loginPageFields.get(1).sendKeys(constants.CORRECT_PASSWORD);
		loginPageFields.get(2).click();
		this.DropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(sortingDropdownList));
		List<WebElement> spanPageElements = driver.findElements(spans);
		Assert.assertTrue(spanPageElements.get(0).getText().contains(constants.SUCCESS_STRING));
		}
	
	public String get_title() {
		this.log_btn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
		String title_text =driver.getTitle();
		return (title_text);
		}
	
}
