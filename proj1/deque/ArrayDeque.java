package deque;

import java.util.Iterator;

public class ArrayDeque <T>implements Iterable<T>,Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque(){
        this.items=(T[]) new Object[10];;
        this.size=0;
        this.nextFirst=4;
        this.nextLast=5;
    }
    //add first
    public void addFirst(T item){
        if(size== items.length){
            resize(2*size());
        }
        items[nextFirst]=item;
        nextFirst=(nextFirst-1+items.length)%items.length;
        size++;
    };

    //resize array
    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];

        // 按逻辑顺序从头到尾遍历现有元素，依次放入新数组的 0, 1, 2... 位置
        for (int i = 0; i < size; i++) {
            // (nextFirst + 1 + i) 就是原来数组中第 i 个元素的真实索引
            newItems[i] = items[(nextFirst + 1 + i + items.length) % items.length];
        }

        items = newItems;
        nextFirst = capacity - 1; // 新数组的头部在末尾
        nextLast = size;          // 新数组的尾部在 size 处
    }
    //add last
    public void addLast(T item){
        if(size==items.length){
            resize(2*size());
        }
        items[nextLast]=item;
        nextLast=(nextLast+1)%items.length;
        size++;
    }
    //return true when empty
    public boolean isEmpty(){
        if(size==0){
            return true;
        }else{
            return false;
        }
    }
    //return size
    public int size(){
        return size;
    }
    //print deque
    public void printDeque(){
        int i=nextFirst-1;
        while(items[i]!=null){
            System.out.print(items[i]+" ");
            i=(i+1)%items.length;
        }
        System.out.println("\n");
    }
    //remove first
    public T removeFirst(){
        if((size<items.length/4)&&(size>=16)){
            resize((items.length)/2);
        }
        if(size==0){
            return null;
        }
        T item=items[(nextFirst+1)%items.length];
        nextFirst=(nextFirst+1)%items.length;
        items[nextFirst]=null;
        size--;
        return item;
    }
    //remove last
    public T removeLast(){
        if((size<items.length/4)&&(size>1)){
            resize((items.length)/2);
        }
        if(size==0){
            return null;
        }
        T item = items[(nextLast - 1 + items.length) % items.length];
        nextLast = (nextLast - 1 + items.length) % items.length;
        items[nextLast]=null;
        size--;
        return item;
    }
    //return indes th item
    public T get(int index){
        if (index < 0 || index >= size) { // 必须拦截不合法的索引
            return null;
        }
        int i=(nextFirst+1+index+ items.length)%items.length;
        return items[i];
    }
    //return iterator
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    public class ArrayDequeIterator implements Iterator<T>{
        private int current;
        public ArrayDequeIterator(){
            this.current=(nextFirst+1)%items.length;
        }
        @Override
        public T next() {
            T item=items[current];
            current=(current+1)%items.length;
            return item;
        }
        @Override
        public boolean hasNext() {
            if(current==nextLast){
                return false;
            }
            return true;
        }
    }
    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || !(o instanceof Deque)) { return false; } // 必须用 instanceof Deque

        Deque<T> other = (Deque<T>) o;

        if (this.size() != other.size()) { return false; }

        for (int i = 0; i < this.size(); i++) {
            T item1 = this.get(i);
            T item2 = other.get(i);
            if (!item1.equals(item2)) { // 必须用 .equals()
                return false;
            }
        }
        return true;
    }

}
