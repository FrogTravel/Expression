package base;

/**
 * Created by ekaterina on 10/11/17.
 * For term operators + - none
 */
public class Term extends Expression{
    public Term(Opcode op, Expression left, Expression right) {
        super.setLeft(left);
        super.setRight(right);
        this.op = op;
    }

    enum Opcode {
        plus, minus, none
    }

    private Opcode op;

    public Opcode getOp() {
        return op;
    }

    public void setOp(Opcode op) {
        this.op = op;
    }


    /**
     * Prints tree
     */
    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if(super.getRight()!=null) {
            super.getRight().toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(op.toString()).append("\n");
        if(super.getLeft()!=null) {
            super.getLeft().toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

}