package base;

/**
 * Created by ekaterina on 10/11/17.
 * Relations class < <= > >= = /= none
 */
public class Relation extends Expression {

    enum Opcode {less, less_eq, greater, greater_eq, equal, not_eq, none}

    private Opcode op;

    public Relation(Opcode op, Expression result, Expression right) {
        super.setRight(right);
        super.setLeft(result);
        this.op = op;
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

