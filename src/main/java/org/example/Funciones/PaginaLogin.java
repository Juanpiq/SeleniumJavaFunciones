package org.example.Funciones;

import org.openqa.selenium.WebDriver;

public class PaginaLogin {
    private WebDriver driver;

    // Constructor
    public PaginaLogin(WebDriver driver) {
        this.driver = driver;
    }

    public void loginMaster(String url, String name, String clave, double t) throws InterruptedException {
        FuncionesGlobales f = new FuncionesGlobales(driver);

        // Navegar a la URL
        f.Navegar(url, t);

        // Ingresar nombre de usuario
        f.TextoMixto("xpath", "//input[contains(@id,'user-name')]", name, t);

        // Ingresar clave
        f.TextoMixto("xpath", "//input[contains(@id,'password')]", clave, t);

        // Hacer clic en el botón de login
        f.ClickMixto("xpath", "//input[contains(@id,'login-button')]", t);

        // Finalizar prueba
        f.Salida();
    }
}

