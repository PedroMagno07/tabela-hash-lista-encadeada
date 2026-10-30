import java.util.Random;

class Elo {
    int num;
    Elo seguinte;

    Elo(int n){
        num = n;
        seguinte = null;
    }
}


public class TabelaHash {

    public static void main(String[] args) {

        Random rnd = new Random(1090);
        Elo[] vetorzao = new Elo[100];

        int[] tresUlt = new int[3];
        int qtdColisao = 0;


        for (int i = 0; i < 1000; i++) {
            int sorteado = rnd.nextInt(100001);
            int pos = sorteado % 100;

            Elo novo = new Elo(sorteado);

            if (vetorzao[pos] == null) {
                vetorzao[pos] = novo;
            }
            else {
                Elo p = vetorzao[pos];
                while(p.seguinte != null){
                    p = p.seguinte;
                }
                p.seguinte = novo;

                // guarda so os 3 ultimos que cairam em lista
                tresUlt[0] = tresUlt[1];
                tresUlt[1] = tresUlt[2];
                tresUlt[2] = sorteado;
                qtdColisao++;
            }
        }

        int vazias = 0, listas = 0;
        int tamMaior = 0;
        int idxMaior = -1;

        for(int k = 0; k < 100; k++){
            if (vetorzao[k] == null) {
                vazias++;
            } else {
                int cont = 0;
                Elo aux = vetorzao[k];
                while (aux != null) {
                    cont++;
                    aux = aux.seguinte;
                }

                if(cont > 1)  listas++;

                if (cont > tamMaior) {
                    tamMaior = cont;
                    idxMaior = k;
                }
            }
        }



        System.out.println("Maior lista encadeada -> posicao " + idxMaior + " (" + tamMaior + " elementos):");
        Elo q = vetorzao[idxMaior];
        while (q != null) {
            System.out.print(q.num);
            if (q.seguinte != null) System.out.print(" -> ");
            q = q.seguinte;
        }
        System.out.println();

        System.out.println("\nQuantidade de listas encadeadas geradas: " + listas);
        System.out.println("Posicoes vazias: " + vazias);
        System.out.println("Total de colisoes: " + qtdColisao);

        System.out.println("Ultimos 3 valores inseridos em listas encadeadas: "
                + tresUlt[0] + ", " + tresUlt[1] + ", " + tresUlt[2]);
    }
}
