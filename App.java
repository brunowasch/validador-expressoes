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

    // Verifica se o caractere é válido.
    public static boolean isCaractereValido(char c) {
        return Character.isDigit(c) || isOperador(c) || isCaractereDeAbertura(c) || isCaractereDeFechamento(c);
    }

    public static boolean isValida(String string) {
        // Cria a pilha de tamanho string.length() para comportar todos os caracteres.
        Pilha pilha = new Pilha(string.length());

        // Verifica se a expressão começa ou termina com operador.
        if (isOperador(string.charAt(0)))
            return false;

        if (isOperador(string.charAt(string.length() - 1)))
            return false;

        // Variável para armazenar o caractere anterior.
        char anterior = ' ';

        // Loop para percorrer todos os caracteres da string.
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == ' ')
                continue;

            // Verifica se o caractere atual é válido.
            if (!isCaractereValido(c))
                return false;

            // Se o caractere atual e o anterior forem operadores, retorna false.
            if (i > 0 && isOperador(c) && isOperador(anterior))
                return false;

            // Se for um caractere de abertura, adiciona ele na pilha.
            if (isCaractereDeAbertura(c)) {
                pilha.push(c);
            }

            // Se for um caractere de fechamento, verifica se ele é válido.
            else if (isCaractereDeFechamento(c)) {

                if (pilha.pilhaVazia())
                    return false;

                char topo = pilha.buscarTopo();

                if (topo == '(' && c == ')' || topo == '[' && c == ']' || topo == '{' && c == '}')
                    pilha.pop();
                else
                    return false;
            }

            // Verifica se existe um operador após uma abertura.
            if (isOperador(c) && isCaractereDeAbertura(anterior))
                return false;

            // Verifica se existe um operador antes de um fechamento.
            if (isCaractereDeFechamento(c) && isOperador(anterior))
                return false;

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
                "{5 + [3 * (2 / 2)]}"// VÁLIDA
        };

        System.out.println("--- Validador de Expressões ---");
        for (String string : testes) {
            System.out.printf("Expressão: %-25s -> %s\n", string,
                    (isValida(string) ? "VÁLIDA" : "INVÁLIDA"));
        }
    }
}
