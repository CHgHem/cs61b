/**
 * Created by L on 2017/2/27.
 */
public class LinkedListDeque<T> {
    private Node<Integer> sentinel;
    private int size;

    public static class Node<T>{
        public Node prev;
        public T item;
        public Node next;

        public Node(T x, Node prev, Node next){
            item = x;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Constructor Without Parameter.
     */
    public <T> LinkedListDeque(){
        sentinel = new Node(63, sentinel, sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel.next;
        size = 0;
    }

    /**
     * Constructor Parameter.
     */
    public LinkedListDeque(T obj){
        sentinel = new Node(63, sentinel, sentinel);
        sentinel.next = new Node(obj, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size += 1;
    }

    /**
    * Adds an item to the front of the Deque.
    */
    public void addFirst(T obj){
        sentinel.next = new Node(obj, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /**
     * Adds an item to the back of the Deque.
     */
    public void addLast(T obj){
        sentinel.prev = new Node(obj, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Returns the number of items in the Deque.
     */
    public int size(){
        return size;
    }

    /**
     * Prints the items in the Deque from first to last, separated by a space.
     */
    public void printDeque(){
        if(size == 0){
            System.out.print("null");
        }
        Node L = sentinel.next;
        for(int i = 1; i <= size; i++){
            if(i == size){
                System.out.println(L.item + "");
                return;
            }else{
                System.out.print(L.item + " ");
                L = L.next;
            }
        }
    }

    /**
     * Removes and returns the item at the front of the Deque.
     * If no such item exists, returns null.
     */
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T firstItem = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return firstItem;
    }

    /**
     * Removes and returns the item at the back of the Deque.
     * If no such item exists, returns null.
     */
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T lastItem = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return lastItem;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public  T get(int index){
        if(size == 0 || size <= index){
            return null;
        }
        if(index <= size / 2){//find from the font
            Node N = sentinel.next;
            for(int i = 0; i < index; i++){
                N = N.next;
            }
            return (T) N.item;
        }else{//find from the back.
            Node N = sentinel.prev;
            for(int i = size - 1; i > index; i--){
                N = N.prev;
            }
            return (T) N.item;
        }
    }

    /**
     *  Same as get, but uses recursion.
     */
    public T getRecursive(int index){
        if(size == 0 || size <= index){
            return null;
        }
        Node L = sentinel;
        T t = null;
        if (index <= size / 2) {//find from the font
            if (index == 0) {
                return (T) sentinel.next.item;
            } else {
                sentinel = sentinel.next;
                t = getRecursive(index - 1);
            }
        } else {//find from the back.
            if (index == size - 1) {
                return (T) sentinel.prev.item;
            } else {
                sentinel = sentinel.prev;
                t = getRecursive(index + 1);
            }
        }
        sentinel = L;
        return t;
    }


    public static void main(String[] args) {
        LinkedListDeque<String> sL = new LinkedListDeque<>();
        sL.addFirst("you");
        sL.addFirst("love");
        sL.addFirst("I");
        sL.addLast("so");
        sL.addLast("much!");
        System.out.println(sL.isEmpty());
        sL.printDeque();
        System.out.println(sL.size());
        String first = sL.removeFirst();
        System.out.println(first);
        sL.printDeque();
        String last = sL.removeLast();
        System.out.println(last);
        sL.printDeque();
        System.out.println(sL.size());
        String s0 = sL.get(0);
        String s2 = sL.get(2);
        System.out.println(s0);
        System.out.println(s2);
        sL.printDeque();
        System.out.println("++++++++++++");
       /* String ss0 = sL.getRecursive(0);
        System.out.println(ss0);
        sL.printDeque();*/
        System.out.println("++++++++++++");
        String ss1 = sL.getRecursive(1);
        System.out.println(ss1);
        sL.printDeque();

    }





























}
