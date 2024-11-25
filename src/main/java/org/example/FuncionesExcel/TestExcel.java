package org.example.FuncionesExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.example.Funciones.FuncionesGlobales;

import java.io.IOException;

public class TestExcel {
    private WebDriver driver;
    private final int tg = 1;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Drivers\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void test1() throws InterruptedException, IOException, InvalidFormatException {
        WebDriver driver = this.driver;
        FuncionesGlobales f = new FuncionesGlobales(driver);
        FunExcel fe = new FunExcel(driver);

        f.Navegar("https://demoqa.com/text-box", tg);
        String ruta = "C:\\Users\\Juan Navarro\\practicas\\Datos_ok.xlsx";
        int filas = fe.getRowCount(ruta, "Hoja1");

        for (int r = 2; r <= filas; r++) {
            String nombre = fe.readData(ruta, "Hoja1", r, 1);
            String email = fe.readData(ruta, "Hoja1", r, 2);
            String dir1 = fe.readData(ruta, "Hoja1", r, 3);
            String dir2 = fe.readData(ruta, "Hoja1", r, 4);

            f.TextoMixto("id", "userName", nombre, tg);
            f.TextoMixto("id", "userEmail", email, tg);
            f.TextoMixto("id", "currentAddress", dir1, tg);
            f.TextoMixto("id", "permanentAddress", dir2, tg);
            f.ClickMixto("id", "submit", tg);

            String e = f.Existe("id", "name", tg);
            if ("Existe".equals(e)) {
                System.out.println("El elemento se insertó correctamente");
                fe.writeData(ruta, "Hoja1", r, 5, "Insertado");
            } else {
                System.out.println("No se insertó");
                fe.writeData(ruta, "Hoja1", r, 5, "Error");
            }
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
