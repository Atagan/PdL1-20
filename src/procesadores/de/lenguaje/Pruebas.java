/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesadores.de.lenguaje;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author laura
 */
public class Pruebas {

    public static void main(String[] args) {
        ArrayList<String> cadenasGeneradas = new ArrayList<>();
        MaquinaDeEstados mq = new MaquinaDeEstados();
        mq.setAll();
        mq.iniciar();
        
        System.out.println(mq.getListaEstados());
        System.out.println(mq.getMatriz().get(1).get('a'));//Retorna todas los caracteres que un determinado estado acepta
        //Sintaxis: mq.getMatriz().get(_estado_).keySet()
        
        
        /*
        Set a =mq.getMatriz().get(4).keySet();
        char[] aux=mq.setToArray(a);
        for (int i = 0; i < aux.length; i++) {
            System.out.print(aux[i]+" ");
        }
        */
    }
}
