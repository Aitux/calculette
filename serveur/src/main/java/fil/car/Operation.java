package fil.car;

public class Operation {
    private int leftOperand;
    private char operator;
    private int rightOperand;

    public Operation() {
    }

    public void setLeftOperand(int leftOperand) {
        this.leftOperand = leftOperand;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }


    public void setRightOperand(int rightOperand) {
        this.rightOperand = rightOperand;
    }

    public int calcul() {
        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '/':
                return leftOperand / rightOperand;
            case '*':
                return leftOperand * rightOperand;
            default:
                return 0;
        }
    }
}
