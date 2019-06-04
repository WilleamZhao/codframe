import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className pointToModel
 * @date 2019/5/28 4:11 PM
 */
public class pointToModel {

    /**
     * 将 A.B.C 变成如下格式：
     * {"A":{"B":{"C":"XXX"}}}
     */
    public static void main(String args[]){
        String module = "A.B.C.D";
        String[] module1 = new String[]{"A.B.C.E", "A.B.C.D", "A.C", "A.D"};
        String content = "";
        String result;
        String[] split = module.split("\\.");
        Map<String,Object> resultMap = new HashMap<>();

        for (int i = 0; i < split.length; i++) {
            if (i == split.length-1){
                resultMap.put(split[i],content);
            }else{
                resultMap.put(split[i],new HashMap<>());
            }
        }

        if (split.length > 1) {
            for (int i = split.length-2; i >= 0 ; i--) {
                Map<String,Object> tempMap = (HashMap)resultMap.get(split[i]);
                tempMap.put(split[i+1],resultMap.get(split[i+1]));
            }

            Map<String,Object> newMap = new HashMap<>();
            newMap.put(split[0],resultMap.get(split[0]));
            result = new Gson().toJson(newMap);
        } else {
            result = new Gson().toJson(resultMap);
        }

        System.out.println(result);
    }
}
