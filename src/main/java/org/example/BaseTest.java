package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.example.Funciones.FuncionesGlobales;
import org.example.Funciones.PaginaLogin;
import java.time.Duration;

public class BaseTest{
    private WebDriver driver;
    private final double t = 0.5;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Drivers\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void test1() throws InterruptedException {
        FuncionesGlobales f = new FuncionesGlobales(driver);
        PaginaLogin pg = new PaginaLogin(driver);
        pg.loginMaster("https://www.saucedemo.com/", "Juan", "Admin123", t);
    }

    @Test
    public void test2() throws InterruptedException {
        FuncionesGlobales f = new FuncionesGlobales(driver);
        f.Navegar("https://demoqa.com/upload-download", t);
        f.UploadXpath("//input[contains(@id,'uploadFile')]", "C:\\Users\\Juan Navarro\\Selenium\\src\\cpu.png", 3);
    }

    @Test
    public void test3() throws InterruptedException {
        FuncionesGlobales f = new FuncionesGlobales(driver);
        f.Navegar("https://demoqa.com/automation-practice-form", t);
        f.CheckXpath("//label[contains(.,'Sports')]", t);
    }

    @Test
    public void test4() throws InterruptedException {
        FuncionesGlobales f = new FuncionesGlobales(driver);
        f.Navegar("https://demoqa.com/automation-practice-form", t);

        // Usamos un bucle para seleccionar múltiples checkboxes
        for (int n = 1; n <= 3; n++) {
            String selector = "//label[@for='hobbies-checkbox-" + n + "']";
            f.CheckXpath(selector, t);
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        try {
            Thread.sleep(4000); // Pausa para observar el cierre (opcional)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}