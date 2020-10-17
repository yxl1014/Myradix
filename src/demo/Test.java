package demo;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Radix radix=new Radix();
        radix.add_Node(new RadixNode("ac"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("h"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("abc"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("hi"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("hive"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("have"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("h"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("hvv"));
        System.out.println("--------------------------");
        radix.add_Node(new RadixNode("accd"));
        System.out.println("--------------------------");
        radix.selecttree();
        System.out.println("--------------------------");
        ArrayList<String> dis=radix.selectall();
        System.out.println("all:"+dis.size()+"ge");
        System.out.println("such as:");
        for(String ss:dis){
            System.out.print(" "+ss+" ");
        }
    }
}
