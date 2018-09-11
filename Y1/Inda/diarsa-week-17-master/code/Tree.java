import java.lang.Comparable;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Diar on 2017-02-15.
 */
public class Tree<T extends Comparable<T>>
{
    private Node<T> mainRoot;
    private int size;

    private class Node<T>
    {
        public Node<T> leftChild;           //det vänstra barnet
        public Node<T> rightChild;          //det högra barnet
        public T data;                      //"datan" Noden innehåller

        public Node(T obj)
        {
            this.rightChild = null;
            this.leftChild = null;
            this.data = obj;
        }
    }

    public Tree()           //skapar nytt träd
    {
        mainRoot = null;
        size = 0;
    }

    /**
     * det som händer i metoden här är att vi först kollar om main är null, då skapar vi en ny nod.
     * annars kollar vi så att då main inte är null assignas en tillfällig node som vi sedan går nedför trädet med.
     * @param t det givna objektet som ska sättas in i trädet
     */
    public void insert(T t)
    {
        Node<T> prev;
        Node<T> current = mainRoot;

        if(mainRoot == null) {
            mainRoot = new Node<>(t);
            size++;
            return;
        }

        while (mainRoot != null) {
            prev = current;
            if (t.compareTo(current.data) < 0) {
                current = current.leftChild;
                if (current == null) {
                    prev.leftChild = new Node<>(t);
                    size++;
                    return;
                }
            } else {
                current = current.rightChild;
                if (current == null) {
                    prev.rightChild = new Node<>(t);
                    size++;
                    return;
                }
            }
        }
    }

    /**
     * assignar en tillfällig node till main som vi sedan går nedför trädet med, hittar den objektet returnar den en boolean.
     * @param t det givna objektet som vi ska söka efter i trädet
     * @return true ifall objektet hittas, annars false
     */
    public boolean search(T t)
    {
        Node<T> current = mainRoot;

        while (current != null) {
            if (t.compareTo(current.data) == 0) {
                return true;
            } else if (t.compareTo(current.data) < 0) {
                current = current.leftChild;
            } else if (t.compareTo(current.data) > 0) {
                current = current.rightChild;
            }
        }
        return false;
    }

    public int size()
    {
        return size;
    }

    /**
     *
     * @return returnar den rekursiva depth metoden , och ger den mainroot som node.
     * i den rekursiva delen returneras det högsta värdet av antingen det högra eller vänstra trädet.
     */
    public int depth()
    {
        if (mainRoot == null) {
            return -1;
        } else {
            Node<T> node = mainRoot;
            return depth(node);
        }
    }

    public int depth(Node<T> node)
    {
        int x;
        if (node == null) {
            return -1;
        } else {
            x = 1 + Math.max(depth(node.leftChild),depth(node.rightChild));
            return x;
        }
    }

    /**
     *
     * @return precis som i depth returnerar denna mainroot till leaves metoden som node.
     * denna metod fortsätter längs trädet tills dess att den ser att det inte finns några fler barn
     */
    public int leaves()
    {
        if (mainRoot == null) {
            return -1;
        } else {
            Node<T> node = mainRoot;
            return leaves(node);
        }
    }

    public int leaves(Node<T> node)
    {
        int x;
        if (node == null) {
            return 0;
        }
        if ((node.leftChild == null) && (node.rightChild == null)) {
            return 1;
        } else {
            x = leaves(node.leftChild) + leaves(node.rightChild);
            return x;
        }
    }

    /**
     * metoden lägger till högra och vänstra barn då de hittas i trädet.
     * @return returnerar trädet i form av en sträng.
     */
    public String toString()
    {
        if (mainRoot == null) {
            return "";
        } else {
            Node<T> node = mainRoot;
            return toString(node);
        }
    }

    public String toString(Node<T> node)
    {
        String x = "";
        if (node == null) {
            return "";
        } else {
            x += toString(node.leftChild);
            x += node.data.toString();
            x += toString(node.rightChild);

            return x;
        }
    }
}
