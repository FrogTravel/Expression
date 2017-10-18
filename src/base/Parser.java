package base;

/**
 * Created by ekaterina on 10/11/17.
 * Builds tree
 * Parse every single character of the input
 * For each symbol goes as deep as it could
 */
public class Parser {
    private String input;
    private int currentPositon;

    public Parser(String s) {
        input = s;
        currentPositon = 0;
    }

    public Expression parse() {
        return parseLogical();
    }

    /**
     * For logical operations
     */
    private Expression parseLogical() {
        Expression result = parseRelation();

        Logical.Opcode op = ParseLogOperator();
        if (op != Logical.Opcode.none) {
            Expression right = parseLogical();
            result = new Logical(op, result, right);
        }

        return result;
    }

    /**
     * For relation operations
     */
    private Expression parseRelation() {
        Expression result = parseTerm();

        Relation.Opcode op = ParseRelOperator();
        if (op != Relation.Opcode.none){
            Expression right = parseTerm();
            result = new Relation(op, result, right);
        }

        return result;
    }

    /**
     * For relation term
     */
    private Expression parseTerm() {
        Expression result = parseFactor();

        //Self call
        Term.Opcode op = ParseTermOperator();
        if(op != Term.Opcode.none){
            Expression right = parseTerm();//Уходит в бесконечный цикл и не возвращается
            result = new Term(op, result, right);
        }

        return result;
    }


    /**
     * For relation factor
     */
    private Expression parseFactor() {
        Expression result = parsePrimary();

        //Self call
        Factor.Opcode op = ParseFactorOperator();
        if(op != Factor.Opcode.none){
            Expression right = parseFactor();
            result = new Factor(op, result, right);
        }

        return result;
    }


    /**
     * For relation primary
     */
    private Expression parsePrimary() {
        Expression result = null;

        if(input.charAt(currentPositon) == '-' && Character.isDigit(input.charAt(currentPositon+1))
                && (currentPositon==0 || (input.charAt(currentPositon-1) != ')' && !Character.isDigit(input.charAt(currentPositon-1)))) ){
            result = parseNegativeInteger();
        } else if (Character.isDigit(input.charAt(currentPositon))) {
            result = parseInteger();
        } else if (input.charAt(currentPositon) == '(') {
            currentPositon++;
            result = parse();
            currentPositon++;
        } else {
            return null;
        }
        return result;
    }

    private Expression parseNegativeInteger() {
        String line = "-";
        currentPositon++;
        while ((currentPositon < input.length()) && (Character.isDigit(input.charAt(currentPositon)))) {
            line += input.charAt(currentPositon);
            currentPositon++;
        }
        MyInteger integer = new MyInteger(Integer.valueOf(line));
        return integer;
    }

    /**
     * For relation integer
     */
    private Primary parseInteger() {
        String line = "";
        while ((currentPositon < input.length()) && (Character.isDigit(input.charAt(currentPositon)))) {
            line += input.charAt(currentPositon);
            currentPositon++;
        }
        MyInteger integer = new MyInteger(Integer.valueOf(line));
        return integer;
    }

    /**
     * Parse logicals operators
     * Check if the symbols are and xor or and so on...
     */
    private Logical.Opcode ParseLogOperator() {
        try {
            if ((input.charAt(currentPositon) == 'a') && (input.charAt(currentPositon + 1) == 'n') && (input.charAt(currentPositon + 2) == 'd')) {
                currentPositon += 3;
                return Logical.Opcode.and;
            } else if ((input.charAt(currentPositon) == 'o') && (input.charAt(currentPositon + 1) == 'r')) {
                currentPositon += 2;
                return Logical.Opcode.or;
            } else if ((input.charAt(currentPositon) == 'x') && (input.charAt(currentPositon + 1) == 'o') && (input.charAt(currentPositon + 2) == 'r')) {
                currentPositon += 3;
                return Logical.Opcode.xor;
            }
        }catch (StringIndexOutOfBoundsException e){

        }
        return Logical.Opcode.none;
    }

    /**
     * Parse relations operators
     */
    private Relation.Opcode ParseRelOperator() {
        try {
            if (input.charAt(currentPositon) == '<') {
                currentPositon++;
                return Relation.Opcode.less;
            }else if ((input.charAt(currentPositon) == '<') && (input.charAt(currentPositon + 1) == '=')) {
                currentPositon+=2;
                return Relation.Opcode.less_eq;
            }else if (input.charAt(currentPositon) == '>') {
                currentPositon++;
                return Relation.Opcode.greater;
            }else if ((input.charAt(currentPositon) == '>') && (input.charAt(currentPositon + 1) == '=')) {
                currentPositon+=2;
                return Relation.Opcode.greater_eq;
            }else if (input.charAt(currentPositon) == '=') {
                currentPositon++;
                return Relation.Opcode.equal;
            }else if ((input.charAt(currentPositon) == '/') && (input.charAt(currentPositon + 1) == '=')) {
                currentPositon+=2;
                return Relation.Opcode.not_eq;
            }

        }catch (StringIndexOutOfBoundsException e){

        }
        return Relation.Opcode.none;
    }

    /**
     * Parse terms operators
     */
    private Term.Opcode ParseTermOperator() {
        try {
            if (input.charAt(currentPositon) == '+') {
                currentPositon++;
                return Term.Opcode.plus;
            }else if (input.charAt(currentPositon) == '-') {
                currentPositon++;
                return Term.Opcode.minus;
            }
        }catch (StringIndexOutOfBoundsException e){

        }
        return Term.Opcode.none;
    }


    /**
     * Parse factors operators
     */
    private Factor.Opcode ParseFactorOperator() {
        try {
            if (input.charAt(currentPositon) == '*') {
                currentPositon++;
                return Factor.Opcode.mult;
            }else if ((input.charAt(currentPositon) == '/') && (input.charAt(currentPositon + 1) != '=')){
                currentPositon++;
                return Factor.Opcode.dev;
            }
        }catch (StringIndexOutOfBoundsException e){

        }
        return Factor.Opcode.none;
    }

}
