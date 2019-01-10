package ordenacao;

import java.util.ArrayList;

public class Ordenacao {

    public void ordenacaoSelecao(ArrayList dados) {
        
        int menorIndice;
        Comparavel menor;
        
        for(int i = 0; i < dados.size(); i++) {
            menor = (Comparavel)dados.get(i);
            menorIndice = i;
            
            for(int j = i + 1; j < dados.size(); j++) {
                if(menor.maior(dados.get(j))) {
                    menor = (Comparavel)dados.get(j);
                    menorIndice = j;
                }
                
                if(menorIndice != i) {
                    Comparavel temp = (Comparavel)dados.get(i);
                    dados.set(i, dados.get(menorIndice));
                    dados.set(menorIndice, temp);
                }
            }
        }

    }
    
}
