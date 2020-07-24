//package dms.auctionspider.storage
//
//import dms.auctionspider.bean.FinalBean
//import jxl.Workbook
//import jxl.write.Label
//import jxl.write.WritableWorkbook
//import java.io.File
//import java.text.DateFormat
//import java.util.*
//
//
//class `ExcelStorage。bak` {
//    companion object{
//        var i=1;
//        var file= File("./"+ DateFormat.getDateInstance(DateFormat.DEFAULT).format(Date())+".xls");
//
//        fun saveFinalBean(bean:FinalBean){
//            i=i+1;
//            val d_book = Workbook.getWorkbook(file)
//            val new_file= File("./"+ DateFormat.getDateInstance(DateFormat.DEFAULT).format(Date())+"-"+i+".xls");
//            new_file.createNewFile()
//            var book: WritableWorkbook = Workbook.createWorkbook(new_file,d_book)
//            var sheet=book.createSheet("数据",0)
//            var keys={"地址";"户型";"面积";"装修";"参考价";"起拍价";"保证金";"加幅";"开拍时间";"持续时间";"产权"}
//
//            sheet.addCell(Label(1,i,bean.dizhi));
//            sheet.addCell(Label(2,i,bean.goujia));
//            sheet.addCell(Label(3,i,bean.mianji));
//            sheet.addCell(Label(4,i,bean.zhuanxiu));
//            sheet.addCell(Label(5,i,bean.cankao));
//            sheet.addCell(Label(6,i,bean.qipai));
//            sheet.addCell(Label(7,i,bean.baozhen));
//            sheet.addCell(Label(8,i,bean.jiafu));
//            sheet.addCell(Label(9,i,bean.kaipai));
//            sheet.addCell(Label(10,i,bean.shijian));
//            sheet.addCell(Label(11,i,bean.chanquan));
//
//
//            //finalbean= loupan(null)楼盘  ,dizhi地址,goujia 构架,jianchen(null),mianji面积,zhuanxiu装修,cankao参考价,qipai起拍价,baozhen保证金,jiafu增幅,kaipai+jinpai 开拍时间与周期 chanquan产权情况
//            book.write()
//            book.close()
//            d_book.close()
//            file.delete()
//            file=new_file
//
//        }
//         fun initFile(){
//        if(file.exists())
//            file.delete()
//        file.createNewFile();
//            var book: WritableWorkbook = Workbook.createWorkbook(file)
//            var sheet=book.createSheet("数据",0)
//            var keys= arrayOf( "地址","户型","面积","装修","参考价","起拍价","保证金","加幅","开拍时间","持续时间","产权")
//
//            for( i in  keys.indices){
//                var label=Label(i+1,1,keys[i] );
//                sheet.addCell(label);
//            }
//             book.write()
//             book.close()
//            //finalbean= loupan(null)楼盘  ,dizhi地址,goujia 构架,jianchen(null),mianji面积,zhuanxiu装修,cankao参考价,qipai起拍价,baozhen保证金,jiafu增幅,kaipai+jinpai 开拍时间与周期 chanquan产权情况
//
//        }
//
//    }
//
//
//}