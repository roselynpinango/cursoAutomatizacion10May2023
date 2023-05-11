package Edit.EducacionIT_63596;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Clase2 {
	// Variables (valores comunes que vamos a utilizar)
	String url = "http://automationpractice.pl/index.php";
	
	// Métodos (acciones que se van a ejecutar)
	@Test 
	public void buscarPalabraFirefox() {
		// Acciones o Paso a Paso para ejecutar la prueba
		// 1. Indicar qué navegador vamos a utilizar 
		WebDriver navegador = new FirefoxDriver();
		
		// 2. Abrir el navegador en la página que vamos a probar
		navegador.get(url);
		
		// 3. Escribir la palabra que queremos buscar
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress"); //Escribir el valor en el campo
	
		// 4. Hacer la búsqueda (simulando que presionamos ENTER)
		txtBuscador.sendKeys(Keys.ENTER); //Simula que presiona esa tecla 
		
		// 5. Cerrar el navegador
		
	}
	
	@Test 
	public void buscarPalabraChrome() {
		// Acciones o Paso a Paso para ejecutar la prueba
		// 1. Indicar qué navegador vamos a utilizar 
		WebDriverManager.chromedriver().setup();
		WebDriver navegador = new ChromeDriver();
		
		// 2. Abrir el navegador en la página que vamos a probar
		navegador.get(url);
		
		// 3. Escribir la palabra que queremos buscar
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress"); //Escribir el valor en el campo
	
		// 4. Hacer la búsqueda (simulando que presionamos ENTER)
		txtBuscador.sendKeys(Keys.ENTER); //Simula que presiona esa tecla 
		
		// 5. Cerrar el navegador
		navegador.close();
	}
	
	@Test 
	public void buscarPalabraEdge() {
		// Acciones o Paso a Paso para ejecutar la prueba
		// 1. Indicar qué navegador vamos a utilizar 
		WebDriver navegador = new EdgeDriver();
		
		// 2. Abrir el navegador en la página que vamos a probar
		navegador.get(url);
		
		// 3. Escribir la palabra que queremos buscar
		WebElement txtBuscador = navegador.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress"); //Escribir el valor en el campo
	
		// 4. Hacer la búsqueda (simulando que presionamos ENTER)
		txtBuscador.sendKeys(Keys.ENTER); //Simula que presiona esa tecla 
		
		// 5. Cerrar el navegador
		
	}
}
