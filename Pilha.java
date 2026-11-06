public class Pilha {
    private int topo;
    private char pilha[];

    public Pilha(int tamanho) {
        this.topo = 0;
        this.pilha = new char[tamanho];
    }

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

    public boolean pilhaVazia() {
        if (this.getTopo() == 0) 
            return true;
        else
            return false;
    }

    public char buscarTopo() {
        if(this.getTopo() > 0)
            return this.pilha[this.getTopo()-1];
        else
            return '\0';
    }

    public boolean push(char elemento) {
        if (this.getTopo() < this.pilha.length) {
            this.pilha[this.getTopo()] = elemento;
            this.topo++;
            return true;
        }
        else 
            return false;
    }

    public char pop() {
        if (!this.pilhaVazia()) {
            char nodoTopo = this.pilha[this.topo-1];
            this.setTopo(this.getTopo()-1);
            return nodoTopo;
        } else 
            return '\0';
    }
}
