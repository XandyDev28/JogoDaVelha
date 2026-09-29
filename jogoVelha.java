import java.util.Random;
import java.util.Scanner;

public class jogoVelha {
    public static void main(String[] args) {
        
        Scanner ler = new Scanner (System.in);
        Random random = new Random();
        int [] jogoVelha = new int[9];
        
        int player = 1;
        boolean win = false;
        int jogadas = 0;
        

        while (!win) {
            showTab(jogoVelha);
            int posicao;

            if(player == 1){
                System.out.println("Você! Digite a posição desejada entre 1 a 9");
                posicao = ler.nextInt() - 1;

                if (jogoVelha[posicao] != 0) {
                    System.out.println("Posição já esta ocupada!");
                    continue;
                }
            } else {
                System.out.println("Jarvis esta jogando");
                posicao = jogadaComputador(jogoVelha, random);
                System.out.println("Jarvis jogou na posição: " +posicao);
            }

            jogoVelha[posicao] = player;
            jogadas++;

            if (verificarVitoria(jogoVelha, player)) {
                showTab(jogoVelha);
                if (player == 1) {
                    System.out.println("Você venceu!");
                } else {
                    System.out.println("O computador venceu!");
                }
                win = true;
            } else if (jogadas == 9) {
                showTab(jogoVelha);
                System.out.println("Empate!");
                win = true;
            } else {
                player = (player == 1) ? 2 : 1; 
            }
        }
        ler.close();
    }

    public static int jogadaComputador(int[] tabuleiro, Random random) {
        int posicao;
        do {
            posicao = random.nextInt(9);
        } while (tabuleiro[posicao] != 0);

        return posicao;
    }

    public static String simbolo(int valor){
        if (valor == 1) return "X";
        if (valor == 2) return "O";
        return "-";
    }

    public static void showTab(int[] tabuleiro){
        System.out.println(simbolo(tabuleiro[0]) + " | " + simbolo(tabuleiro[1]) + " | " + simbolo(tabuleiro[2]));
        System.out.println("---------");
        System.out.println(simbolo(tabuleiro[3]) + " | " + simbolo(tabuleiro[4]) + " | " + simbolo(tabuleiro[5]));
        System.out.println("---------");
        System.out.println(simbolo(tabuleiro[6]) + " | " + simbolo(tabuleiro[7]) + " | " + simbolo(tabuleiro[8]));
    }

    public static boolean verificarVitoria(int[] t, int jogador) {
        int[][] combinacoesVitoria = {
            {0,1,2}, {3,4,5}, {6,7,8}, 
            {0,3,6}, {1,4,7}, {2,5,8}, 
            {0,4,8}, {2,4,6}     
        };

        for (int[] combinacao : combinacoesVitoria) {
            if (t[combinacao[0]] == jogador &&
                t[combinacao[1]] == jogador &&
                t[combinacao[2]] == jogador) {
                return true;
            }
        }
        return false;
    }

}