package dms.auctionspider.bean
//addrbean=网址,地址,起拍价,市场价,开拍时间
//title=[ '室内构架' , '面积/平方米','装修情况', '保证金/万元','加价幅度','竞拍时长','产权情况']
//
//finalbean= loupan(null)楼盘  ,dizhi地址,goujia 构架,jianchen(null),mianji面积,zhuanxiu装修,cankao参考价,qipai起拍价,baozhen保证金,jiafu增幅,kaipai+jinpai 开拍时间与周期 chanquan产权情况

data class FinalBean (val dizhi:String,val goujia:String,val mianji:String,val zhuanxiu:String,val cankao:String,val qipai:String,val baozhen:String,val jiafu:String,val kaipai:String,val shijian:String,val chanquan:String){

}