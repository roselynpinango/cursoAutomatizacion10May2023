package Edit.EducacionIT_63596;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utilidades.CapturaEvidencia;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Clase5 {
	String url = "http://automationpractice.pl";
	String dirEvidencias = "..\\EducacionIT-63596\\Evidencias\\";
	String nombreArchivo = "Documento de Evidencias.docx";
	File pantalla;
	WebDriver driver;
	
	@BeforeSuite
	public void antesDelSuite() {
		// Se ejecuta una sola vez antes que cualquier @Test
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		
		driver = new ChromeDriver(options);
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	@BeforeTest
	public void antesDelTest() {
		// Se ejecuta antes de cada @Test
	}
	
	
	@Test(priority=100,description="CP01 - Funcionalidad Contactanos Caso Feliz")
	public void irAContactanos() throws InvalidFormatException, IOException, InterruptedException {
		CapturaEvidencia.escribirTituloEnDocumento(
				dirEvidencias + nombreArchivo,
				"Documento de Evidencias - AutomationPractice",
				18);
		
		// Capturar Evidencia 1
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				dirEvidencias + "img.jpg",
				dirEvidencias + nombreArchivo,
				"Paso 1 - Al ingresar en AutomationPractice");
		
		// Paso 1: Hacer clic en Contact Us
		driver.findElement(By.linkText("Contact us")).click();
		
		// Capturar Evidencia 2
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				dirEvidencias + "img.jpg",
				dirEvidencias + nombreArchivo,
				"Paso 2 - Después de hacer clic en Contact Us");
		
		String tituloEsperado = "Contact us - My Store";
		String tituloActual = driver.getTitle(); // Devuelve el titulo actual de la pagina
		
		// Chequeo que el titulo actual de la pagina sea que estabamos esperando
		Assert.assertEquals(tituloActual, tituloEsperado);
		
		// Paso 2: Completar el formulario
		Select selSubject = new Select(driver.findElement(By.id("id_contact")));
		selSubject.selectByVisibleText("Customer service"); // Lista Desplegable Subject Heading
		
		driver.findElement(By.name("from")).sendKeys("correo@gmail.com"); // Correo
		driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("123ABC");
		driver.findElement(By.cssSelector("#fileUpload")).sendKeys("C:\\addIntegerData.txt"); // Adjuntar archivo
		driver.findElement(By.tagName("textarea")).sendKeys("Mensaje de Contacto a la Empresa");
		
		// Capturar Evidencia 3
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				dirEvidencias + "img.jpg",
				dirEvidencias + nombreArchivo,
				"Paso 3 - Después de completar el formulario");
		
		// Paso 3: Hacer clic en Send
		driver.findElement(By.id("submitMessage")).click();
		
		// Capturar Evidencia 4
		CapturaEvidencia.capturarPantallaEnDocumento(
				driver,
				dirEvidencias + "img.jpg",
				dirEvidencias + nombreArchivo,
				"Paso 4 - Luego de enviar el formulario");
		
		// Validación de mensaje de operación exitosa
		WebElement lblMensaje = null;
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Your message has been successfully sent to our tea')]")));
			
			lblMensaje = driver.findElement(By.xpath("//p[contains(text(),'Your message has been successfully sent to our tea')]"));
		} catch (Exception e) {
			
		}
		
		// Si la variable no está vacía, significa que tenemos el mensaje exitoso en pantalla
		Assert.assertNotNull(lblMensaje);
	}
	
	@Test(priority=200,description="CP02 Funcionalidad Buscar Palabra Caso Feliz")
	public void buscarPalabra() throws IOException {
		// Capturar Evidencia
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(dirEvidencias + "01_pantallaPrincipal.jpg"));
		
		WebElement txtBuscador = driver.findElement(By.id("search_query_top"));
		txtBuscador.sendKeys("dress");
		
		// Capturar Evidencia
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(dirEvidencias + "02_palabraABuscar.jpg"));
		
		WebElement btnLupa = driver.findElement(By.name("submit_search"));
		btnLupa.click();
		
		// Capturar Evidencia
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(dirEvidencias + "03_resultadoBusqueda.jpg"));
		
	}
	
	@AfterSuite
	public void despuesDeLaSuite() {
		// Se ejecuta después de terminar todos los @Test
		driver.close();
	}
}
