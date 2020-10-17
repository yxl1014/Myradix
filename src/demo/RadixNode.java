package demo;

import java.util.ArrayList;
import java.util.HashMap;

public class RadixNode {

    private String date;
    private HashMap<String,RadixNode> next=new HashMap<>();
    public RadixNode(String date){
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String,RadixNode>getNext() {
        return next;
    }

    public void setNext(HashMap<String,RadixNode> next) {
        this.next = next;
    }
}
