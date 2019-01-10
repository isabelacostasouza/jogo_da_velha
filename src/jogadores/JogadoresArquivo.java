package jogadores;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ordenacao.Ordenacao;

public class JogadoresArquivo {
    
    private ArrayList <Jogador> jogadores;
    
    public JogadoresArquivo(String caminhoArquivo) throws IOException {
        this.jogadores = new ArrayList();
        this.ler(caminhoArquivo);
        Ordenacao ordenacao = new Ordenacao();
        ordenacao.ordenacaoSelecao(jogadores);
    }
    
    public void setJogador(Jogador jogador) {
        this.jogadores.add(jogador);
    }
    
    public void limparArquivo(String caminhoArquivo) throws IOException {
        File file = new File(caminhoArquivo);
        file.delete();
        File f = new File(caminhoArquivo);
        f.createNewFile();
    }
    
    public void colocarJogadoresArquivo(String caminhoArquivo) throws IOException {
        for(int i = 0; i < jogadores.size(); i++) {
            escreverArquivo(jogadores.get(i).getNome(), caminhoArquivo);
            escreverArquivo(Integer.toString(jogadores.get(i).getPontos()), caminhoArquivo);
            escreverArquivo(jogadores.get(i).getSenha(), caminhoArquivo);
        }
    }
    
    public void adicionarPontoPorNome(String nome) {
        for(int i = 0; i < jogadores.size(); i++) {
            if(jogadores.get(i).getNome().equals(nome))
                jogadores.get(i).setPontos(jogadores.get(i).getPontos() + 1);
        }
    } 
    
    public void escreverArquivo(String linha, String caminhoArquivo) throws IOException {
        
        FileWriter fw = new FileWriter(caminhoArquivo, true);
            BufferedWriter conexao = new BufferedWriter(fw);
            conexao.write(linha);
            conexao.newLine();
            conexao.close();
    }
    
    public void inicializar(String caminhoArquivo) throws IOException {
        this.ler(caminhoArquivo);
        Ordenacao ordenacao = new Ordenacao();
        ordenacao.ordenacaoSelecao(jogadores);
    }
    
    public ArrayList<Jogador> getJogadores() {
        return this.jogadores;
    }
    
    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    
    public void ler(String caminhoArquivo) throws FileNotFoundException, IOException {
        FileReader f = new FileReader(caminhoArquivo);
        BufferedReader readerf = new BufferedReader(f);
        String linha = readerf.readLine();
        ArrayList<String> linhas = new ArrayList();

        while (linha != null) {
            linhas.add(linha);
            linha = readerf.readLine();
        }    
        int contador = 0;

        for(int i = 0; i < linhas.size(); i++) {
            Jogador e = new Jogador();
            if(contador > 2) 
                contador = 0;
            else if(contador == 1) {
                e.setNome(linhas.get(i-1));
                e.setPontos(Integer.parseInt(linhas.get(i)));
                e.setSenha(linhas.get(i+1));
                this.jogadores.add(e);
            }
            contador++;
        }
        readerf.close();
    }
    
    public int nomeJogadorExistente(String nome) {
        for(int i = 0; i < this.jogadores.size(); i++) {
            if(this.jogadores.get(i).getNome().equals(nome))
                return i;
        }
        return -1;
    }
    
    public int getIndiceJogador(String nome) {
        for(int i = 0; i < this.jogadores.size(); i++) {
            if(this.jogadores.get(i).getNome().equals(nome))
                return i;
        }
        return -1;
    }
    
}
