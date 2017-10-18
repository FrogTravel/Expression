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


    @Override
    public long calculate() {
        long r1 = getLeft().calculate();
        long r2 = getRight().calculate();

        switch (op){
            case less:
                return (r1 < r2) ? 1 : 0;
            case less_eq:
                return (r1 <= r2) ? 1 : 0;
            case greater:
                return (r1 > r2) ? 1 : 0;
            case greater_eq:
                return (r1 >= r2) ? 1 : 0;
            case equal:
                return (r1 == r2) ? 1 : 0;
            case not_eq:
                return (r1 != r2) ? 1 : 0;

        }
        return 0;
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

