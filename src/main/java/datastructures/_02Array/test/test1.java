package datastructures._02Array.test;

import java.util.ArrayList;

/**
 * @author jhqxx 梦想成为工程师的人
 * @date 2026/7/15 13:01
 * @description:
 */
public class test1 {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        strings.add("f");
        strings.add("g");


        String remove = strings.remove(2);
        for (String string : strings) {
            System.out.println(string);
        }


    }
}
