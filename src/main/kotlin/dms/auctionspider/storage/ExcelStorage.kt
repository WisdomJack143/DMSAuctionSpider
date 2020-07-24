package dms.auctionspider.storage

import dms.auctionspider.bean.FinalBean
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableWorkbook
import java.io.File
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList


class ExcelStorage {
    companion object{
        var file= File("./"+ DateFormat.getDateInstance(DateFormat.DEFAULT).format(Date())+".xls");

        fun saveFinalBean(beans:ArrayList<FinalBean>){

            var i=1;
           var book: WritableWorkbook = Workbook.createWorkbook(file )
            var sheet=book.createSheet("数据",0)
          //  var keys={"地址";"户型";"面积";"装修";"参考价";"起拍价";"保证金";"加幅";"开拍时间";"持续时间";"产权"}
            var keys= arrayOf( "地址","户型","面积","装修","参考价","起拍价","保证金","加幅","开拍时间","持续时间","产权")
            for( i in  keys.indices){
                var label=Label(i,0,keys[i] );
                sheet.addCell(label);
            }
            for (bean in beans){
                println("Writing bean "+i+"with "+beans.size+"in total")
                sheet.addCell(Label(0,i,bean.dizhi));
                sheet.addCell(Label(1,i,bean.goujia));
                sheet.addCell(Label(2,i,bean.mianji));
                sheet.addCell(Label(3,i,bean.zhuanxiu));
                sheet.addCell(Label(4,i,bean.cankao));
                sheet.addCell(Label(5,i,bean.qipai));
                sheet.addCell(Label(6,i,bean.baozhen));
                sheet.addCell(Label(7,i,bean.jiafu));
                sheet.addCell(Label(8,i,bean.kaipai));
                sheet.addCell(Label(9,i,bean.shijian));
                sheet.addCell(Label(10,i,bean.chanquan));
                i=i+1;
            }
            book.write()
            book.close()

        }


    }


}