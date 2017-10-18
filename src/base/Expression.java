package base;

/**
 * Created by ekaterina on 10/11/17.
 * Super class for all Parts of expression
 */
public class Expression {

    private Expression left, right;

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    /**
     * Prints tree
     */
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb){
        if(right!=null) {
            right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }

        if(left!=null) {
            left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;

    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }

}
