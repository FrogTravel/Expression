package base;

/**
 * Class logical operators
 * and or xor none
 */
public class Logical extends Expression {
    public Logical(Opcode op, Expression result, Expression right) {
        super.setLeft(result);
        super.setRight(right);
        this.op = op;
    }
    enum Opcode {
        and, or, xor, none
    }
    private Opcode op;

    public Opcode getOp() {
        return op;
    }

    public void setOp(Opcode op) {
        this.op = op;
    }

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
