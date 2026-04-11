package deque;

import java.util.Iterator;

public class LinkedListDeque<T>implements Iterable<T>,Deque<T> {
    private Node sentinel;
    private int size;
    public class Node {
        public T item;
        public Node back;
        public Node front;
        public Node(T item) {
            this.item = item;
        }
        public Node(T item, Node Back,Node Front) {
            this.item = item;
            this.back=Back;
            this.front=Front;
        }
        public Node() {
            this.item = null;
        }
    }
    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.back=sentinel;
        sentinel.front=sentinel;
        size = 0;
    };
    public void addFirst(T item){
        Node newnode =new Node(item,sentinel.back,sentinel);
        sentinel.back.front=newnode;
        sentinel.back=newnode;
        size++;
    }
    public void addLast(T item){
        Node newnode=new Node(item,sentinel,sentinel.front);
        sentinel.front.back=newnode;
        sentinel.front=newnode;
        size++;
    }
    public boolean isEmpty(){
        if(sentinel.back==sentinel){
            return true;
        }else {
            return false;
        }
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node temp=sentinel.back;
        while(temp!=sentinel){
            System.out.print(temp.item+" ");
            temp=temp.back;
        }
        System.out.println("\n");
    }
    public T removeFirst(){
        if (sentinel.front==sentinel){
            return null;
        }
        Node temp=sentinel.back;
        sentinel.back.back.front=sentinel;
        sentinel.back.front=null;
        sentinel.back=sentinel.back.back;
        size--;
        return temp.item;
    }
    public T removeLast(){
        if (sentinel.front==sentinel){
            return null;
        }
        Node temp=sentinel.front;
        temp.front.back=sentinel;
        sentinel.front=temp.front;
        size--;
        return temp.item;
    }
    public T get(int index){
        int i=0;
        Node temp=sentinel;
        if(index>size){
            return null;
        }
        while(i<index){
            temp=temp.back;
            i++;
        }
        return temp.item;
    }
    public T getRecursive(int index){
        Node temp=sentinel.back;
        T value;
        if(index==1){
            return sentinel.back.item;
        }else{
            sentinel.back=sentinel.back.back;
            value=getRecursive(index-1);
        }
        sentinel.back=temp;
        return value;
    }
    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current=sentinel.back;
        public LinkedListDequeIterator(){
            current=sentinel.back;
        }
        @Override
        public boolean hasNext(){
            if(current!=sentinel){
                return true;
            }
            return false;
        }
        @Override
        public T next(){
            T item=current.item;
            current=current.back;
            return item;
        }
    }
    @Override
    public boolean equals(Object o){
        if (o == null) { return false; }
        if (this == o) { return true; } // optimization
        if (this.getClass() != o.getClass()) { return false; }
        LinkedListDeque<T> other=(LinkedListDeque<T>) o;
        if(this.size!=other.size()){ return false; }
        for(T item:this){
            if(item!=other.removeFirst()){ return false; }
        }
        return true;
    }


}
