package jogodavelha;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jogadores.Jogador;

public class InterfaceJogo{
    
    private int numeroJogadas = 0;
    private JButton[] botoes;
    private JLabel label;
    private JFrame frame;
    private int[] botoesClicados = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    
     public void setBotaoClicado(int numero) {
        if(this.numeroJogadas%2 == 0)
            this.botoesClicados[numero] = 2;
        else
            this.botoesClicados[numero] = 1;
    }
     
    public int[] getBotoesClicados() {
        return this.botoesClicados;
    }
    
    public JButton[] getBotoes() {
        return this.botoes;
    }
    
    public JButton getBotao(int i) {
        return this.botoes[i];
    }
     
    public void setNumeroJogadas(int numero) {
        this.numeroJogadas = numero;
    }
    
    public int getNumeroJogadas() {
        return this.numeroJogadas;
    }

    public void interfaceJogar(JFrame frame, JLabel label) {
        
        frame.setSize(300, 375);
        
        JButton[] buttons = new JButton[9];
        buttons[0] = new JButton();
        buttons[1] = new JButton();
        buttons[2] = new JButton();
        buttons[3] = new JButton();
        buttons[4] = new JButton();
        buttons[5] = new JButton();
        buttons[6] = new JButton();
        buttons[7] = new JButton();
        buttons[8] = new JButton();

        buttons[0].setBounds(10, 50, 89, 89);
        buttons[1].setBounds(99, 50, 89, 89);
        buttons[2].setBounds(188, 50, 89, 89);
        buttons[3].setBounds(10, 139, 89, 89);
        buttons[4].setBounds(99, 139, 89, 89);
        buttons[5].setBounds(188, 139, 89, 89);
        buttons[6].setBounds(10, 228, 89, 89);
        buttons[7].setBounds(99, 228, 89, 89);
        buttons[8].setBounds(188, 228, 89, 89);

        this.botoes = buttons;
        this.label = label;
        this.frame = frame;
        
        label.setText("Escolha uma posição: ");
        
        frame.add(buttons[0]);
        frame.add(buttons[1]);
        frame.add(buttons[2]);
        frame.add(buttons[3]);
        frame.add(buttons[4]);
        frame.add(buttons[5]);
        frame.add(buttons[6]);
        frame.add(buttons[7]);
        frame.add(buttons[8]);
        
    }
    
    public void PVP(String caminhoArquivo, String jogador1, String jogador2) {
        ControladorBotoes controlador = new ControladorBotoes();
        controlador.AdicionarAcao(botoes[0], 1, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[1], 2, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[2], 3, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[3], 4, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[4], 5, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[5], 6, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[6], 7, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[7], 8, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
        controlador.AdicionarAcao(botoes[8], 9, this, frame, caminhoArquivo, jogador1, jogador2, this.getBotoes(), this.label);
    }
    
    public void PVM(String caminhoArquivo, String jogador1) {
        ControladorBotoes controlador = new ControladorBotoes();
        controlador.AdicionarAcao2(botoes[0], 1, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[1], 2, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[2], 3, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[3], 4, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[4], 5, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[5], 6, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[6], 7, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[7], 8, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
        controlador.AdicionarAcao2(botoes[8], 9, this, frame, caminhoArquivo, jogador1, this.getBotoes(), this.label);
    }
    
    public int randomizarJogada(){
        int botao = 0;
        Random random = new Random();
        do {
                botao = random.nextInt(7) + 1;
        }
        while(this.botoesClicados[botao] == 0);
       return botao;
    }

    public void jogarPVP(JFrame frame, JLabel label, ArrayList<Jogador> jogadores, String caminhoArquivo, String jogador1, String jogador2) {
        
        interfaceJogar(frame, label);
        PVP(caminhoArquivo, jogador1, jogador2);
        ControladorBotoes controlador = new ControladorBotoes();
        if(controlador.getNumeroJogador() != 0)
            jogadores.get(jogadores.size() - controlador.getNumeroJogador() - 1).setPontos(jogadores.get(jogadores.size() - controlador.getNumeroJogador() - 1).getPontos());

    }   
    
    public void jogarPVM(JFrame frame, JLabel label, ArrayList<Jogador> jogadores, String caminhoArquivo, String jogador1) {
       
        interfaceJogar(frame, label);
        PVM(caminhoArquivo, jogador1);
        ControladorBotoes controlador = new ControladorBotoes();
        if(controlador.getNumeroJogador() == 1)
            jogadores.get(jogadores.size()).setPontos(jogadores.get(jogadores.size()).getPontos());

    }

}
