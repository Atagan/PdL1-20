/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesadores.de.lenguaje;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author Laura
 */
public class MaquinaDeEstados {

    private Automata automata;
    private Integer estadoActual;
    private ArrayList<String> listaCadenas = new ArrayList<String>();

    public MaquinaDeEstados() {
        automata = new Automata();

    }

    public void iniciar() {
        estadoActual = automata.getEstadoInicial();
    }

    public void setAll() {
        try {
            File inputFile = new File("src/documentos/er42.jff");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList estados = doc.getElementsByTagName("state");
            NodeList transiciones = doc.getElementsByTagName("transition");

            for (int i = 0; i < estados.getLength(); i++) {
                Node nNode = estados.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nNode;
                    automata.iniciarMatriz(Integer.valueOf(elemento.getAttribute("id")));
                    /*
                    if (elemento.getElementsByTagName("initial") != null) {
                        automata.cargarEstadoInicial(Integer.valueOf(elemento.getAttribute("id")));
                        System.out.println("estado inicial: " + elemento.getAttribute("id"));
                    }
                    if (elemento.getElementsByTagName("final") != null) {
                        automata.cargarEstadoFinal(Integer.valueOf(elemento.getAttribute("id")));
                        System.out.println("estad final: " + elemento.getAttribute("id"));
                        
                    }
                     */
                }
            }
            for (int i = 0; i < transiciones.getLength(); i++) {
                int estadoBase = 0;
                int estadoFin = 0;
                char charAux = ' ';
                Node nNode = transiciones.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nNode;
                    estadoBase = Integer.valueOf(elemento.getElementsByTagName("from").item(0).getTextContent());
                    estadoFin = Integer.valueOf(elemento.getElementsByTagName("to").item(0).getTextContent());
                    //System.out.println(elemento.getElementsByTagName("read").item(0).getTextContent());
                    charAux = elemento.getElementsByTagName("read").item(0).getTextContent().charAt(0);
                    //System.out.println("para ir de estado "+estadoBase+" a "+estadoFin+" necesito: "+charAux);
                    automata.cargarMatriz(charAux, estadoBase, estadoFin);
                    automata.cargarAlfabeto(charAux);
                }
            }
            automata.cargarEstadoFinal(2);
            automata.cargarEstadoInicial(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        return automata.isFinal(estado);
    }

    public String generarCadenaIterativo() {
        String a = "";
        int estado = automata.getEstadoInicial();
        boolean acabada = false;
        char[] posibilidades;
        char siguienteChar;
        while (!acabada) {
            if (automata.isFinal(estado) && a.length() >= 20) {
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

    public boolean generarCadenaRecur(String cadenaIn, int estadoActual) {
        String cadenaOut;
        if (cadenaIn.length() <= 20&&listaCadenas.size()<=19) {
            if (automata.isFinal(estadoActual)) {
                //System.out.println("Meto la cadena: " + cadenaIn);
                if (!listaCadenas.contains(cadenaIn)) {
                    listaCadenas.add(cadenaIn);
                }
                return true;
            }
        } else {
            if (cadenaIn.length() >= 19) {
                return false;
            }
        }
        if (listaCadenas.size() <= 20) {
            for (Character siguienteChar : automata.getAlfabeto()) {
                //System.out.println("Con la cadena: " + cadenaIn + " en el estado: " + estadoActual);
                //System.out.println(siguienteChar);
                if (automata.getMatriz().get(estadoActual).get(siguienteChar) != null) {
                    int estadoNuevo = automata.getMatriz().get(estadoActual).get(siguienteChar);
                    cadenaOut = cadenaIn.concat(String.valueOf(siguienteChar));
                    generarCadenaRecur(cadenaOut, estadoNuevo);
                } else {
                    //System.out.println("Pero no he podido meterla");
                }
            }
        }
        return false;
    }

    public ArrayList<String> getListaCadenas() {
        return listaCadenas;
    }

    public char[] setToArray(Set a) {
        int i = 0;
        char[] array = new char[a.size()];
        for (var x : a) {
            array[i++] = (char) x;
        }
        return array;
    }

    public int getEstadoInicial() {
        return automata.getEstadoInicial();
    }

}
