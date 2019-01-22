package fil.car;

import java.util.ArrayList;
import java.util.List;

public class ParserServer {


    public static String[] parse(String str) throws NumberFormatException, OperatorInvalidException, ArrayIndexOutOfBoundsException{
        String[] calcul = str.split(" ");

        if((calcul.length <= 1))
            throw new ArrayIndexOutOfBoundsException();

        List<Character> operateurs = new ArrayList<>();
        operateurs.add('*');
        operateurs.add('/');
        operateurs.add('-');
        operateurs.add('+');

        String [] res = new String[calcul.length];
        int i = 0;
        int cpt = 0;
        for (String s : calcul) {
            if(i == 0){
                int operande;
                try{
                    operande = Integer.parseInt(s);
                }catch (NumberFormatException e){
                    throw new NumberFormatException();
                }
                res[cpt++] = operande + "";

            }
            else if(i == 1){
                    if (operateurs.contains(s))
                        res[cpt++] = s ;
                    else
                        throw new OperatorInvalidException();
                }
            i = 1 - i;
        }
        return res;
    }
}
