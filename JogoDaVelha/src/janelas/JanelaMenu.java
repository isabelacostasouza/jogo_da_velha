package janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JanelaMenu {
    
    public void menu(JFrame frame) {
        
        frame.setSize(300, 250);
        frame.setLayout(null);
        
        JLabel label = new JLabel("Bem vindx ao Jogo da Velha");
        JLabel label2 = new JLabel("Selecione uma das opções abaixo:");
        JButton button = new JButton("Player vs Player");
        JButton button2 = new JButton("Player vs Máquina");
        JButton button3 = new JButton("Visualizar Placar");
        JButton button4 = new JButton("Sair");
        
        label.setBounds(10, 9, 267, 30);
        label2.setBounds(10, 30, 267, 30);
        button.setBounds(10, 65, 267, 30);
        button2.setBounds(10, 100, 267, 30);
        button3.setBounds(10, 135, 267, 30);
        button4.setBounds(10, 170, 267, 30);
        
        frame.add(label);
        frame.add(label2);
        frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\teste.txt";
        
        JanelaLogin login = new JanelaLogin();
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(button);
                frame.remove(button2);
                frame.remove(button3);
                frame.remove(button4);
                frame.remove(label);
                frame.remove(label2);
                login.escolhaUser(frame, caminhoArquivo, 1);
            }
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(button);
                frame.remove(button2);
                frame.remove(button3);
                frame.remove(button4);
                frame.remove(label);
                frame.remove(label2);
                login.escolhaUser(frame, caminhoArquivo, 2);
            }
        });
        
        JanelaPlacar placar = new JanelaPlacar();
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(button);
                frame.remove(button2);
                frame.remove(button4);
                frame.remove(label);
                frame.remove(label2);
                frame.remove(button3);
                try {
                    placar.placar(frame);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
        
}
    

