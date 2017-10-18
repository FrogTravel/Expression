package base;

public class Main {

    public static void main(String[] args) {
        String line = "1+(26-98)/15+777<28";
        Parser parser = new Parser(line);
        Expression expression = parser.parse();
        System.out.println(expression.toString());
    }
}