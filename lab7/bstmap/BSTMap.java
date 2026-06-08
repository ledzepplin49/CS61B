package bstmap;


import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
     private Node root;

     private class Node{
         private K key;
         private V value;
         private Node left,right;
         private int size;

         public Node(K key, V value,int size){
             this.key = key;
             this.value = value;
             this.size = size;
         }

         public K getKey(){return key;}

         public V getValue(){return value;}

         public void setValue(V value){this.value = value;}

         public void setsize(int size){this.size = size;}
     }

    public BSTMap(){}

    /** Removes all of the mappings from this map. */
    public void clear(){root = null;}

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){return getkey(root,key) !=null;};

    //return the specified node with the specified key
    private K getkey(Node node,K key){
        if(node==null) return null;
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        int cmp =  key.compareTo(node.key);
        if        (cmp < 0) return getkey(node.left,key);
        else   if (cmp > 0) return getkey(node.right,key);
        else                return node.key;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){return getvalue(root,key);};

    private V getvalue(Node node,K key){
        if(node==null) return null;
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        int cmp =  key.compareTo(node.key);
        if        (cmp < 0) return getvalue(node.left,key);
        else   if (cmp > 0) return getvalue(node.right,key);
        else                return node.value;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){return size(root);}

    private int size(Node node){
        if(node==null) return 0;
        else return node.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(key,value,root);
    };

    /**set the value into node**/
    private Node put(K key, V value,Node node){
        if (node==null) return new Node(key,value,1);
        int cmp =  key.compareTo(node.key);
        if      (cmp < 0) node.left = put(key,value,node.left);
        else if (cmp > 0) node.right = put(key,value,node.right);
        else    node.setValue(value);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /*implement iterable**/
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
