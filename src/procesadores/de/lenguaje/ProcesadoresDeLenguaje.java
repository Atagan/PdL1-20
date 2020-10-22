/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesadores.de.lenguaje;


import java.util.ArrayList;


/**
 *
 * @author Laura
 */
public class ProcesadoresDeLenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MaquinaDeEstados mq = new MaquinaDeEstados();
        mq.setAll();
        mq.iniciar();
        String[] s = new String[]{"z\\", "aabcpbpbqbbmbobcqbp",
            "abmbpbobccobmbpbmbmbcm", "abobqbqbnbbobnbnbobp"};

        for (int i = 0; i < s.length; i++) {
            if (mq.comprobarCadena(s[i])) {
                System.out.println("Cadena " + (i + 1) + ": " + s[i] + " --> valida");
            } else {
                System.out.println("Cadena " + (i + 1) + ": " + s[i] + " --> no valida");
            }
        }

        mq.generarCadenaRecur("", 0);
        ArrayList<String> listaCadenas = mq.getListaCadenas();
        
        System.out.println("\n" + (listaCadenas.size())
                + " Cadenas posibles que cumplen la ER:\n");
        for (int i = 0; i < listaCadenas.size(); i++) {
            System.out.println((i + 1) + "- " + listaCadenas.get(i) + "\n");
        }

    }
}
