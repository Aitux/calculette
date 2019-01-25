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
 */
public class App {
    public static int oldRes = 0;
    List<Character> operateur = new ArrayList<>();

    public static void main(String[] args) {

        ServerSocket ps = null;
        BufferedReader in = null;
        DataOutputStream out = null;
        boolean flag = true;
        try {
            ps = new ServerSocket(4000);
            while (true) {
                Socket as = ps.accept();

                in = new BufferedReader(new InputStreamReader(as.getInputStream()));
                out = new DataOutputStream(as.getOutputStream());
                out.writeBytes("Bonjour, je suis votre calculette personnelle, " +
                        "Veuillez m'indiquer le calcul de votre choix en respectant la syntaxe <a +.-.*./ b> les espaces sont importants ! Pour vous déconnecter écrivez . sur une ligne simple\n");
                out.flush();
                while (flag) {
                    String msg = in.readLine();
                    System.out.println(msg);
                    try {
                        Operation p = ParserServer.parse(msg);
                        out.writeBytes("[SUCCESS] " + p.calcul() + "\n");
                        oldRes = p.calcul();
                        System.out.println("oldRes=" + oldRes);

                    } catch (OperatorInvalidException e) {
                        e.printStackTrace();
                        out.writeBytes("Operateur invalide\n");
                        out.flush();
                        flag = true;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        out.writeBytes("Problème aves le format des nombres (impossible de les parser)\n");
                        out.flush();
                        flag = true;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        out.writeBytes("Phrase trop courte (la taille ça compte)\n");
                        out.flush();
                        flag = true;
                    } catch (NullPointerException e) {
                        flag = false;
                        System.out.println("Client Disconnected");
                    }
                }


                //on reçoit les trois réponses
                //on calcule
//                String resp = msg.calcule();

//                out.writeBytes(resp);
                System.out.println("Response has been sent.");
                flag = true;
            }
        } catch (IOException ex) {
            System.exit(-1);
        }
    }

    public void verfication() {

    }

    /**
     * Calcule l'opération proposée
     *
     * @param c1  premier chiffre
     * @param ope l'opérateur
     * @param c2  second chiffre
     * @return le résultat du calcul entre les deux chiffres
     */
    public String calcule(int c1, char ope, int c2) {
        operateur.add('*');
        operateur.add('/');
        operateur.add('-');
        operateur.add('+');
        int res = 0;
        if (operateur.contains(ope)) {
            switch (ope) {
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
        } else {
            return "Erreur, votre opérateur n'est pas correct. \n " +
                    "Opérateurs disponible : +,/,*,-";
        }
    }
}

