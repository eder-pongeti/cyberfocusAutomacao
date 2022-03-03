package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class OpenSiteTest {
	//Aqui eu criei uma vari�vel para instanciar o driver
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver" , "C:\\Users\\inovacao\\Downloads\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
				
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		String baseURL = "http://demo.automationtesting.in/Register.html";
		//Actions serve para emular gestos complexos do usu�rio
		Actions builder = new Actions(driver);
		//JavascriptExecutor serve para rodar comandos javascript pelo selenium
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		//Cen�rio 1 - Automatizar o formul�rio presente ao acessar o menu de navega��o �Register�
		driver.get(baseURL);
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[4]/a")).click();//Menu Switch To
		driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[4]/ul/li[3]/a")).click();//Clico no Frames
		Thread.sleep(2000);
		//Aqui eu saio do popup do Google Ads
		builder.moveByOffset( 10, 20 ).click().build().perform();
		Thread.sleep(5000);
		//Cen�rio 2 - No menu de navega��o �Switch > Frames� voc� deve escrever dentro de um frame com sua automa��o.
		driver.switchTo().frame("singleframe");
		driver.findElement(By.xpath("/html/body/section/div/div/div/input")).click();//clico no textbox
		driver.findElement(By.xpath("/html/body/section/div/div/div/input")).sendKeys("Escrevi com minha automa��o!");		
		//driver.wait();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		//Cen�rio 3 - No menu de navega��o �Widgets > Datapicker� voc� deve inserir sua	data de nascimento nos dois campos
		driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[5]/a")).click();
		driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[5]/ul/li[3]/a")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"datepicker1\"]")).click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].removeAttribute('readonly');", driver.findElement(By.xpath("//*[@id=\"datepicker1\"]")));
		driver.findElement(By.xpath("//*[@id=\"datepicker1\"]")).sendKeys("10/14/1983");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id=\"datepicker1\"]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"datepicker2\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"datepicker2\"]")).sendKeys("10/14/1983");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"datepicker2\"]")).sendKeys(Keys.ENTER);
		//Cen�rio 4 - No menu de navega��o �Widgets > Slider� voc� deve fazer com que sua automa��o movimente 50% da barra.
		driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[5]/a")).click();
		driver.findElement(By.xpath("/html/body/header/nav/div/div[2]/ul/li[5]/ul/li[4]/a")).click();
		builder.clickAndHold(driver.findElement(By.xpath("/html/body/section/div[1]/div/div/div/a"))).moveByOffset(340, 0).release().perform();
		
	}

}
