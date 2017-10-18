package base;

/**
 * Created by ekaterina on 10/11/17.
 * Holds value of long 
 */
public class MyInteger extends Primary {
    private long value;

    public MyInteger(Integer value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    /**
     * Prints tree
     */
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb){
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(String.valueOf(value) + "\n");
        return sb;
    }

}
