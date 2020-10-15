/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesadores.de.lenguaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Laura
 */
public class MaquinaDeEstados {

    private Automata automata;
    private Integer estadoActual;

    public MaquinaDeEstados() {
        automata = new Automata();

    }

    public void iniciar() {
        estadoActual = automata.getEstadoInicial();
    }

    public void setAll() {
        automata.cargarAlfabeto('a');
        automata.cargarAlfabeto('b');
        automata.cargarAlfabeto('c');
        automata.cargarAlfabeto('d');
        automata.cargarAlfabeto('e');
        automata.cargarAlfabeto('f');
        automata.cargarAlfabeto('g');
        automata.cargarAlfabeto('h');
        automata.cargarAlfabeto('i');
        automata.cargarAlfabeto('j');
        automata.cargarAlfabeto('k');
        automata.cargarAlfabeto('l');
        automata.cargarAlfabeto('m');
        automata.cargarAlfabeto('n');
        automata.cargarAlfabeto('o');
        automata.cargarAlfabeto('p');
        automata.cargarAlfabeto('q');
        automata.cargarAlfabeto('r');
        automata.cargarAlfabeto('s');
        automata.cargarAlfabeto('t');
        automata.cargarAlfabeto('u');
        automata.cargarAlfabeto('v');
        automata.cargarAlfabeto('x');
        automata.cargarAlfabeto('y');
        automata.cargarAlfabeto('z');
        automata.cargarAlfabeto('0');
        automata.cargarAlfabeto('1');
        automata.cargarAlfabeto('2');
        automata.cargarAlfabeto('3');
        automata.cargarAlfabeto('4');
        automata.cargarAlfabeto('5');
        automata.cargarAlfabeto('6');
        automata.cargarAlfabeto('7');
        automata.cargarAlfabeto('8');
        automata.cargarAlfabeto('9');

        automata.iniciarMatriz(0);
        automata.iniciarMatriz(1);
        automata.iniciarMatriz(2);
        automata.iniciarMatriz(3);
        automata.iniciarMatriz(4);

        automata.cargarEstadoInicial(0);
        automata.cargarEstadoFinal(4);

        automata.cargarMatriz('0', 0, 1);
        automata.cargarMatriz('1', 0, 1);
        automata.cargarMatriz('2', 0, 1);
        automata.cargarMatriz('3', 0, 1);
        automata.cargarMatriz('4', 0, 1);
        automata.cargarMatriz('5', 0, 1);
        automata.cargarMatriz('6', 0, 1);
        automata.cargarMatriz('7', 0, 1);
        automata.cargarMatriz('8', 0, 1);
        automata.cargarMatriz('9', 0, 1);

        automata.cargarMatriz('a', 0, 2);
        automata.cargarMatriz('b', 0, 2);
        automata.cargarMatriz('c', 0, 2);

        automata.cargarMatriz('0', 1, 1);
        automata.cargarMatriz('1', 1, 1);
        automata.cargarMatriz('2', 1, 1);
        automata.cargarMatriz('3', 1, 1);
        automata.cargarMatriz('4', 1, 1);
        automata.cargarMatriz('5', 1, 1);
        automata.cargarMatriz('6', 1, 1);
        automata.cargarMatriz('7', 1, 1);
        automata.cargarMatriz('8', 1, 1);
        automata.cargarMatriz('9', 1, 1);

        automata.cargarMatriz('a', 1, 2);
        automata.cargarMatriz('b', 1, 2);
        automata.cargarMatriz('c', 1, 2);

        automata.cargarMatriz('a', 2, 2);
        automata.cargarMatriz('b', 2, 2);
        automata.cargarMatriz('c', 2, 2);

        automata.cargarMatriz('v', 2, 3);

        automata.cargarMatriz('0', 3, 4);
        automata.cargarMatriz('1', 3, 4);
        automata.cargarMatriz('2', 3, 4);
        automata.cargarMatriz('3', 3, 4);
        automata.cargarMatriz('4', 3, 4);
        automata.cargarMatriz('5', 3, 4);
        automata.cargarMatriz('6', 3, 4);
        automata.cargarMatriz('7', 3, 4);
        automata.cargarMatriz('8', 3, 4);
        automata.cargarMatriz('9', 3, 4);

        automata.cargarMatriz('0', 4, 4);
        automata.cargarMatriz('1', 4, 4);
        automata.cargarMatriz('2', 4, 4);
        automata.cargarMatriz('3', 4, 4);
        automata.cargarMatriz('4', 4, 4);
        automata.cargarMatriz('5', 4, 4);
        automata.cargarMatriz('6', 4, 4);
        automata.cargarMatriz('7', 4, 4);
        automata.cargarMatriz('8', 4, 4);
        automata.cargarMatriz('9', 4, 4);

    }

    public ArrayList<Integer> getListaEstados() {
        return automata.getListaEstados();
    }

    public HashMap<Integer, HashMap<Character, Integer>> getMatriz() {
        return automata.getMatriz();
    }

    public void acepta(Character c) {
        Integer estadoTemp = automata.getSiguienteEstado(estadoActual, c);
        if (estadoTemp != null) {
            estadoActual = estadoTemp;
        } else {
            throw new NullPointerException("Cadena invalida");
        }
    }

    public boolean isFinal() {
        return automata.isFinal(estadoActual);
    }

    public boolean comprobarCadena(String cad) {
        boolean valida = false;
        char[] arrayChars = cad.toCharArray();
        int estado = automata.getEstadoInicial();

        for (int i = 0; i < arrayChars.length; i++) {
            //System.out.println(automata.getAlfabeto().contains(arrayChars[i]));
            if (!(automata.getAlfabeto().contains(arrayChars[i]))) {
                //System.out.println("En la cadena: " + cad + " no vale el caracter: " + arrayChars[i]);
                return false;
            }
            //System.out.println("Estoy evaluando el estado: " + estado);
            if (automata.getMatriz().get(estado).keySet().contains(arrayChars[i])) {
                estado = automata.getMatriz().get(estado).get(arrayChars[i]);
            } else {
                //System.out.println("El caracter: " + arrayChars[i] + " no me gusta en esta cadena: " + cad);
                //System.out.println("La ER no acepta esta cadena: " + cad);
                return false;
            }
        }
        //System.out.println("Acabo la cadena: " + cad + " en ek estado: " + estado);
        valida = automata.isFinal(estado);

        return valida;
    }

    public String generarCadena() {
        String a = "";
        int estado = automata.getEstadoInicial();
        boolean acabada = false;
        char[] posibilidades;
        char siguienteChar;
        while (!acabada) {
            if (automata.isFinal(estado) && (Math.random() > 0.9)) {
                acabada = true;
            } else {
                Set<Character> aux = automata.getMatriz().get(estado).keySet();
                posibilidades = new char[aux.size()];
                posibilidades = setToArray(aux);

                siguienteChar = posibilidades[(int) (Math.random() * posibilidades.length)];
                estado = automata.getMatriz().get(estado).get(siguienteChar);
                a = a.concat(String.valueOf(siguienteChar));
                //System.out.println(a);
            }
        }

        return a;
    }

    public char[] setToArray(Set a) {
        int i = 0;
        char[] array = new char[a.size()];
        for (var x : a) {
            array[i++] = (char) x;
        }
        return array;
    }

   
}
