import java.util.Scanner;

public class TicTacToe
{
    // подготовка к началу игры
    private  static char [][] map; //игровое поле, матрица игры
    private  static int SIZE = 3; // размер поля
    private  static char DOT_EMPTY = '*'; // пустое поле
    private  static char DOT_X = 'X';   // символ Х игрока 1
    private  static char DOT_O = 'O';   //  символ О игрока 2
    private static Scanner scanner = new Scanner(System.in); //считывание с клавиатуры

    public static void main(String[] args)
    {
        initMap();
        printMap();

        while(true)
        {
            humanTurn(); //ход человека
            if(isEndGame(DOT_X)){ break;} // проверка окончания игры

            computerTurn(); // ход компьютера
            if(isEndGame(DOT_O)) {break;} // проверка окончания игры
        }
        System.out.println("Игра окончена");


    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + "  ");
        }
        System.out.println();

        for(int i =0; i < SIZE; i++){
            System.out.print((i+1) + "  ");
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void humanTurn()
    {
        int x, y;
        do
        {
            System.out.println("Введите координаты ячейки (X Y) через пробел");
            y = scanner.nextInt() - 1; // Считывание номера строки
            x = scanner.nextInt() - 1; // Считывание номера столбца
        }
        while (!isCellValid(x, y));

        map[y][x] = DOT_X;
    }


    //проверка валидности введеных данных

    public static boolean isCellValid(int x, int y){
        boolean result = true;

        //проверка координат
        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }

        // проверка что ячейка пустая
        if(map[y][x] != DOT_EMPTY){
            result = false;
        }

        return result;
    }

    //Проверка окончания игры

    private static boolean isEndGame(char playerSymbol)
    { boolean result = false;

    printMap();

    if (chekWin() == playerSymbol)
    {
        System.out.println("Победили " +playerSymbol);
        result=true;
    }

        return result;
    }




}
