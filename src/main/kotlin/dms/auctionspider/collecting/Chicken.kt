package dms.auctionspider.collecting

import dms.auctionspider.City
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.edge.EdgeOptions
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

//产出Egg，每一个平台都差不多，使用House内的“pageArg”函数与PHP调用
//原始URL与城市
class Chicken{
    val house:House;
    val city: City;
//    val egg_Array:ArrayList<Egg>;
//    var finished=false;
    private var pages=0;
    private val driver: ChromeDriver
    constructor(house:House,city: City) {
        this.city=city;
        this.house=house;
//        this.egg_Array= ArrayList()
        driver=ChromeDriver(ChromeOptions().addArguments("--headless"))
    }
    //当前城市

    //获取总页数
    //搜集所有页数
    //todo 此处有报错
    fun spawnEggs(){
        //搜集该页中所有的egg， 多页则启用多线程
        //调用spawnegg（i）
       this.pages= getPages()
        var index=1;
       while(index<=pages){
           thread (start = true){
               spawnEgg(index)
           }
           index=index+1;
       }
        //完成搜集页数后，就关闭浏览器
        driver.close()
        driver.quit()
//        println("Now We have "+egg_Array.size+" Eggs for city"+city.chinese)
//        finished=true
    }
    //返回页面的总页数
    private fun getPages():Int{
        driver.load(house.platform.url)
        var pages=Integer.valueOf(driver.findElementByXPath(house.platform.page_xpath).text);
     return   pages;
    }
    private fun spawnEgg(page:Int){
        driver.load(house.platform.url+house.platform.page_url+page)
        //通过搜索xpath获取地址
        println("Collecting eggs for "+city.chinese+" in page "+page)
//        val eggArray=ArrayList<Egg>()
       for(element in driver.findElementsByXPath(house.platform.egg_xpath)){
           val egg=Egg(house,city,element.getAttribute("href"));
            //eggArray.add(egg);
           println("Collected egg with "+egg.url);
            thread(start = true){
                //hatch egg
                Hatcher(egg).hatch()

            }
       }

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

}
