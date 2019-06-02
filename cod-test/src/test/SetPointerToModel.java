import com.tlkj.cod.common.CodCommonJson;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc 测试 Set 一个指针到 model
 *
 * @author sourcod
 * @version 1.0
 * @className SetPointerToModel
 * @date 2019/5/28 6:07 PM
 */
public class SetPointerToModel {

    private Map name;

    private String a;

    private String[] b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String[] getB() {
        return b;
    }

    public void setB(String[] b) {
        this.b = b;
    }

    public Map getName() {
        return name;
    }

    public void setName(Map name) {
        this.name = name;
    }

    public static void main(String[] args) {
        SetPointerToModel setPointerToModel = new SetPointerToModel();
        // map
        Map map = new HashMap();
        map.put("a", "a");
        setPointerToModel.setName(map);
        System.out.println(CodCommonJson.dump(setPointerToModel));
        map.put("a", "b");
        System.out.println(CodCommonJson.dump(setPointerToModel));
        map.put("a", "bdddddd");
        System.out.println(CodCommonJson.dump(setPointerToModel));

        // map a
        //map.put("a", "a");
        setPointerToModel.setA((String) map.get("a"));
        System.out.println(CodCommonJson.dump(setPointerToModel));
        map.put("a", "b");
        System.out.println(CodCommonJson.dump(setPointerToModel));
        map.put("a", "bdddddd");
        System.out.println(CodCommonJson.dump(setPointerToModel));

        // a
        String a = "a";
        setPointerToModel.setA(a);
        System.out.println(CodCommonJson.dump(setPointerToModel));
        a = "b";
        System.out.println(CodCommonJson.dump(setPointerToModel));

        String[] b = new String[1];
        b[0] = "b";
        setPointerToModel.setB(b);
        System.out.println(CodCommonJson.dump(setPointerToModel));
        b[0] = "c";
        System.out.println(CodCommonJson.dump(setPointerToModel));

        Map<String, Integer> map1 = new HashMap<>();
        map1.put("aa", 1);
        map1.put("bb", 2);
        map1.put("cc", 3);
        int aa = map1.get("aa");
        System.out.println(aa);
        map1.put("aa", 4);
        aa = map1.get("aa");
        System.out.println(aa);

        String[] bb = new String[1];
        bb[0] = a;

        char x = 'x';
        int i = 0;
        System.out.println(true ? x : 0);
        System.out.println(false ? i : x);
        System.out.println('x' + 1);

    }
}
