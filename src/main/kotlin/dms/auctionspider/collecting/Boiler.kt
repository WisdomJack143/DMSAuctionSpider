package dms.auctionspider.collecting

import org.openqa.selenium.WebElement

//烧水器，把egg网页的参数，孵化为bean的参数，然后转化为bean
open class Boiler(element: WebElement){
    //通过boil element得到数值， 数值的规范检测都放在这里
    open fun boil():String{

    }
}