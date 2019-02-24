package me.funapi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class Util {
    private WebDriver driver;
    private Robot robot;
    private WallPostThread thread;
    private Clipboard clip;

    public Util() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://xui.ptlogin2.qq.com/cgi-bin/xlogin?proxy_url=https%3A//qzs.qq.com/qzone/v6/portal/proxy.html&daid=5&&hide_title_bar=1&low_login=0&qlogin_auto_login=1&no_verifyimg=1&link_target=blank&appid=549000912&style=22&target=self&s_url=https%3A%2F%2Fqzs.qzone.qq.com%2Fqzone%2Fv5%2Floginsucc.html%3Fpara%3Dizone&pt_qr_app=%E6%89%8B%E6%9C%BAQQ%E7%A9%BA%E9%97%B4&pt_qr_link=http%3A//z.qzone.com/download.html&self_regurl=https%3A//qzs.qq.com/qzone/v6/reg/index.html&pt_qr_help_link=http%3A//z.qzone.com/download.html&pt_no_auth=1");
        clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        thread = new WallPostThread();
        thread.start();
    }

    public void add(Wall wall) {
        thread.add(wall);
    }

    public void post(Wall wall) {
        try {
            driver.findElement(By.id("$1_substitutor_content")).click();
        } catch (Exception e) {
        }
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Demo.CQ.logDebug("[Wall-Selenium]", "准备访问剪切板");
        Transferable tText = new StringSelection(getInfo(wall));
        clip.setContents(tText, null);
        Demo.CQ.logDebug("[Wall-Selenium]", "准备粘贴");
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Demo.CQ.logDebug("[Wall-Selenium]", "准备回车");
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public String getInfo(Wall wall) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!wall.isAnonymous()) {
//            stringBuilder.append("来自QQ " + wall.getNick() + "(" + String.valueOf(wall.getQQ()) + ")\n");
            stringBuilder.append("来自QQ " + "(" + String.valueOf(wall.getQQ()) + ")\n");
        } else {
            stringBuilder.append("来自匿名:\n");
        }
        stringBuilder.append(wall.getContent() == null ? "无(错误)" : wall.getContent());
        return stringBuilder.toString();
    }

    public void exit() {
        thread.interrupt();
        driver.quit();
    }
}
