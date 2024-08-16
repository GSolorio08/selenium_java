package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import config.constants;
import config.page_elements;
import helper_functions.Helper;
import pages.page_methods;

public class CommonConditions {
	Helper helper_functions = new Helper(); // Declarar el objeto aquí para que esté disponible en los métodos de prueba
	protected WebDriver driver;
	protected page_methods webPage;  // Declarar el objeto aquí para que esté disponible en los métodos de prueba
	
	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", constants.CHROME_DRIVER_PATH);
		/*
		 * Para correr las pruebas en modo headless, es decir sin entorno gráfico
		 */
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions); //Se agrega chromeOptions para poder correrlo en headeless
		//driver.manage().window().maximize();
		driver.navigate().to(constants.WEB_PAGE);
		//helper_functions.mSeconds_wait(constants.WAIT_mSECONDS);
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(constants.TIMEOUT_ms)); //Espera a que cargue el DOM hasta el valor definido en TIMEOUT_ms
		webPage = new page_methods(driver, 
									page_elements.userField, 
									page_elements.passwordField, 
									page_elements.loginBtn, 
									page_elements.sortingDropdownList); // Inicializar el objeto pageLogin
		//el constructor recibe los nombres de los campos en la pagina web
	}
	
	@AfterMethod
	//Destroy the objects used in test
	public void tearDown(ITestResult result) {
		System.out.println(helper_functions.PrintResult(result));
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
