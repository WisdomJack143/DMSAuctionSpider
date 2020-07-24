package dms.auctionspider.storage

import com.google.gson.Gson
import dms.auctionspider.bean.FinalBean
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.util.*

class JsonStorage {
    companion object {
        val file = File("./" + DateFormat.getDateInstance(DateFormat.DEFAULT).format(Date()) + ".json");

        fun saveFinalBean(arraybean: ArrayList<FinalBean>) {
            initFile();
           var writer= FileWriter(file);
            Gson().toJson(arraybean,writer)
            writer.flush()
            writer.close()
        }

        private fun initFile() {
            if (file.exists())
                file.delete()
            file.createNewFile();
        }

    }
}