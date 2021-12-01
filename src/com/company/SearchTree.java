package com.company;


class Trie {
    private Trie[] children = new Trie[26];
    private boolean end;
    private int count = 0;
    private Trie(){};
    private static Trie root = new Trie();
    public static Trie getInstance(){
        return root;
    }

    public void insert(String s){
        insert(root, s);
    }

    private void insert(Trie t, String s){
        t.count++;
        if(s.isEmpty()){
            t.end = true;
            return;
        }

        if(t.children==null){
            t.children = new Trie[26];
            t.children[s.charAt(0)-'a']=new Trie();
            insert(t.children[s.charAt(0)-'a'], s.substring(1));
        }
        else{
            if(t.children[s.charAt(0)-'a']==null){
                t.children[s.charAt(0)-'a']=new Trie();
            }
            insert(t.children[s.charAt(0)-'a'], s.substring(1));
        }


    }

    public int find(String s) {
        Trie t = root;
        for (int i = 0; i < s.length(); i++) {
            t = t.children[s.charAt(i)-'a'];
            if(t==null){
                return 0;
            }
        }

        return t.count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(char i=0;i<26;i++){
            if(children[i]!=null){
                sb.append((char)('a'+i));
                if(children[i].end)sb.append("<>");
                sb.append("->"+children[i].toString());
                sb.append(',');
            }
        }
        sb.append('\b');
        sb.append("]");
        return sb.toString();
    }
}

public class SearchTree {

    public static void main(String[] args){

        Trie root = Trie.getInstance();
        root.insert("s");
        root.insert("ss");
        root.insert("sss");
        root.insert("ssss");
        root.insert("sssss");

        System.out.println(root);
        int c = root.find("s");

        System.out.println(c);

    }


}
