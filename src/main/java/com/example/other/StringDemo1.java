package com.example.other;

import lombok.Data;
import lombok.ToString;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author huangqi
 * @Package com.example.other
 * @Description:
 * @date 2019-07-25 15:23
 */
@Data
@ToString
public class StringDemo1 {

    private String name;

    private Date time;

    private List<String> list;

    static final int MAXIMUM_CAPACITY = 1 << 30;
    public Pattern type = Pattern.compile("<td></td>");


    public static void main(String[] args) throws Exception {

       /* List<String> strings = Files.readAllLines(Paths.get("/Users/huangqi/Desktop/content_type.txt"));
        Map<String, String> map = new HashMap<>();
        Map<String, String> temp = new HashMap<>();
        strings.forEach(s -> {
            if (s.contains("<td class=\"separateColor\">")) {
                String replace = s.replaceAll("<td class=\"separateColor\">", "").replace("</td>", "");
                temp.put("value", replace);
                return;
            }
            if (s.contains("<td>")) {
                String replace = s.replaceAll("<td>", "").replace("</td>", "");
                map.put(replace, temp.get("value"));
                temp.remove("value");
                return;
            }

        });

        map.forEach((k,v)->{

            System.out.println(String.format("map.put(\"%s\", \"%s\");",k.trim(),v.trim()));


        });*/

        List<String> strings = Files.readAllLines(Paths.get("/Users/huangqi/ideaworkspace/other-demo/src/main/java/com/example/other/file"));
        strings.forEach(s -> {
            System.out.println(String.format("paramMap.put(\"%s\",\"\");",s.trim()));

        });

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
