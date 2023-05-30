package extentReport;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import page_objects.DashboardObjects;
import page_objects.LoginObjects;
import page_objects.UserRoleObjects;

public class ExtentReport {

	WebDriver driver;
	ExtentReports extentReport;
	ExtentHtmlReporter htmlReporter;
	ExtentTest testCase;

	@BeforeMethod
	public void setupMethod(){

		extentReport=new ExtentReports();
		htmlReporter=new ExtentHtmlReporter("ExtentReport.html");
		extentReport.attachReporter(htmlReporter);

		WebDriverManager.chromedriver().setup();

		driver=new ChromeDriver();

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	}

	@Test(priority=1)
	public void verifyPageObjectModel() throws  IOException, InterruptedException  {

		testCase=extentReport.createTest("verify Login");
		testCase.log(Status.INFO, "CHROME Browser Launched");
		testCase.log(Status.INFO, "Navigating to LoginPage : "+"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		LoginObjects login=new LoginObjects(driver);
		testCase.log(Status.INFO, "Verification of LoginPage");
		login.loginAdminUser();
		testCase.log(Status.INFO, "LOGIN STATUS are passed");


		testCase=extentReport.createTest("verify Dashboard Function");
		testCase.log(Status.INFO, "Navigating to Dashboard Function :"+"https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		DashboardObjects verify=new DashboardObjects(driver);
		testCase.log(Status.INFO, "Verification of Dashboard Function");
		verify.checkDashboard();

		testCase.log(Status.INFO, "DASHBOARD FUNCTION  are passed");
		TakesScreenshot screenshot1 =(TakesScreenshot) driver;
		File sourceFile1=screenshot1.getScreenshotAs(OutputType.FILE);
		File destinationFile1 =new File("dashboard.png");
		FileHandler.copy(sourceFile1, destinationFile1);
		testCase.addScreenCaptureFromPath("dashboard.png");


		testCase=extentReport.createTest("verify Users");
		testCase.log(Status.INFO, "Navigating to Users :"+"https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers" );
		UserRoleObjects check=new UserRoleObjects(driver);
		testCase.log(Status.INFO, "Verification of Users");
		check.checkUserRole();

		testCase.log(Status.INFO, "USERS  are passed");
		TakesScreenshot screenshot2 =(TakesScreenshot) driver;
		File sourceFile2=screenshot2.getScreenshotAs(OutputType.FILE);
		File destinationFile2 =new File("Users.png");
		FileHandler.copy(sourceFile2, destinationFile2);
		testCase.addScreenCaptureFromPath("Users.png");

	}

	@AfterTest
	public void afterTest(){

		driver.quit();
		extentReport.flush();
	} 
}
