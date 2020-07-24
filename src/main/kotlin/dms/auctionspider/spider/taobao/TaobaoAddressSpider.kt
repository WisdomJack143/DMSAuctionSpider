package dms.auctionhouse.spider.taobao

import dms.auctionhouse.bean.AddressBean
import dms.auctionhouse.spider.AbstractSpider
import dms.auctionspider.App
import dms.auctionspider.spider.taobao.TaobaoHouseSpider
import dms.auctionspider.storage.ExcelStorage
import dms.auctionspider.storage.JsonStorage
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class TaobaoAddressSpider : AbstractSpider  {
   lateinit var driver:ChromeDriver;
   constructor(a:ChromeDriver){
       driver=a;
   }
    override fun start() {
        connect()
         getPageNumber()
        var index=0;
        var addressbeans=getAddressBeans();
        try {
            for (addBean in addressbeans) {
                index = index + 1;
                //todo备忘录输出
//                if(index>20){
//                    println("Test mode, we will only deal 10 data")
//                    break;
//                }

                println("Current searching house bean for" + index)
                println("in total"+addressbeans.size)
                val subspider = TaobaoHouseSpider(driver, addBean)
                println("Spider is now started")
                subspider.start()
            }

        }catch (e: Exception){
            println("Unknown Error Happened");
            e.printStackTrace()
        }finally {
            ExcelStorage.saveFinalBean(App.beanarray);
           // JsonStorage.saveFinalBean(App.beanarray)
        }
    }
//private fun runTest(){
//    println("Running Test")
//val subider=    TaobaoHouseSpider(AddressBean("https://sf-item.taobao.com/sf_item/621469811998.htm?spm=a213w.7398504.paiList.6.151a54b3vuU9bF","","","",""))
//println("Test Start")
//    subider.start()
//}

    //根据预选条件的筛选结果
    val url = "https://sf.taobao.com/item_list.htm?spm=a213w.7398504.miniNav.2.6feb4cc6sa1bhe&circ=&category=50025969&auction_source=0&city=%B3%C9%B6%BC&sorder=1&st_param=5&auction_start_seg=-1"
    var pages = 0;

    //加载筛选界面
    private fun connect() {
        try {
            driver.get(url)
            println("Current Loading By Url " +
                    "\n" +
                    url)
        }catch (e: TimeoutException){
            print("加载页面太慢，停止加载，继续下一步操作")
            driver.executeScript("window.stop()")
        }
    }

    //获取总页数
    private fun getPageNumber() {
        val element = driver.findElementByXPath("//html/body/div[3]/div[4]/span[4]/em");

        pages = Integer.valueOf(element.text);
        println("Current got pages with " + pages)

    }

    //获取“Addressbean”
    private fun getAddressBeans():List< AddressBean> {
        var bean_list=ArrayList<AddressBean>();
        var index_page = 1
        //遍历每一页，搜索所有的AddressBean
        while (index_page < pages) {
            var url_index = url + "&page=" + (index_page);
            try{
                driver.get(url_index);
            }catch (e: TimeoutException){
                print("加载页面太慢，停止加载，继续下一步操作")
                driver.executeScript("window.stop()")
            }
            //搜索该页内的所有Bean
            println("Collecting beans on page" + index_page);
            var index_bean = 0;
            for (div_info in driver.findElementsByClassName("info-section")) {
                try{
                index_bean = index_bean + 1;
                println("Collecting bean " + index_bean);
                var str = div_info.text.replace("变卖价", "").replace("市场价", "").replace("起拍价", "").replace("评估价", "").trim().replace("开拍时间", "").replace("¥", "").split("万")
                //参考价
                var html = div_info.findElement(By.xpath("./..")).getAttribute("href")
                var address = div_info.findElement(By.xpath("//a/div[1]/p")).text
                    bean_list.add(AddressBean(html, address, str[0], str[1], str[2]));
                }catch(e:TimeoutException){
                    println("TimeOut,getting next")

                }


                //println("Find bean with " + bean)

            }
//            for (val str: element_html.text.findall('itemUrl.*?//(.*?)".*?status')){
//            println("Current got AddressBean with "+text)
//            }(val html:String,val address:String,val price_market:String,val price_origin:String, val time_start:String){
            index_page = index_page + 1;
        }
        //driver.close()
        return bean_list
    }


}