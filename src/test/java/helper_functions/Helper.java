package helper_functions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import config.constants;


public class Helper {
	private ArrayList<String> tabs;
	
	/** Funicón para esperar X milisegundos con un handler de exceptions **/
	public void mSeconds_wait(int mseconds) {
		try {
			Thread.sleep(mseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Tomar un screenshot, y se guarda en la ruta especificada (en este caso es de success) **/
	public void takeScreenshot(WebDriver driver, String testCaseName) {
		File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = LocalDateTime.now().format(dtf);
			String screenshotName = constants.SCREENSHOT_PATH_PASS+ "/" + testCaseName + "_" + currentDateTime+ ".png";
			FileUtils.copyFile(myScreenshot, new File (screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Tomar un screenshot, como es de Error se pone el prefijo "Error y se guarda en la ruta especificada" **/
	public void takeErrorScreenshot(WebDriver driver, String testCaseName) {
		File myScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
	        String currentDateTime = LocalDateTime.now().format(dtf);
			String screenshotName = constants.SCREENSHOT_PATH_FAILED + "/" + "Error_" + testCaseName + "_" + currentDateTime+ ".png";
			FileUtils.copyFile(myScreenshot, new File (screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/** Crear una nueva tab y abrir cierta URL **/
	public void openNewTab(WebDriver driver, String newTabWebPage) {
	    JavascriptExecutor executorJS = (JavascriptExecutor) driver;  // Casteo necesario
	    executorJS.executeScript("window.open('" + newTabWebPage + "')");
	}
	
	/** Cambiar el focus a una tab especificada **/
	public void SelectTab(WebDriver driver, int index) {
		tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index));
	}
	/** Cambiar de pagina web en tab seleccionada **/
	public void NavigateSelectTab(WebDriver driver, int index, String toURL) {
		tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(index)).navigate().to(toURL);;
	}
	
	/** DIFERENTES FORMA DE MANIPULAR EL TAMAÑO DEL BROWSER**/
	public void setWindowSize(WebDriver driver, String size, int x, int y) {
		switch(size) {
			case "maximize":
				driver.manage().window().maximize();
		    break;
			case "fullscreen":
				driver.manage().window().fullscreen();
		    break;
			default:
				driver.manage().window().setSize(new Dimension(x,y));  //<- Posicion determianda, requiere import org.openqa.selenium.Point
				/* for(int i=0; i<=800;i++)
				*	driver.manage().window().setPosition(new Point(i,i));
				*/
		}
	}
}
