package config;

import java.time.Duration;

public class constants {
	// Constantes públicas (si es necesario acceder desde fuera)
	public static final boolean TAKE_OK_SCREENSHOT = false;
    public static final int WAIT_mSECONDS = 1500;
    public static final int TIMEOUT_ms = 8000;
    public static final String CHROME_DRIVER_PATH = "../drivers/chromedriver-win64/chromedriver.exe";
    public static final String SCREENSHOT_PATH_PASS = "./screenshots/success";
    public static final String SCREENSHOT_PATH_FAILED = "./screenshots/failed";
	
    // Constantes privadas (uso solo dentro de esta clase)
    public static final String USER_NAME = "standard_user";
    public static final String WRONG_PASSWORD = "anything";
    public static final String CORRECT_PASSWORD = "secret_sauce";
    public static final String FAIL_STRING = "Epic sadface:";
    public static final String SUCCESS_STRING = "Products";
    public static final String WEB_PAGE = "https://www.saucedemo.com/";
    public static final String NEW_TAB_WEB_PAGE = "https://www.google.com/";
    public static final String TITLE_TEXT = "Swag Labs";
    
	//Dropdown List elements
    public static final int DROP_DW_LIST_INDEX = 3;
    public static final String VISIBLE_TEXT = "Price (low to high)";
    public static final String VALUE = "za";

}
