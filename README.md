# 🧠 Simulador de Autômatos Finitos em Java

Este projeto é um **simulador de autômatos finitos determinísticos (AFD)** desenvolvido em Java. A ferramenta permite ler um autômato a partir de um arquivo JSON e testar múltiplas palavras de entrada fornecidas em um arquivo CSV. O programa verifica se cada palavra é aceita ou rejeitada pelo autômato e compara com o resultado esperado.

---

## 📌 Descrição da Ferramenta

A ferramenta recebe 3 arquivos via linha de comando:

- `arquivo_do_automato.aut`: especificação do autômato em formato JSON
- `arquivo_de_testes.in`: lista de palavras e resultados esperados (CSV)
- `arquivo_de_saida.out`: arquivo de saída com os resultados obtidos

Cada linha da saída indica se o resultado foi o esperado e quanto tempo levou para processar.

---

## ⚙️ Funcionamento

### 📄 Exemplo: `automato.aut`

Arquivo JSON com a definição do autômato:

```json
{
  "states": ["q0", "q1", "q2"],
  "alphabet": ["a", "b"],
  "initial_state": "q0",
  "accept_states": ["q2"],
  "transitions": {
    "q0": { "a": "q1", "b": "q0" },
    "q1": { "a": "q1", "b": "q2" },
    "q2": { "a": "q1", "b": "q0" }    
  }
}
