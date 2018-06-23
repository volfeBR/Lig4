import java.util.*;
public class main
{
    public static Random r         = new Random();
    public static String playerName= "";            //Nome do jogador.
    public static int linha[]      = new int[7];    //Array para preencher linhas corretamente.
    public static boolean J1       = false;         //Seta o jogador atual.
    public static boolean J2       = false;         //Seta o jogador atual.     
    public static boolean PC       = false;         //Seta o jogador atual.
    public static String lig4[][]  = new String[6][7];     //Matriz usada para jogar.
    public static boolean hasWinner= false;                //Termina o jogo quando algum player ganhar.
    public static void main(String[] args)
    {
        Scanner sc          = new Scanner(System.in);        
        for(int i = 0; i<linha.length; i++)         //Preenche o array das linhas com o número da última linha.
        {
            linha[i] = 5;
        }
        
        boolean wenttocatch = false;                //Usado para checar coluna.
                
        System.out.printf("Selecione o modo de jogo: \n     1 --- 2 Jogadores\n     2 --- Jogador Contra PC\n");
        int modo = sc.nextInt();
        switch(modo)
        {
            case 1:
            startGame(1);
            break;
            case 2:
            startGame(2);
            break;
            default:
            startGame(1);
            break;
        }
          
        while(!hasWinner)
        {
            do{
                if(J1)
                    playerName = "J1";
                else if(J2)
                    playerName = "J2";
                else
                    playerName = "PC";
                
                System.out.printf("É a vez do %s, digite a posição desejada\n", playerName);                
                if(sc.hasNextInt()){
                    int col = sc.nextInt();
                    wenttocatch = true;
                    play(col, lig4, modo);      
                }else{
                    sc.nextLine();
                    System.out.println("Insira um valor de 1 à 7");
                }
            }while(!wenttocatch);
        }
    }
    public static void startGame(int mode)
    {            
            if(mode == 1){
                if(r.nextBoolean()){//Seleciona aleatório o primeiro jogador
                    J1 = true;
                }else{
                    J2 = true;
                }
            }else if(mode == 2){ //Contra PC.
                J1 = true;       //Sempre começa com jogador 1
            }
            reset(lig4);         //Limpa toda matriz.
    }
    public static void play(int col, String lig4[][], int modo)
    {
        if((col > 0 && col < 8) && linha[col-1] >= 0)
        {
            hasWinner = ganhou(col);
            lig4[linha[col-1]][col-1] = playerName;        
            linha[col-1]--;
            print(lig4);
            changePlayer(modo);
        }else if(col <= 0 || col >= 8){
            System.out.printf("Jogada Inválida, insira uma coluna de 1 à 7\n");
        }else{
            System.out.printf("Jogada Inválida, a coluna se encontra cheia\n");
        }        
    }
    public static void changePlayer(int modo)
    {
        if(modo == 1){ //Contra outro Jogador
            if(J1){
            J1 = false;
            J2 = true;
            }else if(!J1){
            J2 = false;
            J1 = true;
            }
        }else if(modo == 2){ //Contra PC
            
        }
    }
    public static void reset(String lig4[][])
    {
        for(int i=0; i<lig4.length; i++)
        {
            for(int j = 0; j<lig4[0].length; j++)
            {
                lig4[i][j] = "";
            }         
        }
    }
    public static void print(String lig4[][])
    {
        for(int i = 0; i < 30; i++)
            System.out.println();
        System.out.println("  01   02   03   04   05   06   07");
        for(int i=0; i<lig4.length; i++)
        {
            for(int j = 0; j<lig4[0].length; j++)
            {                
                System.out.printf(" |%2s|" ,lig4[i][j]);
            }
            System.out.println();
        }
        for(int i = 0; i < 10; i++)
            System.out.println();
    }
    
    
    public static boolean ganhou(int col){
        String jogador;
        int cont = 0;
        //coluna
        for(int i = linha[col-1] + 1; i < 6; i++){
            if(playerName.equals(lig4[i][col-1])){
                cont++;
            }
            else
                break;
        }
        if(cont >=3)
            return true;
        
        //linha
        cont = 0;
        for(int i = col - 2; i >=0; i--){
           if(playerName.equals(lig4[linha[col-1]][i])){
                cont++;
            }
            else
                break;
        
        }
        for(int i = col; i < 7; i++){
           if(playerName.equals(lig4[linha[col-1]][i])){
                cont++;
            }
            else
                break;
        }
        if(cont >= 3)
            return true;
            
        //diagonal principal
        cont = 0;
        int x, y;
        x = linha[col - 1]-1;
        y = col - 2;
        while(x >= 0 && y >= 0){
            if(playerName.equals(lig4[x][y])){
                cont++;
            }
            else
                break;
            x--;
            y--;
        }
        x = linha[col - 1] + 1;
        y = col;
        while(x <= 5 && y <= 6){
            if(playerName.equals(lig4[x][y])){
                cont++;
            }
            else
                break;
            x++;
            y++;
        }
        if(cont >=3)
            return true;
            
        //diagonal secundaria
        cont = 0;
        x = linha[col - 1]-1;
        y = col;
        while(x >= 0 && y <= 5){
            if(playerName.equals(lig4[x][y])){
                cont++;
            }
            else
                break;
            x--;
            y++;
        }
        x = linha[col - 1] + 1;
        y = col - 2;
        while(x <= 5 && y >= 0){
            if(playerName.equals(lig4[x][y])){
                cont++;
            }
            else
                break;
            x++;
            y--;
        }
        if(cont >=3)
            return true;    
        return false;
    }
}