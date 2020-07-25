package dms.auctionspider.collecting

import dms.auctionspider.City
import dms.auctionspider.Platform
import kotlin.concurrent.thread

class House {
    val platform:Platform;
    val cities=City.values()
    //val egg_Array=ArrayList<Egg>()
    //房子的平台
    constructor(platform: Platform){
        this.platform=platform;
    }
    //开始搜索数据与储存数据
    fun startCooking(){
    val chicken=spawnChicken();
        //s-
        for(chick in chicken){
        thread (start = true){
           chick.spawnEggs();
            //egg_Array.addAll(eggs)
        }
    }
    }
    //根据城市生成小鸡
    fun spawnChicken():ArrayList<Chicken>{
        val chicken=ArrayList<Chicken>()
    for(city in cities){
       val chick= Chicken(house =this,city = city)
        println("Spawning chick for "+city.chinese)
        chicken.add(chick)
    }
    return (chicken)
    }

}