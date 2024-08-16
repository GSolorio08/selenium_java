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
import org.openqa.selenium.chrome.ChromeOptions;
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



public class TS01_LoginTest extends CommonConditions {
	
	@Test(description = "Login Incorrecto")
	public void TC_Smoke_01_WRG_PASSWORD() {
		helper_functions.setWindowSize(driver, "window_size", 800, 600);
		webPage.login_method(constants.USER_NAME, constants.WRONG_PASSWORD); // Llamar al método de instancia en el objeto pageLogin
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(constants.TIMEOUT_ms)); //Espera a que cargue el DOM hasta el valor definido en TIMEOUT_ms
		Assert.assertTrue(driver.findElement(page_elements.assertElement_TC1).getText().contains(constants.FAIL_STRING));
	}
	
	@Test(description = "Login Correcto")
	public void TC_Smoke_02_RIGHT_PASSWORD() {
		helper_functions.setWindowSize(driver, "fullscreen", 0, 0);
		webPage.login_method(constants.USER_NAME, constants.CORRECT_PASSWORD); // Llamar al método de instancia en el objeto pageLogin
		Assert.assertTrue(driver.findElement(page_elements.assertElement_TC2).getText().contains(constants.SUCCESS_STRING));
	}
	
}