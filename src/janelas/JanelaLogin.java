package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jogadores.Jogador;
import jogadores.JogadoresArquivo;
import jogodavelha.InterfaceJogo;

public class JanelaLogin {
    
    private Jogador jogador1 = new Jogador();
    private Jogador jogador2 = new Jogador();
    private int contador = 0;
    
    public void setJogador1(Jogador jogador) {
        this.jogador1 = jogador;
    } 
    
    public void setJogador2(Jogador jogador) {
        this.jogador2 = jogador;
    } 
    
    public Jogador getJogador1() {
        return this.jogador1;
    } 
    public Jogador getJogador2() {
        return this.jogador2;
    } 
    
    public void escolhaUser(JFrame frame, String caminhoArquivo, int opcaoJogo) {

        frame.setSize(300, 235);
        frame.setLayout(null);

        JLabel label = new JLabel("Escolha uma opção para prosseguir");
        JButton buttonCadastrar = new JButton("Fazer Cadastro");
        JButton buttonLogin = new JButton("Login");
        JButton buttonVoltar = new JButton("Voltar");
        
        label.setBounds(10, 10, 220, 30);
        buttonLogin.setBounds(10, 50, 267, 40);
        buttonCadastrar.setBounds(10, 95, 267, 40);
        buttonVoltar.setBounds(10, 150, 80, 30);
        
        buttonVoltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(label);
                frame.remove(buttonLogin);
                frame.remove(buttonCadastrar);
                frame.remove(buttonVoltar);
                JanelaMenu menu = new JanelaMenu();
                menu.menu(frame);
            }
            
        });
        
        
        buttonLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(label);
                frame.remove(buttonLogin);
                frame.remove(buttonCadastrar);
                frame.remove(buttonVoltar);
                try {
                    login(frame, caminhoArquivo, opcaoJogo);
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        buttonCadastrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(label);
                frame.remove(buttonLogin);
                frame.remove(buttonCadastrar);
                frame.remove(buttonVoltar);
                cadastro(frame, caminhoArquivo, opcaoJogo);
            }
            
        });

        frame.add(label);
        frame.add(buttonCadastrar);
        frame.add(buttonLogin);
        frame.add(buttonVoltar);
        
    }
    
    public void inserirDados(String caminhoArquivo, JFrame frame, JLabel label, JLabel nome, JLabel senha, JTextField textoNome, JTextField textoSenha, JButton buttonEnviar, JButton buttonVoltar) {
        
        frame.setSize(300, 260);
        frame.setLayout(null);
        
        label.setBounds(10, 10, 220, 30);
        nome.setBounds(10, 50, 57, 40);
        textoNome.setBounds(67, 50, 200, 40);
        senha.setBounds(10, 95, 57, 40);
        textoSenha.setBounds(67, 95, 200, 40);
        buttonEnviar.setBounds(120, 150, 80, 30);
        buttonVoltar.setBounds(10, 180, 80, 30);
        
        frame.add(label);
        frame.add(nome);
        frame.add(senha);
        frame.add(textoNome);
        frame.add(textoSenha);
        frame.add(buttonEnviar);
        frame.add(buttonVoltar);
        
        buttonVoltar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                frame.remove(label);
                frame.remove(nome);
                frame.remove(senha);
                frame.remove(buttonVoltar);
                frame.remove(textoNome);
                frame.remove(textoSenha);
                frame.remove(buttonEnviar);
                escolhaUser(frame, caminhoArquivo, 0);
            }
            
        });
        
    }
    
    public void login(JFrame frame, String caminhoArquivo, int opcaoJogo) throws IOException {
        
        JLabel label = new JLabel("Informe seus dados");
        JLabel nome = new JLabel("Nome: ");
        JLabel senha = new JLabel("Senha: ");
        JTextField textoNome = new JTextField(20);
        JTextField textoSenha = new JTextField(20);
        JButton buttonEnviar = new JButton("Enviar");
        JButton buttonVoltar = new JButton("Voltar");

        buttonEnviar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textoNome.getText().equals("") || textoSenha.getText().equals("") )
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos"); 
                try {
                    JogadoresArquivo jogadores = new JogadoresArquivo(caminhoArquivo);
                    ArrayList<Jogador> jogadoresArray = jogadores.getJogadores();
                    if(jogadores.getJogadores().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Ainda não há jogadores cadastrados"); 
                    }
                    else {
                        if(jogadores.nomeJogadorExistente(textoNome.getText()) != -1) {
                            if(opcaoJogo == 1) {
                                if(jogadoresArray.get(jogadores.nomeJogadorExistente(textoNome.getText())).getSenha().equals(textoSenha.getText())) {
                                    contador++;
                                    if(contador == 2) {
                                        JOptionPane.showMessageDialog(frame, "Vamos jogar!"); 
                                        jogador2 = jogadoresArray.get(jogadores.nomeJogadorExistente(textoNome.getText()));
                                        frame.remove(nome);
                                        frame.remove(senha);
                                        frame.remove(textoNome);
                                        frame.remove(textoSenha);
                                        frame.remove(buttonVoltar);
                                        frame.remove(buttonEnviar);
                                        InterfaceJogo jogar = new InterfaceJogo();
                                        jogar.jogarPVP(frame, label, jogadoresArray, caminhoArquivo, jogador1.getNome(), jogador2.getNome());
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(frame, "Jogador 1 entrou com sucesso. Entre com o Jogador 2"); 
                                        jogador1 = jogadoresArray.get(jogadores.nomeJogadorExistente(textoNome.getText()));
                                        frame.remove(label);
                                        frame.remove(nome);
                                        frame.remove(senha);
                                        frame.remove(textoNome);
                                        frame.remove(textoSenha);
                                        frame.remove(buttonVoltar);
                                        frame.remove(buttonEnviar);
                                        escolhaUser(frame, caminhoArquivo, opcaoJogo);
                                    }
                                }
                                else if(textoSenha.getText().equals(""))    
                                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos"); 
                                else
                                    JOptionPane.showMessageDialog(frame, "Dados incorretos!"); 
                            }
                            else {
                                 if(jogadoresArray.get(jogadores.nomeJogadorExistente(textoNome.getText())).getSenha().equals(textoSenha.getText())) {
                                    JOptionPane.showMessageDialog(frame, "Vamos jogar!"); 
                                    jogador1 = jogadoresArray.get(jogadores.nomeJogadorExistente(textoNome.getText()));
                                    frame.remove(nome);
                                    frame.remove(senha);
                                    frame.remove(textoNome);
                                    frame.remove(textoSenha);
                                    frame.remove(buttonVoltar);
                                    frame.remove(buttonEnviar);
                                    InterfaceJogo jogar = new InterfaceJogo();
                                    jogar.jogarPVM(frame, label, jogadoresArray, caminhoArquivo, jogador1.getNome());
                                 }
                                else if(textoSenha.getText().equals(""))
                                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos"); 
                                else
                                    JOptionPane.showMessageDialog(frame, "Dados incorretos!"); 
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        inserirDados(caminhoArquivo, frame, label, nome, senha, textoNome, textoSenha, buttonEnviar, buttonVoltar);
        
    }
    
    public void cadastro(JFrame frame, String caminhoArquivo, int opcaoJogo) {
        
        JLabel label = new JLabel("Informe seus dados");
        JLabel nome = new JLabel("Nome: ");
        JLabel senha = new JLabel("Senha: ");
        JTextField textoNome = new JTextField(20);
        JTextField textoSenha = new JTextField(20);
        JButton buttonEnviar = new JButton("Enviar");
        JButton buttonVoltar = new JButton("Voltar");

        buttonEnviar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textoNome.getText().equals("") || textoSenha.getText().equals("") )
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos"); 
                try {
                    JogadoresArquivo jogadores = new JogadoresArquivo(caminhoArquivo);
                    if(jogadores.getJogadores().isEmpty()) {
                        if(textoSenha.getText().equals(""))    
                        JOptionPane.showMessageDialog(frame, "Preencha todos os campos"); 
                        else {
                            if(opcaoJogo == 1) {
                                    contador++;
                                    if(contador == 2) {
                                        JOptionPane.showMessageDialog(frame, "Vamos jogar!"); 
                                        jogador2 = new Jogador();
                                        jogador2.setNome(textoNome.getText());
                                        jogador2.setSenha(textoSenha.getText());
                                        jogador2.setPontos(0);
                                        jogadores.setJogador(jogador2);
                                        jogadores.limparArquivo(caminhoArquivo);
                                        jogadores.colocarJogadoresArquivo(caminhoArquivo);
                                        frame.remove(nome);
                                        frame.remove(senha);
                                        frame.remove(textoNome);
                                        frame.remove(textoSenha);
                                        frame.remove(buttonVoltar);
                                        frame.remove(buttonEnviar);
                                        InterfaceJogo jogar = new InterfaceJogo();
                                        jogar.jogarPVP(frame, label, jogadores.getJogadores(), caminhoArquivo, jogador1.getNome(), jogador2.getNome());
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(frame, "Jogador 1 entrou com sucesso. Entre com o Jogador 2"); 
                                        jogador1 = new Jogador();
                                        jogador1.setNome(textoNome.getText());
                                        jogador1.setSenha(textoSenha.getText());
                                        jogadores.setJogador(jogador1);
                                        jogadores.limparArquivo(caminhoArquivo);
                                        jogadores.colocarJogadoresArquivo(caminhoArquivo);
                                        frame.remove(label);
                                        frame.remove(nome);
                                        frame.remove(senha);
                                        frame.remove(textoNome);
                                        frame.remove(textoSenha);
                                        frame.remove(buttonVoltar);
                                        frame.remove(buttonEnviar);
                                        escolhaUser(frame, caminhoArquivo, opcaoJogo);
                                }

                            }
                            else{
                                JOptionPane.showMessageDialog(frame, "Vamos jogar!"); 
                                jogador1 = new Jogador();
                                jogador1.setNome(textoNome.getText());
                                jogador1.setSenha(textoSenha.getText());
                                jogador1.setPontos(0);
                                jogadores.setJogador(jogador1);
                                jogadores.limparArquivo(caminhoArquivo);
                                jogadores.colocarJogadoresArquivo(caminhoArquivo);
                                frame.remove(nome);
                                frame.remove(senha);
                                frame.remove(textoNome);
                                frame.remove(textoSenha);
                                frame.remove(buttonVoltar);
                                frame.remove(buttonEnviar);
                                InterfaceJogo jogar = new InterfaceJogo();
                                jogar.jogarPVM(frame, label, jogadores.getJogadores(), caminhoArquivo, jogador1.getNome());
                            }
                            }
                    }
                    else {
                        if(textoSenha.getText().equals(""))    
                            JOptionPane.showMessageDialog(frame, "Preencha todos os campos"); 
                        else if(jogadores.nomeJogadorExistente(textoNome.getText()) != -1)
                            JOptionPane.showMessageDialog(frame, "Nome de usuário já existente"); 
                        else {
                            if(opcaoJogo == 1) {
                                    contador++;
                                    if(contador == 2) {
                                        JOptionPane.showMessageDialog(frame, "Vamos jogar!"); 
                                        jogador2 = new Jogador();
                                        jogador2.setNome(textoNome.getText());
                                        jogador2.setSenha(textoSenha.getText());
                                        jogador2.setPontos(0);
                                        jogadores.setJogador(jogador2);
                                        jogadores.limparArquivo(caminhoArquivo);
                                        jogadores.colocarJogadoresArquivo(caminhoArquivo);
                                        frame.remove(nome);
                                        frame.remove(senha);
                                        frame.remove(textoNome);
                                        frame.remove(textoSenha);
                                        frame.remove(buttonVoltar);
                                        frame.remove(buttonEnviar);
                                        InterfaceJogo jogar = new InterfaceJogo();
                                        jogar.jogarPVP(frame, label, jogadores.getJogadores(), caminhoArquivo, jogador1.getNome(), jogador2.getNome());
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(frame, "Jogador 1 entrou com sucesso. Entre com o Jogador 2"); 
                                        jogador1 = new Jogador();
                                        jogador1.setNome(textoNome.getText());
                                        jogador1.setSenha(textoSenha.getText());
                                        jogadores.setJogador(jogador1);
                                        jogadores.limparArquivo(caminhoArquivo);
                                        jogadores.colocarJogadoresArquivo(caminhoArquivo);
                                        frame.remove(label);
                                        frame.remove(nome);
                                        frame.remove(senha);
                                        frame.remove(textoNome);
                                        frame.remove(textoSenha);
                                        frame.remove(buttonVoltar);
                                        frame.remove(buttonEnviar);
                                        escolhaUser(frame, caminhoArquivo, opcaoJogo);
                                }

                            }
                            else {
                                JOptionPane.showMessageDialog(frame, "Vamos jogar!"); 
                                jogador1 = new Jogador();
                                jogador1.setNome(textoNome.getText());
                                jogador1.setSenha(textoSenha.getText());
                                jogador1.setPontos(0);
                                jogadores.setJogador(jogador1);
                                jogadores.limparArquivo(caminhoArquivo);
                                jogadores.colocarJogadoresArquivo(caminhoArquivo);
                                frame.remove(nome);
                                frame.remove(senha);
                                frame.remove(textoNome);
                                frame.remove(textoSenha);
                                frame.remove(buttonVoltar);
                                frame.remove(buttonEnviar);
                                InterfaceJogo jogar = new InterfaceJogo();
                                jogar.jogarPVM(frame, label, jogadores.getJogadores(), caminhoArquivo, jogador1.getNome());
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JanelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        inserirDados(caminhoArquivo, frame, label, nome, senha, textoNome, textoSenha, buttonEnviar, buttonVoltar);
        
    }
    
}
