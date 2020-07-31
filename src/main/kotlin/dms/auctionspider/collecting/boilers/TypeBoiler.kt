package dms.auctionspider.collecting.boilers

import dms.auctionspider.collecting.Boiler
import org.openqa.selenium.WebElement
import java.util.regex.Pattern

class TypeBoiler<String>(element: WebElement):Boiler<String>(element) {
    override fun boil(): String {
        val text=element.text;

        var index_huxing = text.indexOf("户型");
        var huxing = ""
        if(index_huxing >0){
            var src_huxing = element.substring(index_huxing);
            var pattern_huxing = Pattern.compile(".*?室|.*?厅|.*?卫|.*?厨");
            var matcher_huxing = pattern_huxing.matcher(src_huxing)

            var tried = 0;
            while (matcher_huxing.find() && tried < 4) {
                var str = matcher_huxing.group();
                tried = tried + 1
                if (tried > 2 && str.contains("室")) {
                    break;
                }
                huxing = huxing + str;
            }
        }
        if (!huxing.contains("室"))
            huxing="不详"
    }
}