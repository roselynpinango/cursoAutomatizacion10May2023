package Edit.EducacionIT_63596;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class Clase4 {
	String url = "http://automationpractice.pl/index.php";
	WebDriver driver;
	
	@Test
	public void registrarUsuario() {
		driver = new FirefoxDriver();
		driver.get(url); // Abrir la página
		driver.manage().window().maximize(); // Maximizar la ventana 
		driver.manage().deleteAllCookies(); // Borrar las cookies
		
		// Paso 1: Hacer clic en Sign In
		driver.findElement(By.partialLinkText("Sign")).click();
		
		// Paso 2: Escribir el correo y hacer clic en el botón 
		driver.findElement(By.name("email_create")).sendKeys("correo21Abr@gmail.com"); // Campo de Texto Email
		driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click(); // Boton Create an Account
		
		// Es necesaria una espera porque hay una demora en la carga del formulario
		// Espera Implicita
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Espera Explicita
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#id_gender1")));
		
		// Paso 3: Completar el formulario
		driver.findElement(By.cssSelector("#id_gender1")).click(); // Radio button Title
		driver.findElement(By.id("customer_firstname")).sendKeys("Omar"); // Nombre
		driver.findElement(By.name("customer_lastname")).sendKeys("Lopez"); // Apellido
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		
		// Forma 1 para generar correo aleatorio
		//String email = "correo" + Math.random() + "@gmail.com"; // correo98415698@gmail.com
		// Forma 2 Libreria javafaker
		Faker faker = new Faker();
		String email = faker.internet().emailAddress();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email); // Campo Email
		driver.findElement(By.id("passwd")).sendKeys("1q2w3e4r5t"); // Contraseña
		
		Select selDays = new Select(driver.findElement(By.name("days")));
		selDays.selectByVisibleText("18  "); // Lista de días
		
		Select selMonths = new Select(driver.findElement(By.xpath("//select[@id='months']")));
		selMonths.selectByValue("6"); // Lista de meses
		
		Select selYear = new Select(driver.findElement(By.cssSelector("#years")));
		selYear.selectByIndex(30); // Lista de años
		
		driver.findElement(By.id("newsletter")).click(); // Check Newsletter
		driver.findElement(By.name("optin")).click(); // Check Offers
		
		// Paso 4: Hacer clic en el botón Register
		driver.findElement(By.cssSelector("#submitAccount")).click();
	}
}
