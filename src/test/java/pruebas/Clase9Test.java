package pruebas;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Clase9Test {
	String url = "https://demoqa.com/alerts";
	WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test
	public void alertaNotificacion() {
		driver.findElement(By.cssSelector("#alertButton")).click();
		
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept(); // Clic en aceptar de la alerta
	}
	
	@Test
	public void alertaDemorada() {
		driver.findElement(By.xpath("//button[@id='timerAlertButton']")).click();
		
		// Introducir una espera porque la alerta demora 5 seg en aparecer
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.alertIsPresent());
		
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void alertaConfirmacion() {
		driver.findElement(By.id("confirmButton")).click();
		
		driver.switchTo().alert().dismiss(); // Hacer clic en Cancelar
	}
	
	@Test
	public void alertaConText() {
		driver.findElement(By.cssSelector("#promtButton")).click();
		
		Alert alerta = driver.switchTo().alert();
		alerta.sendKeys("Clase de Automatizacion!");
		alerta.accept();
	}
}
