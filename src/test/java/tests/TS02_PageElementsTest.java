package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import config.constants;

public class TS02_PageElementsTest extends CommonConditions{
	@Test(description = "Validacion de la lista dropdown de ordenamiento")
	public void TC_Smoke_03_Dropdown_Validation() {
		helper_functions.setWindowSize(driver, "maximize", 800, 600);
		webPage.login_method(constants.USER_NAME, constants.CORRECT_PASSWORD); // Llamar al método de instancia en el objeto pageLogin
		webPage.selectDropdownByIndex(constants.DROP_DW_LIST_INDEX);
		webPage.selectDropdownByVisibleText(constants.VISIBLE_TEXT);
		webPage.selectDropdownByValue(constants.VALUE);
	}
	@Test(description = "Obtener todos los elementos input de la pagina web")
	public void TC_Smoke_04_Get_InputElements() {
		webPage.verifyFields();
	}
	
	@Test(description = "Login correcto a através del array elements de la pagina web")
	public void TC_Smoke_05_Get_loginByElements() {
		webPage.LoginByElementsFields();
	}
	
	@Test(description = "Validacion del título de la página web")
	public void TC_Smoke_06_Verify_WebTitle() {
		Assert.assertEquals(webPage.get_title(),constants.TITLE_TEXT);
	}
}
