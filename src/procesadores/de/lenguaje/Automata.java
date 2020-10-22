/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesadores.de.lenguaje;

import java.util.*;

/**
 *
 * @author Laura
 */
public class Automata {

    private ArrayList<Character> alfabeto = new ArrayList<Character>();
    private ArrayList<Integer> listaEstados = new ArrayList<Integer>();
    private ArrayList<Integer> listaEstadosIniciales = new ArrayList<Integer>();
    private ArrayList<Integer> listaEstadosFinales = new ArrayList<Integer>();
    private HashMap<Integer, HashMap<Character, Integer>> matriz = new HashMap<Integer, HashMap<Character, Integer>>();

    public ArrayList<Character> getAlfabeto() {
        return alfabeto;
    }

    public Automata() {
    }

    public void cargarAlfabeto(Character a) {
        alfabeto.add(a);
    }

    public void cargarEstado(Integer estado) {
        listaEstados.add(estado);
    }

    public void cargarEstadoInicial(Integer estado) {
        listaEstadosIniciales.add(estado);
    }

    public void cargarEstadoFinal(Integer estado) {
        listaEstadosFinales.add(estado);
    }

    public Integer getEstadoInicial(){
        return listaEstadosIniciales.get(0);
    }
    
    public void iniciarMatriz(Integer estado) {
        matriz.put(estado, new HashMap<Character, Integer>());
    }

    public void cargarMatriz(Character entrada, Integer estadoOrigen,
            Integer estadoDestino) {
        matriz.get(estadoOrigen).put(entrada, estadoDestino);
    }

    public ArrayList<Integer> getListaEstados() {
        return listaEstados;
    }

    public HashMap<Integer, HashMap<Character, Integer>> getMatriz() {
        return matriz;
    }
    
    public Integer getSiguienteEstado(Integer estadoActual, Character entrada){
        return matriz.get(estadoActual).get(entrada);
    }
    
    public boolean isFinal(Integer estado){
        return listaEstadosFinales.contains(estado);
    }
    
}
