package org.example.Funciones;

import org.openqa.selenium.By;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class FuncionesGlobales {
    private WebDriver driver;

    public FuncionesGlobales(WebDriver driver) {
        this.driver = driver;
    }

    public void Tiempo(double tie) throws InterruptedException {
        Thread.sleep((int) (tie * 1000));
    }

    public void Navegar(String url, double tiempo) throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
        Tiempo(tiempo);
    }

    public WebElement SEX(String selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement val = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", val);
        return driver.findElement(By.xpath(selector));
    }

    public WebElement SEI(String selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement val = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(selector)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", val);
        return driver.findElement(By.id(selector));
    }

    public void TextoMixto(String tipo, String selector, String texto, double tiempo) throws InterruptedException {
        try {
            WebElement val = tipo.equals("xpath") ? SEX(selector) : SEI(selector);
            val.clear();
            val.sendKeys(texto);
            Tiempo(tiempo);
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + selector);
        }
    }

    public void ClickMixto(String tipo, String selector, double tiempo) throws InterruptedException {
        try {
            WebElement val = tipo.equals("xpath") ? SEX(selector) : SEI(selector);
            val.click();
            Tiempo(tiempo);
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + selector);
        }
    }

    public void SelectXpathText(String xpath, String texto, double tiempo) throws InterruptedException {
        try {
            WebElement val = SEX(xpath);
            Select select = new Select(val);
            select.selectByVisibleText(texto);
            System.out.println("El campo seleccionado es " + texto);
            Tiempo(tiempo);
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + xpath);
        }
    }

    public void SelectXpathType(String xpath, String tipo, String dato, double tiempo) throws InterruptedException {
        try {
            WebElement val = SEX(xpath);
            Select select = new Select(val);
            switch (tipo) {
                case "text":
                    select.selectByVisibleText(dato);
                    break;
                case "index":
                    select.selectByIndex(Integer.parseInt(dato));
                    break;
                case "value":
                    select.selectByValue(dato);
                    break;
            }
            System.out.println("El campo seleccionado es " + dato);
            Tiempo(tiempo);
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + xpath);
        }
    }

    public void UploadXpath(String xpath, String ruta, double tiempo) throws InterruptedException {
        try {
            WebElement val = SEX(xpath);
            val.sendKeys(ruta);
            System.out.println("Se carga la imagen " + ruta);
            Tiempo(tiempo);
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + xpath);
        }
    }

    public void CheckXpath(String xpath, double tiempo) throws InterruptedException {
        try {
            WebElement val = SEX(xpath);
            val.click();
            System.out.println("Click en el elemento " + xpath);
            Tiempo(tiempo);
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + xpath);
        }
    }

    public String Existe(String tipo, String selector, double tiempo) throws InterruptedException {
        try {
            WebElement val = tipo.equals("xpath") ? SEX(selector) : SEI(selector);
            System.out.println("Elemento encontrado: " + selector);
            Tiempo(tiempo);
            return "Existe";
        } catch (TimeoutException ex) {
            System.out.println(ex.getMessage());
            System.out.println("No se encontró el Elemento " + selector);
            return "No Existe";
        }
    }

    public void Salida() {
        System.out.println("Se termina la prueba exitosamente");
    }
}
