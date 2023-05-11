package pruebas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilidades.DatosExcel;
import io.github.bonigarcia.wdm.WebDriverManager;
import paginas.PaginaInicio;
import paginas.PaginaLogin;

public class Clase8Test {
	String url = "http://automationpractice.pl/";
	String rutaExcel = "..\\EducacionIT-63596\\Datos\\";
	String nombreArchivo = "DatosIniciarSesion.xlsx";
	String nombreHoja = "Hoja1";
	WebDriver driver;
	
	@BeforeSuite
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test(dataProvider="obtenerDatosExcel")
	public void iniciarSesion(String email, String password) {
		// Hacer clic en Sign In
		PaginaInicio inicio = new PaginaInicio(driver);
		inicio.hacerClicEnSignIn();
		
		// Ingresar las credenciales (email, password)
		PaginaLogin login = new PaginaLogin(driver);
		login.escribirEmail(email);
		login.escribirPassword(password);
		login.hacerClicEnLogin();
		
		/* Para que el metodo sea capaz de procesar datos
		 * positivos y negativos 
		 * Tenemos que agregar alguna validación que haga que se 
		 * vuelva a la posición original en caso de haber ingresado
		 */
	}
	
	@DataProvider
	public Object[][] obtenerDatosExcel() throws Exception {
		// Armar el arreglo de datos a partir de la info de un Excel
		return DatosExcel.leerExcel(rutaExcel + nombreArchivo, nombreHoja);
	}
	
	@DataProvider(name="Datos Login")
	public Object[][] obtenerDatos() {
		Object[][] datos = new Object[5][2];
		datos[0][0] = "abc@gmail.com";
		datos[0][1] = "23r3q2";
		
		datos[1][0] = "def@gmail.com";
		datos[1][1] = "yg45e45te";
		
		datos[2][0] = "ghi@gmail.com";
		datos[2][1] = "34t34w4w";
		
		datos[3][0] = "jkl@gmail.com";
		datos[3][1] = "3r34wqr43";
		
		datos[4][0] = "mno@gmail.com";
		datos[4][1] = "32r3wrw";
		
		return datos; // este método devuelve el arreglo datos
	}
	
	@AfterSuite
	public void tearDown() {
		driver.close();
	}
}
