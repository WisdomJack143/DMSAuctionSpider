package dms.auctionspider.collecting

import dms.auctionspider.City

class Egg{
    var city:City
    //储存的URL
    var url:String
    var house:House
    constructor ( house:House,o_city:City, o_url:String){
        city=o_city;
        url=o_url
        this.house=house;

    }
}