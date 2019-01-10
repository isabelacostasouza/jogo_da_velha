package jogadores;

import ordenacao.Comparavel;

public class Jogador extends Comparavel{
    
    private String nome;
    private String senha;
    private int pontos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int telefone) {
        this.pontos = telefone;
    }
    
    @Override
    public boolean maior(Object obj) {
        if(obj instanceof Jogador) {
            Jogador contatoComparacao = (Jogador)obj;
            if(this.getPontos() < contatoComparacao.getPontos())
                return true;
        }
        return false;
    }
    
}
