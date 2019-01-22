package fil.car;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server
 *
 */
public class App
{
    List<Character> operateur = new ArrayList<>();
    public static void main(String[] args)
    {
        ServerSocket ps = null;
        try
        {
            ps = new ServerSocket(4000);
            while (true)
            {
                Socket as = ps.accept();
                System.out.println("Bonjour, je suis votre calculette personnelle \n " +
                        "Entrez votre premier nombre pour commencer : \n");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(as.getInputStream()));
                DataOutputStream out = new DataOutputStream(as.getOutputStream());
                String msg = in.readLine();
                int c1 =



                //on reçoit les trois réponses
                //on calcule
                String resp = msg.calcule();

                out.writeBytes(resp);
                System.out.println("Response has been sent.");
            }
        } catch (IOException ex)
        {
            System.exit(-1);
        }
    }

    public void verfication(){

    }

    /**
     * Calcule l'opération proposée
     *
     * @param c1 premier chiffre
     * @param ope l'opérateur
     * @param c2 second chiffre
     * @return le résultat du calcul entre les deux chiffres
     */
    public String calcule(int c1, char ope, int c2){
        operateur.add('*');
        operateur.add('/');
        operateur.add('-');
        operateur.add('+');
        int res = 0;
        if (operateur.contains(ope)){
            switch (ope){
            case '*':
            res = c1 * c2;
             break;
            case '+':
                res = c1 + c2;
                break;
            case '-':
                res = c1 - c2;
                break;
            case '/':
                res = c1 / c2;
                break;
            }
            return c1 + ope + c2 + " = " + res;
        }
        else{
            return "Erreur, votre opérateur n'est pas correct. \n " +
                    "Opérateurs disponible : +,/,*,-";
        }
    }
}

