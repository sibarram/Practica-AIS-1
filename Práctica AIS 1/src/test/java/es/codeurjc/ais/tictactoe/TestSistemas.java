package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSistemas {
	WebDriver cd1,cd2;
	
	@Before
	public void setUp() throws Exception {
		this.cd1 = new ChromeDriver();
		this.cd2 = new ChromeDriver();
		cd1.get("http://localhost:8080/");
		cd2.get("http://localhost:8080/");
		
		cd1.findElement(By.id("nickname")).sendKeys("Jugador 1");
		cd1.findElement(By.id("startBtn")).click();

		cd2.findElement(By.id("nickname")).sendKeys("Jugador 2");
		cd2.findElement(By.id("startBtn")).click();
		
		cd1.findElement(By.id("cell-3")).click();
		cd2.findElement(By.id("cell-0")).click();
		cd1.findElement(By.id("cell-4")).click();
		cd2.findElement(By.id("cell-1")).click();

	}
	@BeforeClass
	public void setUpClass() {
		System.setProperty("webdriver.chrome.driver","R:\\Práctica AIS 1\\src\\test\\java\\es\\codeurjc\\ais\\tictactoe\\chromedriver.exe" );
		WebApp.start();
	}
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	@After
	public void teardown() {
	if (cd1 != null) {
		cd1.quit();
		}
		if (cd2 != null) {
		cd2.quit();
		}
	}
	@Test
	public void testVictoria_Jugador1() throws InterruptedException {
		cd1.findElement(By.id("cell-5")).click();
		String r1 = "Jugador 1 wins! Jugador 2 looses.";
		Thread.sleep(2000);
		String alertCD1=cd1.switchTo().alert().getText();
		Thread.sleep(2000);
		String alertCD2=cd2.switchTo().alert().getText();
		assertEquals(r1 , alertCD1);
		assertEquals(r1 , alertCD2);
	}
	@Test
	public void testVictoria_Jugador2() throws InterruptedException{
		cd1.findElement(By.id("cell-6"));
		cd2.findElement(By.id("cell-2"));
		
		String r1 = "Jugador 2 wins! Jugador 1 looses.";
		Thread.sleep(2000);
		String alertCD1=cd1.switchTo().alert().getText();
		Thread.sleep(2000);
		String alertCD2=cd2.switchTo().alert().getText();
		assertEquals(r1 , alertCD1);
		assertEquals(r1 , alertCD2);
		
	}
	@Test
	public void empate() throws InterruptedException{
		
		cd1.findElement(By.id("cell-2")).click();
		cd2.findElement(By.id("cell-5")).click();
		cd1.findElement(By.id("cell-6")).click();
		cd2.findElement(By.id("cell-7")).click();
		cd1.findElement(By.id("cell-8")).click();
		
		//Mensaje alerta: Draw!. 
		String r1 = "Draw!";

		Thread.sleep(2000);
		String alertCD1 = cd1.switchTo().alert().getText();

		Thread.sleep(2000);
		String alertCD2 = cd2.switchTo().alert().getText();
		
		assertEquals(r1 ,alertCD1);
		assertEquals(r1 ,alertCD2);
		
	}
}
