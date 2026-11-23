public class Pilha {
    private int topo;
    private char pilha[];

    // Construtor: Inicializa a pilha com o tamanho informado.
    public Pilha(int tamanho) {
        this.topo = 0;
        this.pilha = new char[tamanho];
    }

    // Getters e Setters
    public int getTopo() {
        return topo;
    }

    public void setTopo(int topo) {
        this.topo = topo;
    }

    public char[] getPilha() {
        return pilha;
    }

    public void setPilha(char[] pilha) {
        this.pilha = pilha;
    }

    // Retorna true se a pilha estiver vazia e false caso contrário.
    public boolean pilhaVazia() {
        return this.getTopo() == 0;
    }

    // Retorna o caractere que está no topo da pilha, sem removê-lo.
    public char buscarTopo() {
        if(this.getTopo() > 0)
            return this.pilha[this.getTopo()-1];
        else
            return '\0';
    }

    // Adiciona um caractere ao topo da pilha, se houver espaço disponível.
    public boolean push(char elemento) {
        if (this.getTopo() < this.pilha.length) {
            this.pilha[this.getTopo()] = elemento;
            this.topo++;
            return true;
        } else 
            return false;
    }

    // Remove e retorna o caractere que está no topo da pilha.
    public char pop() {
        if (!this.pilhaVazia()) {
            char nodoTopo = this.pilha[this.topo-1];
            this.setTopo(this.getTopo()-1);
            return nodoTopo;
        } else 
            return '\0';
    }
}
