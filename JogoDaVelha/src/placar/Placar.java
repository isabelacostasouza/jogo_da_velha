package placar;

import janelas.JanelaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import jogadores.Jogador;

public class Placar {
    
    public void mostrarPlacar(JFrame frame, ArrayList<Jogador> jogadores) {
        
        frame.setSize(250, 450);
        frame.setLayout(null);
        JLabel[] labels = new JLabel[10];
        
        JLabel placar = new JLabel("Placar");
        placar.setBounds(20, 20, 200, 10);
        frame.add(placar);
        
        int aux = 0;
        for(int i = 0; i < 10; i++) {
            labels[i] = new JLabel();
            if(jogadores.size() > i)
                labels[i].setText(jogadores.get(i).getNome() + " - " + jogadores.get(i).getPontos() + " pontos");
            else
                labels[i].setText("");
            frame.add(labels[i]);
            labels[i].setBounds(20, 20+aux, 200, 80+aux);
            aux = aux + 20;
        }
        JButton button = new JButton("Voltar");
        button.setBounds(20, 360, 100, 30);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 10; i++) {
                    frame.remove(labels[i]);
                }
                frame.remove(button);
                frame.remove(placar);
                JanelaMenu menu = new JanelaMenu();
                menu.menu(frame);
            }
            
        });
        frame.add(button);
        
    }
    
}
