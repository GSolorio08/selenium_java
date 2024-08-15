package tests;

import java.lang.invoke.ConstantCallSite;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import helper_functions.Helper;
import pages.page_methods;
import config.constants;
import config.page_elements;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;



public class Tests {
	
	Helper helper_functions = new Helper(); // Declarar el objeto aquí para que esté disponible en los métodos de prueba
	private WebDriver driver;
	private page_methods webPage;  // Declarar el objeto aquí para que esté disponible en los métodos de prueba
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", constants.CHROME_DRIVER_PATH);
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		helper_functions.setWindowSize(driver, "maximize", 0, 0);

		driver.navigate().to(constants.WEB_PAGE);
		/* Funciones para crear y navegar en nuevas pestañas. 
		 * OJO  el TC correrá sobre el selected tab, asi que siempre hay que ajustar el index
		 * helper_functions.openNewTab(driver, constants.NEW_TAB_WEB_PAGE);
		 * helper_functions.NavigateSelectTab(driver, 1, "https://www.youtube.com/");
		 * helper_functions.SelectTab(driver, 0);
		 */
		//helper_functions.mSeconds_wait(constants.WAIT_mSECONDS);
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(constants.TIMEOUT_ms)); //Espera a que cargue el DOM hasta el valor definido en TIMEOUT_ms
		webPage = new page_methods(driver, 
									page_elements.userField, 
									page_elements.passwordField, 
									page_elements.loginBtn, 
									page_elements.sortingDropdownList); // Inicializar el objeto pageLogin
		//el constructor recibe los nombres de los campos en la pagina web
	}
	@Test
	public void TC_Smoke_01_WRG_PASSWORD() {
		helper_functions.setWindowSize(driver, "window_size", 800, 600);
		webPage.login_method(constants.USER_NAME, constants.WRONG_PASSWORD); // Llamar al método de instancia en el objeto pageLogin
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(constants.TIMEOUT_ms)); //Espera a que cargue el DOM hasta el valor definido en TIMEOUT_ms
		Assert.assertTrue(driver.findElement(page_elements.assertElement_TC1).getText().contains(constants.FAIL_STRING));
	}
	
	@Test
	public void TC_Smoke_02_RIGHT_PASSWORD() {
		helper_functions.setWindowSize(driver, "fullscreen", 0, 0);
		webPage.login_method(constants.USER_NAME, constants.CORRECT_PASSWORD); // Llamar al método de instancia en el objeto pageLogin
		Assert.assertTrue(driver.findElement(page_elements.assertElement_TC2).getText().contains(constants.SUCCESS_STRING));
	}
	
	@Test
	public void TC_Smoke_03_Dropdown_Validation() {
		helper_functions.setWindowSize(driver, "maximize", 800, 600);
		webPage.login_method(constants.USER_NAME, constants.CORRECT_PASSWORD); // Llamar al método de instancia en el objeto pageLogin
		webPage.selectDropdownByIndex(constants.DROP_DW_LIST_INDEX);
		webPage.selectDropdownByVisibleText(constants.VISIBLE_TEXT);
		webPage.selectDropdownByValue(constants.VALUE);
	}
	@Test
	public void TC_Smoke_04_Get_InputElements() {
		webPage.verifyFields();
	}
	
	@Test
	public void TC_Smoke_05_Get_loginByElements() {
		webPage.LoginByElementsFields();
	}
	
	@Test
	public void TC_Smoke_06_Verify_WebTitle() {
		Assert.assertEquals(webPage.get_title(),constants.TITLE_TEXT);
	}
	
	@AfterMethod
	//Destroy the objects used in test
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			String TC_Error_Name = result.getMethod().getMethodName();
			helper_functions.takeErrorScreenshot(driver, TC_Error_Name);
		}
		else if (constants.TAKE_OK_SCREENSHOT) {
			String TC_Name = result.getMethod().getMethodName();
			helper_functions.takeScreenshot(driver, TC_Name);
		}

		driver.quit();
	}
	
}