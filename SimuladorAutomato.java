/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simulador;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;

public class SimuladorAutomato {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: java SimuladorAutomato <automato.aut> <testes.in> <saida.out>");
            return;
        }

        String arquivoAutomato = args[0];
        String arquivoTestes = args[1];
        String arquivoSaida = args[2];

        try {
            String conteudoJson = new String(Files.readAllBytes(Paths.get(arquivoAutomato)));
            JSONObject json = new JSONObject(conteudoJson);

            Set<String> estados = jsonArrayParaSet(json.getJSONArray("states"));
            Set<String> alfabeto = jsonArrayParaSet(json.getJSONArray("alphabet"));
            String estadoInicial = json.getString("initial_state");
            Set<String> estadosFinais = jsonArrayParaSet(json.getJSONArray("accept_states"));

            Map<String, Map<String, String>> transicoes = new HashMap<>();
            JSONObject transJson = json.getJSONObject("transitions");
            for (String estado : transJson.keySet()) {
                JSONObject mapa = transJson.getJSONObject(estado);
                Map<String, String> transEstado = new HashMap<>();
                for (String simbolo : mapa.keySet()) {
                    transEstado.put(simbolo, mapa.getString(simbolo));
                }
                transicoes.put(estado, transEstado);
            }

            Automato automato = new Automato(estados, alfabeto, estadoInicial, estadosFinais, transicoes);

            List<String> linhasEntrada = Files.readAllLines(Paths.get(arquivoTestes));
            List<String> saidas = new ArrayList<>();

            for (String linha : linhasEntrada) {
                String[] partes = linha.split(";");
                String palavra = partes[0];
                int esperado = Integer.parseInt(partes[1]);

                long inicio = System.nanoTime();
                boolean resultado = automato.processa(palavra);
                long fim = System.nanoTime();
                long tempoMs = (fim - inicio) / 1_000_000;

                int obtido = resultado ? 1 : 0;
                saidas.add(palavra + ";" + esperado + ";" + obtido + ";" + tempoMs + "ms");
            }

            Files.write(Paths.get(arquivoSaida), saidas);
            System.out.println("Resultado gravado com sucesso em: " + arquivoSaida);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Set<String> jsonArrayParaSet(JSONArray array) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < array.length(); i++) {
            set.add(array.getString(i));
        }
        return set;
    }
}
