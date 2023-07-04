package net.realmidc.niuzi.util;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SaveHtml2Image {

    fun html2Image(htmlPath: String): String {
        val imagePath = "./data/Image/achou/achou.jpg";
        val image = File(imagePath);
        if (image.exists()) image.delete();
        var webDriverPath = "data/";
        if (System.getProperties().getProperty("os.name").contains("Windows")){
            webDriverPath += "phantomjs.exe";
        }else {
            webDriverPath += "phantomjs";
        }
        val desiredCapabilities = DesiredCapabilities();
        desiredCapabilities.setCapability("acceptSslCerts", true);
        desiredCapabilities.setCapability("takesScreenshot",true);
        desiredCapabilities.setCapability("cssSelectorsEnabled", true);
        desiredCapabilities.setJavascriptEnabled(true);
        desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, webDriverPath);
        val phantomJSDriver = PhantomJSDriver(desiredCapabilities);
        val driver = phantomJSDriver;
        driver.manage().window().setSize(Dimension(640, 1080));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get(htmlPath);
        driver.navigate().refresh();
        Thread.sleep(8000);
        val pageHtml = driver.getPageSource();
        val srcfile = driver.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcfile, image);
        driver.close();
        return imagePath;
    }

}
