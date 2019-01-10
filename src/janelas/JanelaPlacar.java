package janelas;
        
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFrame;
import jogadores.JogadoresArquivo;
import placar.Placar;

public class JanelaPlacar {
    
    public void placar(JFrame frame) throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String caminhoArquivo = s+"\\teste.txt";
        JogadoresArquivo jogadoresArquivo = new JogadoresArquivo(caminhoArquivo);

        Placar placar = new Placar();
        
        frame.setSize(500, 500);
        frame.setVisible(true);

        placar.mostrarPlacar(frame, jogadoresArquivo.getJogadores());
    }
    
}
