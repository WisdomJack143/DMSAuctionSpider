package dms.auctionspider.bak.spider.taobao

import com.google.gson.Gson
import dms.auctionhouse.bean.AddressBean
import dms.auctionhouse.spider.AbstractSpider
import dms.auctionspider.bak.App
import dms.auctionspider.bak.bean.FinalBean
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.chrome.ChromeDriver
import java.util.regex.Pattern

class TaobaoHouseSpider : AbstractSpider {
    var trys=0;
    lateinit var driver: ChromeDriver;
    lateinit var bean_address: AddressBean;

    constructor(driver: ChromeDriver,bean: AddressBean) {
        bean_address = bean
        this.driver=driver;
    }

    override fun start() {

        try {
            connect()
            var bean = getBean();
            App.beanarray.add(bean)
            println("New json" + Gson().toJson(bean));
        }catch(e:Exception){
            println("fail for bean "+bean_address.address)

        }
        }

    private fun connect() {
        this.trys=trys+1;
        try{
         driver.get(bean_address.html);

        }catch (e: TimeoutException){
            print("加载页面太慢，停止加载，继续下一步操作")

                driver.executeScript("window.stop()")
        }
            println("Current spidering " + bean_address.address+"\n" +
                    "With HTML"+bean_address.html)
    }

//    //title=[ '室内构架' , '面积/平方米','装修情况', '保证金/万元','加价幅度','竞拍时长','产权情况']
//
//    data class HouseBean( val address:String,val struct:String ,val square:String, val decorate:String,val price_market:String,val price_base:String,val price_protect:String,val price_add:String,val auction_time:String,val status:String) {

    private fun getBean(): FinalBean {
        var scoll_init = 100;
        while (scoll_init < 12000) {
            driver.executeScript("window.scrollTo(0," + scoll_init + ")");
            Thread.sleep(300)
            scoll_init = scoll_init + 1000
        }
        val element = driver.findElementById("J_NoticeDetail").text//.findElement(By.xpath("/table/tbody"));
        //println("原生字符串")
        // println("element")

        //面积
        var mianji="不详"
        var index_mianji = element.indexOf("面积");
        if(index_mianji>1){
            //  println("面积所在位置"+index_mianji)
            var pattern_mianji = Pattern.compile("[0-9. ]{2,5}");
            var src_mianji = element.substring(index_mianji);
            //  println("截取后的字符串"+src_mianji)
            var matcher_mianji = pattern_mianji.matcher(src_mianji);
            matcher_mianji.find()
            mianji = matcher_mianji.group()
        }
        println("面积为||" + mianji)

        var index_huxing = element.indexOf("户型");
        var huxing = ""
        if(index_huxing >0){

            var src_huxing = element.substring(index_huxing);
            var pattern_huxing = Pattern.compile(".*?室|.*?厅|.*?卫|.*?厨");
            var matcher_huxing = pattern_huxing.matcher(src_huxing)

            var tried = 0;
            while (matcher_huxing.find() && tried < 4) {
                var str = matcher_huxing.group();
                tried = tried + 1
                if (tried > 2 && str.contains("室")) {
                    break;
                }
                huxing = huxing + str;
            }
        }
        if (!huxing.contains("室"))
            huxing="不详"
        println("户型为||" + huxing)
        var baozhengjin = driver.findElementByXPath("//*[@id=\"J_HoverShow\"]/tr[2]/td[1]/span[2]/span").text
        println("保证金为||" + baozhengjin)
        var jiafu = driver.findElementByXPath("//*[@id=\"J_HoverShow\"]/tr[1]/td[2]/span[2]/span").text
        println("加幅||" + jiafu)
        var shijian = driver.findElementByXPath("//*[@id=\"J_HoverShow\"]/tr[2]/td[2]/span[2]").text.substring(1)
        println("时间||" + shijian)
        //装修
        var zhuanxiu = "不详"
        if (element.contains("毛坯房") || element.contains("清水房")) {
            zhuanxiu = "清水"
        } else if (element.contains("精装")) {
            zhuanxiu = "精装"
        } else if (element.contains("简装")) {
            zhuanxiu = "简装"
        }
        println("装修情况为||" + zhuanxiu)


        //产权
        var chanquan = "不详"
        if (element.contains("不动产")) {
            chanquan = "房产证"
        } else if (element.contains("备案号") || element.contains("合同号") || element.contains("网签号")) {
            chanquan = "合同"

        }
        println("产权情况为||" + chanquan)
//finalbean= loupan(null)楼盘  ,dizhi地址,goujia 构架,jianchen(null),mianji面积,zhuanxiu装修,cankao参考价,qipai起拍价,baozhen保证金,jiafu增幅,kaipai+jinpai 开拍时间与周期 chanquan产权情况
      val finalBean =FinalBean(bean_address.address, huxing, mianji, zhuanxiu, bean_address.price_market, bean_address.price_origin, baozhengjin, jiafu, bean_address.time_start, shijian, chanquan,bean_address.html)
    println("Finally we got an final bean"+finalBean.toString())
      //  driver.close();
    return finalBean;
    }
}