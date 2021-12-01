package syntax;

public class PlaceHolder<K,V> {

    private K k;
    private V v;
    PlaceHolder(K k, V v){
        this.k = k;
        this.v = v;
    }
    public K getK(){
        return k;
    }

    public static <K,V> PlaceHolder<K,V> getInstance(K k, V v){

        return new PlaceHolder(k,v);

    }

    public static void main(String[] args){

        PlaceHolder<String, Number> ph = PlaceHolder.<String, Number>getInstance("abc",5);
        new PlaceHolder<String, String>("33","12");

    }

}
