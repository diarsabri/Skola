/**
 * Created by Diar on 2017-02-01.
 */
public interface Stack<T>
{
    /**
     * this method adds an object to the bottom of the list
     */
    void push(T o);

    /**
     * @return returns the removed object
     * this method removes the object at the top
     */
    T pop();

    /**
     * @return returns the object at the top
     * this method only shows which object is at the top, does not remove it
     */
    T top();

    /**
     * @return returns an integer of the size of the stack
     */
    int size();

    /**
     * @return returns true or false depending on if the stack is empty or not
     */
    boolean isEmpty();
}
