# Validador de Expressões

Projeto em Java desenvolvido para a disciplina de Estrutura de Dados I, da Universidade Feevale.

## Objetivo

Validar expressões matemáticas usando uma pilha para conferir se parênteses, colchetes e chaves estão corretamente pareados e aninhados.

## Como executar

Com o JDK instalado, abra um terminal na pasta do projeto e execute:

```bash
javac App.java Pilha.java
java App
```

O programa executa exemplos definidos em `App.java` e informa se cada expressão é válida ou inválida.

## Regras verificadas

- Aceita números, os operadores `+`, `-`, `*` e `/`, e os delimitadores `()`, `[]` e `{}`.
- Ignora espaços entre os caracteres.
- Rejeita expressões vazias, caracteres não permitidos, delimitadores sem correspondência e operadores no início, no final ou em sequência.
