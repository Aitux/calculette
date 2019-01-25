package fil.car;

import java.util.ArrayList;
import java.util.List;

public class ParserServer {


    public static void main(String[] args) {
        // La seule chose importante est maintenant de vérifier que le string reçu par le parser respecte la syntaxe <a +./.*.- b> les espaces sont importants.
        String s = "3 + 4";
        try {
            Operation p = parse(s);
            System.out.println(p.calcul());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | OperatorInvalidException e) {
            e.printStackTrace();
        }
    }

    public static Operation parse(String str) throws NumberFormatException, OperatorInvalidException,ArrayIndexOutOfBoundsException {
        String[] calcul = str.split(" ");
        if ((calcul.length <= 1))
            throw new ArrayIndexOutOfBoundsException();

        List<String> operateurs = new ArrayList<>();
        operateurs.add("*");
        operateurs.add("/");
        operateurs.add("-");
        operateurs.add("+");
        Operation res = new Operation();
        if(operateurs.contains(calcul[0])){
            int i = App.oldRes;
            res.setLeftOperand(i);
            res.setOperator(calcul[0].charAt(0));
            try {
                res.setRightOperand(Integer.parseInt(calcul[1]));
            }catch (NumberFormatException e){
                throw new NumberFormatException();
            }
        }else {
            try {
                res.setLeftOperand(Integer.parseInt(calcul[0]));
                res.setRightOperand(Integer.parseInt(calcul[2]));
            } catch (NumberFormatException e) {
                throw new NumberFormatException();
            }

            if (operateurs.contains(calcul[1]))
                res.setOperator(calcul[1].charAt(0));
            else
                throw new OperatorInvalidException();
        }
        return res;
    }
}
