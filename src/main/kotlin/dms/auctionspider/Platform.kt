package dms.auctionspider

enum class Platform {
    Taobao(0,
            "https://sf.taobao.com/item_list.htm?spm=a213w.7398504.filter.2.534939f53xR3ay&category=50025969&auction_source=0&city=%B3%C9%B6%BC&sorder=1&st_param=-1&auction_start_seg=-1"
            ,"&page="
            ,"&location_code="
            ,".//div[@class=\"page-total\"]"
    ,"//*[@class=\"link-wrap\"]",
    "//*[@id=\"J_DetailTabMain\"]"),
    JD(1,"","","","","","");

    val id:Int;
    val url:String;
    val page_url:String;
    val location_url:String;
    val page_xpath:String;
    val egg_xpath:String;
    val desc_xpath:String;
    //平台ID,四川成都的法拍地址,翻页参数,区域参数
    constructor(id:Int,url:String,page:String,location:String,page_xpath:String,egg_xpath:String,desc_xpath:String){
this.id=id;
        this.url=url;
        this.page_url=page
        this.location_url=location
        this.page_xpath=page_xpath
        this.egg_xpath=egg_xpath;
        this.desc_xpath=desc_xpath;
    }
}