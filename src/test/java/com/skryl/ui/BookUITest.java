package com.skryl.ui;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;
import com.microsoft.playwright.*;
import com.skryl.configuration.ApplicationConfig;
import com.skryl.ui.pages.LoginPage;
import com.skryl.ui.pages.MainPage;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

@Slf4j
public class BookUITest {
    static Playwright playwright;
    static Browser browser;

    BrowserContext context;
    Page page;

    @BeforeClass
    static void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(150));
    }

    @AfterClass
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeMethod
    void createContextAndPage() {
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        page = context.newPage();
    }

    @AfterMethod
    void closeContext(ITestContext testInfo) {
        var playwrightTestResultFolder = System.getProperty("user.dir") + "/build/playwright-results";
        var traceZip = "%s/%s.zip".formatted(playwrightTestResultFolder, testInfo.getName().replace(" ", "_"));
        log.info("Trace zip file path: %s".formatted(traceZip));
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceZip)));
        context.close();
        ReportPortal.emitLog(traceZip, LogLevel.INFO.name(), new Date(), new File(traceZip));
        log.info("RP_MESSAGE#FILE#{}#{}", new File(traceZip).getAbsolutePath(), "I'm logging content via trace file");
    }

    @Test(testName = "Test #1 User login and create book")
    void userLoginAndCreateBook() {
        var config = ConfigFactory.create(ApplicationConfig.class);
        var baseUrl = config.uiUrl();
        new LoginPage(page)
                .goToLoginPage(baseUrl)
                .enterUserName("test")
                .enterPassword("test")
                .clickLogin()
                .addNewBook()
                .enterBookName("Book1")
                .enterISBNnumber("11111-11111")
                .chooseCategory(MainPage.BookCategory.Magazine)
                .chooseFormat(MainPage.BookFormat.eBook)
                .createBook();
    }
}
