package dms.auctionspider.storage

import dms.auctionspider.bean.Bean

interface IStorage {
    fun storage(bean: Bean)
}