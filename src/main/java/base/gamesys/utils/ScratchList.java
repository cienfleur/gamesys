package base.gamesys.utils;

import java.util.Iterator;

public class ScratchList<T> implements Iterable<T> {

    //implement linked list
    public ScratchNode<T> head=null;
    public int length=0;

    public void addElement(T n) {
        ScratchNode<T> tn=new ScratchNode<>();
        tn.setContent(n);
        tn.next=head;
        head=tn;
        length++;
    }

    public void removeElement(int index) {
        ScratchNode<T> temp = head;
        int i = 0;
        while (i < index && temp!=null) {
            temp = temp.next;
            i++;
        }
        if(temp!=null) {
            temp.next=temp.next.next;
            length--;
        }
    }

    public boolean isValidIndex(int index) {
        ScratchNode<T> temp = head;
        int i = 0;
        while (i < index && temp!=null) {
            temp = temp.next;
            i++;
        }
        if (i < index-1 && temp==null) {
            return false;
        }
        return true;
    }

    public T accessIndex(int index) {
        ScratchNode<T> temp = head;
        int i = 0;
        while (i < index && temp!=null) {
            temp = temp.next;
            i++;
        }
        if(temp!=null) {
            return temp.getContent();
        }
        else return null;
    }

    public T getHead() {
        return head.getContent();
    }

    public void clearList() {
        head=null;
    }


    public int getLength() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ScratchIterator<T>(head);
    }


    public class ScratchIterator<E> implements Iterator<E> {
        private ScratchNode<E> pos;

        public ScratchIterator(ScratchNode<E> cnode) {
            pos = cnode;
        }

        @Override
        public boolean hasNext() {
            return pos != null;
        }

        @Override
        public E next() {
            ScratchNode<E> temp = pos;
            pos = pos.next;
            return temp.getContent();
        }
    }

    public class ScratchNode<F> {
        public ScratchNode<F> next = null;
        private F content;

        public F getContent() {
            return content;
        }

        public void setContent(F c) {
            content = c;
        }
    }
}