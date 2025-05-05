/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package simulador;

import java.util.*;

public class Automato {
    private Set<String> states;
    private Set<String> alphabet;
    private String initialState;
    private Set<String> acceptStates;
    private Map<String, Map<String, String>> transitions;

    public Automato(Set<String> states, Set<String> alphabet, String initialState,
                    Set<String> acceptStates, Map<String, Map<String, String>> transitions) {
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.acceptStates = acceptStates;
        this.transitions = transitions;
    }

    public boolean processa(String entrada) {
        String estadoAtual = initialState;
        for (char simbolo : entrada.toCharArray()) {
            String s = String.valueOf(simbolo);
            if (!alphabet.contains(s) || !transitions.containsKey(estadoAtual) || 
                !transitions.get(estadoAtual).containsKey(s)) {
                return false;
            }
            estadoAtual = transitions.get(estadoAtual).get(s);
        }
        return acceptStates.contains(estadoAtual);
    }
}

