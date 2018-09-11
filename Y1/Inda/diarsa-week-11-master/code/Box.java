/**
 * The Box class models a three-dimensional box
 */
public class Box {
    private final int height;
    private final int width;
    private final int depth;

    /**
     * Create a new Box with the specified dimensions (height, width, depth).
     *
     * @param height the height of the box
     * @param width the width of the box
     * @param depth the depth of the box
     */
    public Box(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    /**
     * Get this box's volume
     * @return the box's volume
     */
    public int volume() {
        return height * width * depth;
    }

    /**
     * Defines if two Boxes should be considered equal according to their volume.
     * @param o an object
     * @return true if the given object has equal volume to this Box
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Box)) {
            return false;
        }
        Box other = (Box) o;
        return this.volume() == other.volume();
    }

    //@Override
    public int compareTo(Box a)
    {
        if(this.volume()==a.volume()) {
            return 0;
        }
        else if(this.volume()<a.volume()) {
            return -1;
        }
        else {
            return 1;
        }
    }

    /**
     * Defines the hash code of this Box.
     * For a good explanation, see Effective Java Recipe Item 9
     * @return the hash code of this Box
     */
    @Override
    public int hashCode(){
        int result = 13;
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + depth;
        return result;
    }
}
