public class App {

    // Verifica se o caractere é um operador matemático.
    public static boolean isOperador(char c) {
        return c == '+' || c == '-' || c == '/' || c == '*';
    }

    // Verifica se o caractere é uma abertura.
    public static boolean isCaractereDeAbertura(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    // Verifica se o caractere é um fechamento.
    public static boolean isCaractereDeFechamento(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    // Limpando espaços da expressão para facilitar a validação.
    public static String limparEspacos(String string) {
        String expressao = "";
        for (int i = 0; i < string.length(); i++) {
            // Se o caractere atual não for um espaço, adiciona ele na expressão final.
            if (string.charAt(i) != ' ')
                expressao += string.charAt(i);
        }

        return expressao;
    }

    public static boolean isValida(String string) {
        // Se existir alguma letra na expressão, ela é inválida.
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i)))
                return false;
        }

        String expressao = limparEspacos(string);
        Pilha pilha = new Pilha(expressao.length());

        if (expressao.isEmpty())
            return false;
        // Verifica se o primeiro caractere da string é um operador
        if (isOperador(expressao.charAt(0)))
            return false;
        // Verifica se o último caractere da string é um operador
        if (isOperador(expressao.charAt(expressao.length() - 1)))
            return false;

        // Variável para armazenar o caractere anterior, inicializada com um espaço.
        char anterior = ' ';

        for (int i = 0; i < expressao.length(); i++) {
            char c = expressao.charAt(i);

            // Se o atual caractere for um operador e o caractere anterior também, a operação não é válida.
            if (isOperador(c) && isOperador(anterior))
                return false;

            // Se for um caractere de abertura, empilha ele.
            if (isCaractereDeAbertura(c))
                pilha.push(c);

            // Se for um caractere de fechamento, verifica se o topo da pilha é o correspondente.
            else if (isCaractereDeFechamento(c)) {
                // Se a pilha estiver vazia, não há correspondência para o fechamento.
                if (pilha.pilhaVazia())
                    return false;
                // Buscamos o topo da pilha e verificamos se ele corresponde ao fechamento atual.
                char topo = pilha.buscarTopo();

                // Verifica se os parênteses estão vazios.
                if (isCaractereDeAbertura(anterior))
                    return false;

                if (topo == '(' && c == ')' || topo == '[' && c == ']' || topo == '{' && c == '}')
                    // Se corresponder, desempilha o topo.
                    pilha.pop();
                else
                    return false;
            }
            // Se o caractere atual for um operador e o caractere anterior for um caractere
            // de abertura, a operação não é válida.
            if (isOperador(c) && isCaractereDeAbertura(anterior))
                return false;
            // Se o caractere atual for um caractere de fechamento e o caractere anterior
            // for um operador, a operação não é válida.
            if (isCaractereDeFechamento(c) && isOperador(anterior))
                return false;

            // Atualizamos o caractere 'anterior' para o próximo loop.
            anterior = c;
        }

        return pilha.pilhaVazia();
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
