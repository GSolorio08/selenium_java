package config;

import org.openqa.selenium.By;

public class page_elements {
    //Nombre de los campos a buscar dentro de la pagina web
	public static final  By userField = By.name("user-name");
	public static final  By passwordField = By.name("password");
	public static final  By loginBtn = By.name("login-button");
	public static final  By sortingDropdownList = By.className("product_sort_container");
	
	//Se define los nombres de los headers / containers que se usaran para validar los TC
	public static final  By assertElement_TC1 = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");
	public static final  By assertElement_TC2 = By.xpath("//*[@id=\"header_container\"]/div[2]/span");

}
