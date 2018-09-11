import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Diar on 2017-02-16.
 */
public class TreeTest
{
    private Tree<Integer> tree = new Tree<>();
    private Tree<String> tree2 = new Tree<>();

    @Test
    public void insert() throws Exception
    {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        assertEquals(tree.size(),3);

        tree2.insert("hej");
        assertEquals(tree2.size(),1);
    }

    @Test
    public void search() throws Exception
    {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        assertTrue(tree.search(1));
        assertFalse(tree.search(4));

        tree2.insert("hej");
        assertTrue(tree2.search("hej"));
        assertFalse(tree2.search("hejj"));
    }

    @Test
    public void depth() throws Exception
    {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(6);
        assertEquals(tree.depth(),2);

        tree2.insert("hej");
        tree2.insert("hejj");
        assertEquals(tree2.depth(),1);

    }

    @Test
    public void leaves() throws Exception
    {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(6);
        assertEquals(tree.leaves(),2);

        tree2.insert("hej");
        tree2.insert("hejj");
        assertEquals(tree2.leaves(),1);
    }

    @Test
    public void toStringTest() throws Exception
    {
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(6);
        System.out.println(tree.toString());

        tree2.insert("hej");
        tree2.insert("hejj");
        tree2.insert("hejjj");
        System.out.println(tree2.toString());
    }

}