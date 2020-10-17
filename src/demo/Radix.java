package demo;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class Radix {
    private HashMap<String,RadixNode> topnodes=new HashMap<>();
    public boolean add_Node(RadixNode node){
        System.out.println("insert: "+node.getDate());
        if (selectall().contains(node.getDate())){
            System.out.println("该数据以存在");
            return false;
        }
        if(topnodes.size()==0){
            System.out.println("次树为空，直接插入！");
            topnodes.put(node.getDate(),node);
            return true;
        }else{
            System.out.println("次树不为空!");
            if(add_node0(node.getDate())){
                return true;
            }else{
                System.out.println("无顶层节点，直接添加");
                topnodes.put(node.getDate(),node);
                return true;
            }
        }
    }
/*    private boolean samename(String name){
        ArrayList<String> names=selectall();
        if(names.size()==0){
            return false;
        }else{
            for(String n:names){
                if(n.equals(name)){
                    return true;
                }
            }
            return false;
        }
    }*/
    public void selecttree(){
        ArrayList<HashMap<String,RadixNode>> dis=new ArrayList<>();
        if(topnodes.size()==0){
            System.out.println("hehe");
        }
        System.out.println("di 1 ceng:");
        for(String s:topnodes.keySet()){
            System.out.print(s+"  ");
            if(topnodes.get(s).getNext().size()!=0)
                dis.add(topnodes.get(s).getNext());
        }
        System.out.println();
        System.out.println();
        selecttree0(dis);
    }
    private void selecttree0(ArrayList<HashMap<String,RadixNode>> com){
        if(com.size()!=0) {
            ArrayList<HashMap<String, RadixNode>> dis = new ArrayList<>();
            System.out.println("next:");
            for (HashMap<String, RadixNode> map : com) {
                for (String s : map.keySet()) {
                    System.out.print(s + "  ");
                    if(map.get(s).getNext().size()!=0)
                        dis.add(map.get(s).getNext());
                }
            }
            System.out.println();
            System.out.println();
            selecttree0(dis);
        }

    }



    public ArrayList<String> selectall(){
        ArrayList<String> dis=new ArrayList<>();
        if(topnodes.size()==0){
            return dis;
        }
        for(String s:topnodes.keySet()){
            dis.add(s);
            ArrayList<String> r=selectall1(s,topnodes.get(s).getNext());
            for(String ss:r){
                dis.add(ss);
            }
        }
        return dis;
    }

    private ArrayList<String> selectall1(String add,HashMap<String,RadixNode> com){
        ArrayList<String> dis=new ArrayList<>();
        if(com.size()==0){
            return dis;
        }else {
            for (String s : com.keySet()) {
                dis.add(add+s);
                ArrayList<String> r=selectall1(add+s,com.get(s).getNext());
                for(String ss:r){
                    dis.add(ss);
                }
            }
        }
        return dis;
    }

    private boolean add_node0(String add){
        for(String s:topnodes.keySet()){
            System.out.println("数据前缀是否相同");
            if(add.startsWith(s)){
                System.out.println("相同");
                System.out.println("进入下一级");
                if(!add_node1(add.substring(s.length()),topnodes.get(s).getNext())){
                    topnodes.get(s).getNext().put(add.substring(s.length()),new RadixNode(add.substring(s.length())));
                    return true;
                }else{
                    return true;
                }
            }
            String[] ss=Compare(s,add);
            if(ss!=null){
                RadixNode radixNode=new RadixNode(ss[0]);
                if(ss[1]!=null&&!ss[1].equals("")) {
                    topnodes.get(s).setDate(ss[1]);
                    radixNode.getNext().put(ss[1], topnodes.get(s));
                }
                if(ss[2]!=null&&!ss[2].equals("")) {
                    radixNode.getNext().put(ss[2], new RadixNode(ss[2]));
                }
                topnodes.remove(s);
                System.out.println("数据前缀不相同，同级添加");
                topnodes.put(ss[0],radixNode);
                return true;
            }
        }
        return false;
    }

    private boolean add_node1(String add,HashMap<String,RadixNode> com){
        System.out.println("该节点无子结点，直接添加");
        if(com.size()==0){
           com.put(add,new RadixNode(add));
            return true;
        }
        System.out.println("子结数据前缀是否相同");
        for(String s:com.keySet()){
            if(add.startsWith(s)){
                System.out.println("相同");
                System.out.println("进入下一级");
                if(!add_node1(add.substring(s.length()),com.get(s).getNext())){
                    com.get(s).getNext().put(add.substring(s.length()),new RadixNode(add.substring(s.length())));
                    return true;
                }else {
                    return true;
                }
            }
            String[] ss=Compare(s,add);
            if(ss!=null){
                RadixNode radixNode=new RadixNode(ss[0]);
                if(ss[1]!=null&&!ss[1].equals("")) {
                    com.get(s).setDate(ss[1]);
                    radixNode.getNext().put(ss[1], com.get(s));
                }
                if(ss[2]!=null&&!ss[2].equals("")) {
                    radixNode.getNext().put(ss[2], new RadixNode(ss[2]));
                }
                com.remove(s);
                System.out.println("子结数据前缀不相同，同级添加");
                com.put(ss[0],radixNode);
                return true;
            }
        }
        return false;
    }

    private String[] Compare(String old,String add){
        String reback[]=new String[3];
        char[] olds=old.toCharArray();
        char[] adds=add.toCharArray();
        int o=olds.length;
        int a=adds.length;
        int len=Math.min(o,a);
        char[] node=new char[len];
        int i;
        if(o<=a){
            for (i = 0; i < o; i++) {
                if(olds[i]==adds[i]){
                    node[i]=olds[i];
                }else{
                    break;
                }
            }
            if(node[0]=='\0'){
                return null;
            } else{
                reback[0]=new String(node).substring(0,i);
                reback[1]=new String(olds).substring(i);
                reback[2]=new String(adds).substring(i);
            }
        }else{
            for (i = 0; i < a; i++) {
                if(olds[i]==adds[i]){
                    node[i]=adds[i];
                }else{
                    break;
                }
            }
            if(node[0]=='\0'){
                return null;
            } else{
                reback[0]=new String(node).substring(0,i);
                reback[1]=new String(olds).substring(i);
                reback[2]=new String(adds).substring(i);
            }
        }
        return reback;
    }
}
