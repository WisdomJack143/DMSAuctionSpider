package dms.auctionspider
//城市代码，中文名，京东识别号码，淘宝识别号码
//识别码就是在搜索栏中标的所在地的代码
//tb: &location_code=
enum class City {
    wh("武侯",0,510107),
    jj("锦江",0,510104),
    jn("金牛",0,510106),
    lqy("龙泉驿",0,510112),
    sl("双流",0,510122),
    ch("成华",0,510108),
    qy("青羊",0,510105),
    pd("啤都",0,510124),
    xd("新都",0,510114),
    wj("温江",0,510115),
    //其他为新区的统称
    xq("其他",0,510185);
    val chinese:String;
    val code_jd:Int;
    val code_tb:Int;
    constructor(chinese:String,jdcode:Int,tbcode:Int){
        this.chinese=chinese;
        code_jd=jdcode;
        code_tb=tbcode;

    }
}