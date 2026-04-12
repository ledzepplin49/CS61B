package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T item;
        private Node back;
        private Node front;

        public Node(T item) {
            this.item = item;
        }

        public Node(T item, Node Back, Node Front) {
            this.item = item;
            this.back = Back;
            this.front = Front;
        }

        public Node() {
            this.item = null;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.back = sentinel;
        sentinel.front = sentinel;
        size = 0;
    }

    ;

    //addfirst
    public void addFirst(T item) {
        Node newnode = new Node(item, sentinel.back, sentinel);
        sentinel.back.front = newnode;
        sentinel.back = newnode;
        size++;
    }

    //addlast
    public void addLast(T item) {
        Node newnode = new Node(item, sentinel, sentinel.front);
        sentinel.front.back = newnode;
        sentinel.front = newnode;
        size++;
    }

    //return size
    public int size() {
        return size;
    }

    //print the deque
    public void printDeque() {
        Node temp = sentinel.back;
        while (temp != sentinel) {
            System.out.print(temp.item + " ");
            temp = temp.back;
        }
        System.out.println("\n");
    }

    //remove and return first
    public T removeFirst() {
        if (sentinel.front == sentinel) {
            return null;
        }
        Node temp = sentinel.back;
        sentinel.back.back.front = sentinel;
        sentinel.back.front = null;
        sentinel.back = sentinel.back.back;
        size--;
        return temp.item;
    }

    //remove and return last
    public T removeLast() {
        if (sentinel.front == sentinel) {
            return null;
        }
        Node temp = sentinel.front;
        temp.front.back = sentinel;
        sentinel.front = temp.front;
        size--;
        return temp.item;
    }

    //return the index th item in the aray
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node temp = sentinel.back;
        for (int i = 0; i < index; i++) {
            temp = temp.back;
        }
        return temp.item;
    }

    //recursivly return index th item
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursivehelper(sentinel.back, index);
    }

    //helper function
    private T getRecursivehelper(Node current, int n) {
        if (n == 0) {
            return current.item;
        }
        return getRecursivehelper(current.back, n - 1);
    }

    //return iterator
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    //implete iterator
    private class LinkedListDequeIterator implements Iterator<T> {
        private Node current = sentinel.back;

        public LinkedListDequeIterator() {
            current = sentinel.back;
        }

        @Override
        public boolean hasNext() {
            if (current != sentinel) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.back;
            return item;
        }
    }

    //equal methods
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        //compare
        for (int i = 0; i < this.size(); i++) {
            T item1 = this.get(i);
            T item2 = other.get(i);
            if (!item1.equals(item2)) {
                return false;
            }// 必须用 .equals()
        }
        return true;
    }

}
