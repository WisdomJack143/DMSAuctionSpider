package dms.auctionspider

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class App {
    companion object{
        fun getDriver():ChromeDriver {
            return ChromeDriver(ChromeOptions().addArguments("--headless"))
        }

    }


constructor(){
    println("Now Running App")
    println("Laying Eggs for Taobao")
    println("Laying Eggs for JD")

    println("Hatching Eggs for Taobao")

    println("Hatching Eggs for JD")

    println("Droping All Eggs")

}

}

fun main(args: Array<String>) {
    App()
}