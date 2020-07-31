package dms.auctionspider.collecting

import dms.auctionspider.collecting.boilers.TypeBoiler
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.util.concurrent.TimeUnit

class Hatcher {
    private val egg:Egg;
    private val driver:ChromeDriver;
    constructor(egg:Egg){
        this.egg=egg;

        driver=ChromeDriver(ChromeOptions().addArguments("--headless"))
    }
    fun hatch():Bean{
        driver.load(egg.url)
        //todo 没错，就是在这里，搜索各种各样的数据，明天搞吧
        val element=driver.findElementByXPath(egg.house.platform.desc_xpath);
        val type=TypeBoiler<String>(element)
        


    }



}


private fun ChromeDriver.load(url: String) {
    try{
        manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        manage().timeouts().setScriptTimeout(8, TimeUnit.SECONDS)
        manage().timeouts().pageLoadTimeout(8, TimeUnit.SECONDS)
        get(url)
    }catch (e: TimeoutException){
        print("加载页面太慢，停止加载，继续下一步操作")
        executeScript("window.stop()")
    }
    var scoll_init = 100;
    while (scoll_init < 12000) {
        executeScript("window.scrollTo(0," + scoll_init + ")");
        Thread.sleep(300)
        scoll_init = scoll_init + 1000
    }
}
