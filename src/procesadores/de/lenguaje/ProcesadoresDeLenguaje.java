/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesadores.de.lenguaje;

import java.util.ArrayList;

/**
 *
 * @author luis
 */
public class ProcesadoresDeLenguaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> cadenasGeneradas = new ArrayList<>();
        MaquinaDeEstados mq = new MaquinaDeEstados();
        mq.setAll();
        mq.iniciar();
        String[] s = new String[]{"z\\", "992404aaabv76118", 
            "62bbabccaabbabv", "762ccbv0587420983433"};

        for (int i = 0; i < s.length; i++) {
            if (mq.comprobarCadena(s[i])) {
                System.out.println("Cadena " + (i + 1) + ": " + s[i] + " --> valida");
            } else {
                System.out.println("Cadena " + (i + 1) + ": " + s[i] + " --> no valida");
            }
        }

        String aux;
        while (cadenasGeneradas.size() < 20) {
            aux = mq.generarCadena();
            //System.out.println(aux);
            mq.iniciar();
            if (!cadenasGeneradas.contains(aux)) {
                //System.out.println("cadena: " + aux + " valida");
                cadenasGeneradas.add(aux);
            }
        }

        System.out.println("\n" + (cadenasGeneradas.size())
                + " Cadenas posibles que cumplen la ER:\n");
        for (int i = 0; i < cadenasGeneradas.size(); i++) {
            System.out.println((i + 1) + "- " + cadenasGeneradas.get(i) + "\n");
        }
    }
}
