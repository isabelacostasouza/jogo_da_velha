package jogodavelha;

import janelas.JanelaMenu;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jogadores.JogadoresArquivo;

public class ControladorBotoes {
    
    private int numeroJogador;
    
    public void setNumeroJogador(int numeroJogadorVencededor) {
        this.numeroJogador = numeroJogadorVencededor;
    }
    
    public int getNumeroJogador() {
        return this.numeroJogador;
    }
    
    public void AdicionarAcao(JButton button, int numeroBotao, InterfaceJogo interfaces, JFrame frame, String caminhoArquivo, String jogador1, String jogador2,  JButton[] botoes, JLabel label) {
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(interfaces.getNumeroJogadas()%2 == 0)
                        button.setText("X");
                    else
                        button.setText("O");
                    button.setFont(new Font("Arial", Font.PLAIN, 50));
                    button.setEnabled(false);
                    interfaces.setNumeroJogadas(interfaces.getNumeroJogadas() + 1);
                    interfaces.setBotaoClicado(numeroBotao-1);
                    int numeroJogador = 0;
                    if(interfaces.getNumeroJogadas()%2 == 0)
                        numeroJogador = 2;
                    else
                        numeroJogador = 1;
                    if(fimDeJogoEmpate(interfaces.getNumeroJogadas())) {
                        JOptionPane.showMessageDialog(frame, "O jogo empatou!\nClique para voltar");
                        try {
                            JogadoresArquivo jogadores = new JogadoresArquivo(caminhoArquivo);
                            jogadores.limparArquivo(caminhoArquivo);
                            jogadores.colocarJogadoresArquivo(caminhoArquivo);
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorBotoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JanelaMenu menu = new JanelaMenu();
                        for(int i = 0; i < botoes.length; i++)
                            frame.remove(botoes[i]);
                        frame.remove(label);
                        menu.menu(frame);
                    }
                    else if(fimDeJogoVencedor(interfaces.getBotoesClicados(), numeroJogador)) {
                        JOptionPane.showMessageDialog(frame, "O jogador " + numeroJogador + " venceu!\nClique para voltar");  
                        try {
                            JogadoresArquivo jogadores = new JogadoresArquivo(caminhoArquivo);
                            if(numeroJogador == 1) {
                                jogadores.adicionarPontoPorNome(jogador1);
                                jogadores.limparArquivo(caminhoArquivo);
                                jogadores.colocarJogadoresArquivo(caminhoArquivo);
                            }
                            else if(numeroJogador == 2) {
                                jogadores.adicionarPontoPorNome(jogador2);
                                jogadores.limparArquivo(caminhoArquivo);
                                jogadores.colocarJogadoresArquivo(caminhoArquivo);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorBotoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JanelaMenu menu = new JanelaMenu();
                        for(int i = 0; i < botoes.length; i++)
                            frame.remove(botoes[i]);
                        frame.remove(label);
                        menu.menu(frame);
                    }
                    setNumeroJogador(numeroJogador);
                }
        });
    }
    
    public void AdicionarAcao2(JButton button, int numeroBotao, InterfaceJogo interfaces, JFrame frame, String caminhoArquivo, String jogador1, JButton[] botoes, JLabel label) {
        button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(interfaces.getNumeroJogadas()%2 == 0)
                        button.setText("X");
                    else
                        button.setText("O");
                    button.setFont(new Font("Arial", Font.PLAIN, 50));
                    button.setEnabled(false);
                    interfaces.setNumeroJogadas(interfaces.getNumeroJogadas() + 1);
                    interfaces.setBotaoClicado(numeroBotao-1);
                    int numeroJogador = 0;
                    if(interfaces.getNumeroJogadas()%2 == 0)
                        numeroJogador = 2;
                    else
                        numeroJogador = 1;
                    if(fimDeJogoVencedor(interfaces.getBotoesClicados(), numeroJogador)){
                        JOptionPane.showMessageDialog(frame, "O jogador " + numeroJogador + " venceu!\nClique para voltar");
                        try {
                            JogadoresArquivo jogadores = new JogadoresArquivo(caminhoArquivo);
                            if(numeroJogador == 1) {
                                jogadores.adicionarPontoPorNome(jogador1);
                                jogadores.limparArquivo(caminhoArquivo);
                                jogadores.colocarJogadoresArquivo(caminhoArquivo);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorBotoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JanelaMenu menu = new JanelaMenu();
                        for(int i = 0; i < botoes.length; i++)
                            frame.remove(botoes[i]);
                        frame.remove(label);
                        menu.menu(frame);
                        
                    }
                    else if(fimDeJogoEmpate(interfaces.getNumeroJogadas())) {
                        JOptionPane.showMessageDialog(frame, "O jogo empatou!\nClique para voltar");
                        try {
                            JogadoresArquivo jogadores = new JogadoresArquivo(caminhoArquivo);
                            jogadores.limparArquivo(caminhoArquivo);
                            jogadores.colocarJogadoresArquivo(caminhoArquivo);
                        } catch (IOException ex) {
                            Logger.getLogger(ControladorBotoes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JanelaMenu menu = new JanelaMenu();
                        for(int i = 0; i < botoes.length; i++)
                            frame.remove(botoes[i]);
                        frame.remove(label);
                        menu.menu(frame);
                    }
                    else if(interfaces.getNumeroJogadas()%2 != 0)
                        interfaces.getBotao(randomizarJogada(interfaces.getBotoesClicados())).doClick();
                    setNumeroJogador(numeroJogador);
                }
        });
    }
    
    public int randomizarJogada(int[] botoesClicados){
        int botao = -1;
        Random random = new Random();
        do {
            botao = random.nextInt(7) + 1;
        }
        while(botoesClicados[botao] != 0);
        return botao;
    }
    
    public boolean checarLinhas(int[] botoesClicados, int numeroJogador) {
        if(botoesClicados[0] == numeroJogador && botoesClicados[3] == numeroJogador && botoesClicados[6] == numeroJogador)
            return true;
        else if(botoesClicados[1] == numeroJogador && botoesClicados[4] == numeroJogador && botoesClicados[7] == numeroJogador)
            return true;
        else if(botoesClicados[2] == numeroJogador && botoesClicados[5] == numeroJogador && botoesClicados[8] == numeroJogador)
            return true;
        else
            return false;
    }
    
    public boolean checarColunas(int[] botoesClicados, int numeroJogador) {
        if(botoesClicados[0] == numeroJogador && botoesClicados[1] == numeroJogador && botoesClicados[2] == numeroJogador)
            return true;
        else if(botoesClicados[3] == numeroJogador && botoesClicados[4] == numeroJogador && botoesClicados[5] == numeroJogador)
            return true;
        else if(botoesClicados[6] == numeroJogador && botoesClicados[7] == numeroJogador && botoesClicados[8] == numeroJogador)
            return true;
        else
            return false;
    }
    
    public boolean checarDiagonais(int[] botoesClicados, int numeroJogador) {
        if(botoesClicados[0] == numeroJogador && botoesClicados[4] == numeroJogador && botoesClicados[8] == numeroJogador)
            return true;
        else if(botoesClicados[2] == numeroJogador && botoesClicados[4] == numeroJogador && botoesClicados[6] == numeroJogador)
            return true;
        else
            return false;
    }
    
    public boolean fimDeJogoEmpate(int numeroJogadas) {
        if(numeroJogadas == 9)
            return true;
        else
            return false;
    }
    
    public boolean fimDeJogoVencedor(int[] botoesClicados, int numeroJogador) {
        if(checarLinhas(botoesClicados, numeroJogador) == true || checarColunas(botoesClicados, numeroJogador)== true || checarDiagonais(botoesClicados, numeroJogador) == true)
            return true;
        else
            return false;
    }
    
}
