public class App {

    // Verifica se o caracter é um operador matemático.
    public static boolean isOperador(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    // Limpando espaços da expressão para facilitar a validação.
    public static String limparEspacos(String string) {
        String expressao = "";
        for(int i = 0; i < string.length(); i++) {
            // Se o caracter atual não for um espaço, adiciona ele na expressão final.
            if (string.charAt(i) != ' ')
                expressao+=string.charAt(i);
        }

        return expressao;
    }

    public static boolean isValida(String string) {
        String expressao = limparEspacos(string);

        // Verifica se o primeiro caracter da string é um operador
        if (isOperador(expressao.charAt(0))) return false;
        // Verifica se o último caracter da string é um operador
        if (isOperador(expressao.charAt(expressao.length() - 1))) return false;


        char anterior = ' ';
        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);
            // Se o atual caracter for um operador e o caracter anterior também, a operação não é válida.
            if (isOperador(c) && isOperador(anterior)) return false;
            anterior = c;
        }

        return true;
    }

    public static void main(String[] args) {
        String[] testes = {
                "5 + 3 * [(2 - 1)]", // VÁLIDA
                "5 + * 3", // INVÁLIDA (dois operadores seguidos)
                "(5 + 3) / / 2", // INVÁLIDA (dois operadores seguidos)
                "+ 5 - 2", // INVÁLIDA (começa com operador)
                "5 + 3 -", // INVÁLIDA (termina com operador)
                "5 + ( * 3)", // INVÁLIDA (operador após abertura)
                "5 + (3 - )", // INVÁLIDA (fechamento após operador)
                "{5 + [3 * (2 / 2)]}" // VÁLIDA
        };

       System.out.println("--- Validador de Expressões ---");
       for (String string : testes) {
           System.out.printf("Expressão: %-25s -> %s\n", string,
                   (isValida(string) ? "VÁLIDA" : "INVÁLIDA"));
       }
    }
}
