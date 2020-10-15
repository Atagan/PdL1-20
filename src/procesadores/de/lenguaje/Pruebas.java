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
public class Pruebas {

    public static void main(String[] args) {
        ArrayList<String> cadenasGeneradas = new ArrayList<>();
        MaquinaDeEstados mq = new MaquinaDeEstados();
        mq.setAll();
        mq.iniciar();
        
        System.out.println(mq.getListaEstados());
        System.out.println(mq.getMatriz().get(1).keySet());//Retorna todas los caracteres que un determinado estado acepta
        //Sintaxis: mq.getMatriz().get(_estado_).keySet()
        System.out.println(mq.getMatriz().get(1).get('a'));
    }
}
