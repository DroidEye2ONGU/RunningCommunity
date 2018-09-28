package droideye.common.util;

import java.util.LinkedHashMap;

public class AgeMapUtil {
    public static LinkedHashMap<String,String> getAgeMap() {
        LinkedHashMap<String, String> ageMap = new LinkedHashMap<>();
        ageMap.put("10-19岁", "1");
        ageMap.put("20-29岁", "2");
        ageMap.put("30-39岁", "3");
        ageMap.put("40-49岁", "4");
        ageMap.put("50-59岁", "5");
        ageMap.put("60-69岁", "6");
        ageMap.put("70-79岁", "7");
        ageMap.put("不限", "0");

        return ageMap;
    }
}
