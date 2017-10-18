package base;

/**
 * Created by ekaterina on 10/11/17.
 * For factors * / none
 */
public class Factor extends Expression {
    enum Opcode {
        mult, dev, none
    }

    private Opcode op;

    public Factor(Opcode op, Expression result, Expression right) {
        this.op = op;
        super.setLeft(result);
        super.setRight(right);
    }

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
