import java.util.Random;
import java.util.Scanner;

public class TicTacToe
{
    // подготовка к началу игры
    private  static char [][] map; //игровое поле, матрица игры
    private  static final int SIZE = 3; // размер поля
    private  static char DOT_EMPTY = '*'; // пустое поле
    private  static char DOT_X = 'X';   // символ Х игрока 1
    private  static char DOT_O = 'O';   //  символ О игрока 2
    private static final boolean SILLY_MODE = false; // режим глупого/"умного" компьютера
    private static Scanner scanner = new Scanner(System.in); //считывание с клавиатуры
    private static Random random = new Random();
    private  static boolean SCORING_MODE = true;


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

    //заполнение массива пустыми символами
    private static void initMap() {
        map = new char[SIZE][SIZE];
        for(int i = 0; i < SIZE; i ++){
            for(int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    // вывод поля на экран
    private static void printMap() {
        for(int i = 0; i <= SIZE; i++){
            if (i==0)System.out.print("   ");
            else System.out.print(i + "  ");
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

    // ход человека
    private static void humanTurn()
    {
        int x, y;
        do
        {
            System.out.println("Введите координаты ячейки (X Y) через пробел");
            y = scanner.nextInt() - 1; // Считывание номера строки
            x = scanner.nextInt() - 1; // Считывание номера столбца
        }
        while (!isCellValid(x, y) );

        map[y][x] = DOT_X;
    }

    // ход компьютера
    private static void computerTurn()
    {
        int x = -1;
        int y = -1;
        if(SILLY_MODE){
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while(!isCellValid(x, y));
        }
        else
        {

            if (!SCORING_MODE)
            {
                boolean moveFound = false;
                for (int i = 0; i < SIZE; i++)
                {
                    for (int j = 0; j < SIZE; j++)
                    {
                        // упрощенный алгоритм
                        if (map[i][j] == DOT_EMPTY)
                        {
                            // слева сверху
                            if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j - 1] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("LU");
                                // верх
                            } else if (i - 1 >= 0 && map[i - 1][j] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("U");
                                //справа вверху
                            } else if (i - 1 >= 0 && j + 1 < SIZE && map[i - 1][j + 1] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("UR");
                                // слева
                            } else if (j - 1 >= 0 && map[i][j - 1] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("L");
                                // справа
                            } else if (j + 1 < SIZE && map[i][j + 1] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("R");
                                // слева снизу
                            } else if (i + 1 < SIZE && j - 1 >= 0 && map[i + 1][j - 1] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("LD");
                                // снизу
                            } else if (i + 1 < SIZE && map[i + 1][j] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("D");
                                // снизу cправа
                            } else if (i + 1 < SIZE && j + 1 < SIZE && map[i + 1][j + 1] == DOT_O)
                            {
                                y = i;
                                x = j;
                                moveFound = true;
                                System.out.println("RD");
                            }
                        }
                        // если найдено подходящщее условие выходим из внутреннего цикла
                        if (moveFound)
                        {
                            break;
                        }
                    } //и из внешнего
                    if (moveFound)
                    {
                        break;
                    }
                }

            } else
            { // ход с учетом рейтинга клетки
                int maxScoreFielX = -1;
                int maxScoreFielY = -1;
                int maxScore = 0;

                for (int i = 0; i < SIZE; i++)
                {
                    for (int j = 0; j < SIZE; j++)
                    { int fieldScore = 0;
                        if (map[i][j]==DOT_EMPTY)
                        {
                            // слева сверху
                            if (i-1>=0 && j-1>=0 && map[i-1][j-1]==DOT_O)
                            {

                                fieldScore++;
                                // верх
                            }
                            if (i-1>=0  && map[i-1][j]==DOT_O)
                            {

                                fieldScore++;
                                //справа вверху
                            }
                            if (i-1>=0 && j+1<SIZE && map[i-1][j+1]==DOT_O)
                            {

                                fieldScore++;
                                // слева
                            }
                            if (j-1>=0  && map[i][j-1]==DOT_O)
                            {

                                fieldScore++;
                                // справа
                            }
                            if (j+1<SIZE  && map[i][j+1]==DOT_O)
                            {

                                fieldScore++;
                                // слева снизу
                            }
                            if (i+1<SIZE && j-1>=0  && map[i+1][j-1]==DOT_O)
                            {
                                fieldScore++;
                                // снизу
                            }
                            if (i+1<SIZE && map[i+1][j]==DOT_O)
                            {
                                fieldScore++;
                                // снизу cправа
                            }
                            if (i+1<SIZE && j+1<SIZE  && map[i+1][j+1]==DOT_O)
                            {
                                fieldScore++;
                            }
                        }
                        if (fieldScore>maxScore)
                        {
                            maxScore=fieldScore;
                        maxScoreFielX = j;
                        maxScoreFielY = i;
                        }
                    }
                }
                if (maxScoreFielX != -1)
                {
                    x=maxScoreFielX;
                    y=maxScoreFielY;
                }
            }
            if (x == -1)
            {
                do
                {
                    x = random.nextInt(SIZE);
                    y = random.nextInt(SIZE);
                }
                while (!isCellValid(x, y));
                System.out.println("RANDOM");
            }
            }


        System.out.println("Компьютер выбрал ячейку " + (y + 1) + " " + (x + 1));
        map[y][x] = DOT_O;

    }

    public static boolean isCellValid(int x, int y){
        boolean result = true;

        if((x < 0 || x >= SIZE || y < 0 || y >= SIZE) || (map[y][x] != DOT_EMPTY)) {
            result = false;
        }

//        if(map[y][x] != DOT_EMPTY){
//            result = false;
//        }

        return result;
    }



    //Проверка окончания игры

    private static boolean isEndGame(char playerSymbol)
    { boolean result = false;

    printMap();

    if (checkWin(playerSymbol))
    {
        System.out.println("Победили " +playerSymbol);
        result=true;
    } else if (isMapFull())
    {
        System.out.println("Ничья");
        result=true;
    }

        return result;
    }

    // проверка заполненности игрового поля
    private static boolean isMapFull()
    { boolean result=true;

        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                if (map[i][j]==DOT_EMPTY)
                {
                    result = false;
                    break;
                }

            }
            if (!result) break;
        }

      return result;
    }

    static boolean checkWin(char playerSymbol) {
        boolean result = false;

        if (checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol)) result = true;

       /* if((map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol)){
            result = true;
        } */

        return result;
    }

    // проверка выигрышной комбинации по диагоналям
    static boolean checkWinDiagonals(char playerSymbol)
    {
        boolean leftRight, rightLeft,result=false;
        leftRight = true;
        rightLeft=true;
        for (int i = 0; i < SIZE; i++)
        {
            leftRight &= (map[i][i] == playerSymbol);
            rightLeft &= (map[SIZE-i-1][i] == playerSymbol);

        }
        if (leftRight || rightLeft) result =true;


    return  result;
    }
    // проверка выигрышной комбинации по строкам и столбцам
    static boolean checkWinLines(char playerSymbol)
    {
        boolean cols, rows, result=false;

        for (int col = 0; col < SIZE ; col++)
        {
            cols = true;
            rows = true;

            for (int row = 0; row < SIZE; row++)
            {
                cols&=map[row][row] == playerSymbol;
                rows&=map[row][row] == playerSymbol;

            }
            if (cols || rows)
            {
                result=true;
                break;
            }
            if (result) break;

        }
       return  result;
    }


}
