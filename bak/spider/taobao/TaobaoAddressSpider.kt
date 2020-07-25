package dms.auctionhouse.spider.taobao

import dms.auctionhouse.bean.AddressBean
import dms.auctionhouse.spider.AbstractSpider
import dms.auctionspider.bak.App
import dms.auctionspider.bak.spider.taobao.TaobaoHouseSpider
import dms.auctionspider.bak.storage.ExcelStorage
import org.openqa.selenium.By
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.chrome.ChromeDriver

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
    //青羊
    //val url = "https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.49.309b54b32PVloh&location_code=510105&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
    //双流
    //val url="https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.58.262d54b3qgMNgX&location_code=510122&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
    //啤都
    //val url="https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.59.3cf554b3udvOGC&location_code=510124&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
    //新都
    //val url="https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.55.74cd54b38yRf4x&location_code=510114&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
    //龙泉驿
    //val url="https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.53.3a5b54b3pMIF25&location_code=510112&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
    //温江
    //val url="https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.56.65b254b3FVG928&location_code=510115&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
    //高新其他
    val url="https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.67.632054b36ltEt0&location_code=510185&category=50025969&auction_source=0&city=&province=&sorder=1&st_param=5&auction_start_seg=-1"
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
        val element = driver.findElementByClassName("page-total");

        pages = Integer.valueOf(element.text);
        println("Current got pages with " + pages)

    }

    //获取“Addressbean”
    private fun getAddressBeans():List< AddressBean> {
        var bean_list=ArrayList<AddressBean>();
        var index_page = 1
        //遍历每一页，搜索所有的AddressBean
        while (index_page <= pages) {
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
                var address = div_info.findElement(By.xpath("./../div/p")).text
                    var addbean=AddressBean(html, address, str[0], str[1], str[2]);
                    bean_list.add(addbean);
                    println("bean info with "+addbean)
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