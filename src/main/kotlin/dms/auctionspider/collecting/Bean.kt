package dms.auctionspider.collecting

import dms.auctionspider.Platform

//通过数据整理搜集到的Bean，不随平台变化
//地址	户型	面积	装修	参考价	起拍价	保证金	加幅	开拍时间	持续时间	产权	网址
//小区与人工检查丢给storage
data class Bean(
        val check: Boolean,//是否人工重检
        val html:String,//原网址
        val address:String,//地址
        val platform: String,//平台
        val type:String,//户型
        val area:String,//面积
        val decorate:String,//装修
        val price_start:String,//起拍价
        val price_marign:String,//保证金
        val price_increase:String,//增幅
        val time_start:String,//开拍时间
        val time_continue:String) //持续时间
{
}