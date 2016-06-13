package sswl.caipai.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class MapConstant {
    public static  Map<String,String> emotion = new HashMap<String,String>();
    /*
    * ,0:保密 1:单身 2:恋爱中 3:已婚 4:同性
    * */
    public static Map<String, String> getEmotion() {
        emotion.put("0","保密");
        emotion.put("1","单身");
        emotion.put("2","恋爱中");
        emotion.put("3","已婚");
        emotion.put("4","同性");
        return emotion;
    }

}
