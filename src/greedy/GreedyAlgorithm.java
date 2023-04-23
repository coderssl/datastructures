package greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author:ssl
 * @Description: datastructures
 * @Date: create in 2023/4/23 16:22
 * @Modified By:
 * @VERSON:1.8
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        Set<String> cities = new HashSet<>();
        Map<String, Set<String>> broadcast = new HashMap<>();
        Set<String> select = new HashSet<>();

        HashSet<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        broadcast.put("K1", k1);

        HashSet<String> k2 = new HashSet<>();
        k2.add("北京");
        k2.add("上海");
        k2.add("深圳");
        broadcast.put("K2", k2);

        HashSet<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        broadcast.put("K3", k3);

        HashSet<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");
        broadcast.put("K4", k4);

        HashSet<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        broadcast.put("K5", k5);

        cities.addAll(k1);
        cities.addAll(k2);
        cities.addAll(k3);
        cities.addAll(k4);
        cities.addAll(k5);

        Set<String> temp;
        String maxKey = null;
        while (!cities.isEmpty()) {
            Set<String> max = new HashSet<>();
            for (String key : broadcast.keySet()) {
                temp = broadcast.get(key);
                temp.retainAll(cities);
                if (temp.size() > max.size()) {
                    max = temp;
                    maxKey = key;
                }
            }
            cities.removeAll(max);
            broadcast.remove(maxKey);
            select.add(maxKey);
        }

        for (String s : select) {
            System.out.println(s);
        }
    }
}
