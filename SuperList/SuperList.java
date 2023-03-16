import java.util.EmptyStackException;
import java.util.List;

public class SuperList<E> {
    private ListNode<E> root, end;
    private int size;

    public SuperList() {
        this.root = null;
        this.end = null;
        this.size = 0;
    }

    class ListNode<E> {
        private E val;
        private ListNode<E> next, prev;

        private ListNode(E val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }

        private void setVal(E val) {this.val = val;}
        private void setNext(ListNode<E> node) {this.next = node;}
        private void setPrev(ListNode<E> node) {this.prev = node;}

        private E getVal() {return val;}
        private ListNode<E> getNext() {return this.next;}
        private ListNode<E> getPrev() {return this.prev;}

        private boolean hasNext() {return this.next != null;}
        private boolean hasPrev() {return this.prev != null;}
    }

    public void add(E val) {
        ListNode node = new ListNode(val);

        if(size == 0) {
            root = node;
            end = node;
        } else {
            node.setPrev(end);
            end.setNext(node);
            end = end.getNext();
        }

        size++;
    }

    public void add(int index, E val) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<E> node = root;
        ListNode<E> newNode = new ListNode<>(val);
        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        if(node.hasPrev()) {
            node.getPrev().setNext(newNode);
            newNode.setPrev(node.getPrev());
        }
        newNode.setNext(node);
        node.setPrev(newNode);

        if(root.hasPrev()) {
            root = root.getPrev();
        }

        size++;
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<E> node = root;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        return node.getVal();
    }

    public void set(int index, E val) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<E> node = root;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        node.setVal(val);
    }

    public void push(E val) {
        add(val);
    }

    void remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<E> node = root;
        for(int i = 0; i < index; i++) {
            node = node.next;
        }

        if(node.hasPrev())
            node.getPrev().setNext(node.getNext());
        if(node.hasNext())
            node.getNext().setPrev(node.getPrev());

        if(index == 0) {
            root = root.getNext();
        }

        size--;
    }

    public E pop(){
        if (size == 0)
          throw new EmptyStackException();
    
        E val = end.getValue();
        if (size == 1) 
          clear();
        else{  
          end = end.getPrevious();
          end.setNext(null);
          size--;
        } 
        return val;
    }

    E poll() {
        ListNode temp = root;
        if(temp == null)
            return null;

        root = root.getNext();
        if(root != null)
            root.setPrev(null);
        size--;

        return (E) temp.getVal();
    }

    public E stackPeek() {
        if(root == null)
            throw new EmptyStackException();
        return root.getVal();
    }

    public E queuePeek() {
        if(root == null)
            return null;
        return root.getVal();
    }

    public boolean contains(E val) {
        ListNode<E> node = root;
        while(node != null) {
            if(node.getVal() == val) {
                return true;
            }
            node = node.next;
        }

        return false;
    }

    public void clear() {
        root=null;
		end=null;
		size=0;
    }

    public boolean isEmpty() {return size == 0;}
    public int size() {return size;}

    public String toString() {
        String output = "[";
        ListNode<E> node = root;

        while(node != null) {
            output += node.getVal();

            if(node.next != null)
                output += ", ";
            node = node.next;
        }
        output += "]";

        return output;
    }
}