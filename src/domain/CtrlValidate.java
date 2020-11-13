package domain;

public class CtrlValidate {

    private static Kakuro kakuro;

    public CtrlValidate() {}

    public static void setKakuro(Kakuro currentKakuro) {
        kakuro = currentKakuro;
    }

    public static void computePosSumsRec(int arr[], int index, int num, int reducedNum, int lim, int posSums[], int no) {
        if (reducedNum < 0 || index > lim)
            return;

        if (reducedNum == 0 && index == lim) {
            for (int i = 0; i < index; i++)
                posSums[arr[i]-1] = 1;
            return;
        }

        int prev = (index == 0) ? 0 : arr[index - 1];

        if(prev == 9 || index >= lim) return;

        for (int k = prev+1; k <= 9 ; k++) {
            if (k!=no) {
                arr[index] = k;
                computePosSumsRec(arr, index + 1, num, reducedNum - k, lim, posSums, no);
            }
        }
    }

    public static int [] computePosSums(int x, int n, int no) { //calcula combinaciones de numeros que suman x con n numeros. deja los resultados en posSums.
        int [] posSums = new int[9];
        for(int i=0; i<9; ++i) posSums[i] = 0;

        int [] arr = new int[n];

        computePosSumsRec(arr, 0, x-no, x-no, n, posSums, no);
        return posSums;
    }

    public static int validatePosSums(int [][][] tempBoard, int [] posSums, int num, int row, int i, int j) {
        int unique = 0;
        if(row == 1) {
            for(int x=1; x<=num; ++x) {
                // if(i==4 && j+x==9) System.out.println("aAASDASDHJKLASHDJK");
                int uniqueTemp = 0;
                if(tempBoard[i][j+x][0]==-1)
                    for(int y=0; y<9; ++y)
                        tempBoard[i][j+x][y] = posSums[y];

                else {
                    for(int y=0; y<9; ++y) {
                        if(tempBoard[i][j+x][y] == 0 || posSums[y] == 0) tempBoard[i][j+x][y] = 0;
                        else ++uniqueTemp;
                    }
                }

                if (uniqueTemp == 1) {
                    System.out.println("Unique i:"+i+" j:"+(j+x));
                    ++unique;
                }
            }
        }
        else {
            for(int x=1; x<=num; ++x) {
                int uniqueTemp = 0;
                if(tempBoard[i+x][j][0]==-1)
                    for(int y=0; y<9; ++y)
                        tempBoard[i+x][j][y] = posSums[y];

                else {
                    for(int y=0; y<9; ++y) {
                        if(tempBoard[i+x][j][y] == 0 || posSums[y] == 0) tempBoard[i+x][j][y] = 0;
                        else ++uniqueTemp;
                    }
                }

                if (uniqueTemp == 1) {
                    System.out.println("Unique i:"+(i+x)+" j:"+(j));
                    ++unique;
                }
            }
        }
        return unique;
    }

    public static boolean isUnique(int [] a) { //a tiene mida 9
        int b = 0;
        for(int i=0; i<9; ++i)
            if(a[i]==1) ++b;

        return (b==1);
    }

    public static int checkForNewUniques(int [][][] tempboard) {
        Cell [][] board = kakuro.getBoard();
        int changed = 0;
        for(int i=0; i<kakuro.getRowSize(); ++i) {
            for(int j=0; j< kakuro.getColumnSize(); ++j) {
                if (!board[i][j].isWhite()) {
                    if (j< kakuro.getColumnSize()-1 && board[i][j+1].isWhite()) { //fila hacia derecha
                        // System.out.println("i: "+i+" j: "+(j+1));
                        int numWhiteRun = 1;
                        int numUnique = 0;
                        int numTemp = 0;
                        if (isUnique(tempboard[i][j+1])) {
                            //System.out.println("i: "+i+"j: "+(j+1));
                            ++numUnique;
                            for(int r = 0; r<9; ++r) {
                                if (tempboard[i][j+1][r] == 1) numTemp = numTemp + r + 1;
                            }
                        }

                        while(j+1+numWhiteRun< kakuro.getColumnSize() && board[i][j+1+numWhiteRun].isWhite()) {
                            ++numWhiteRun;
                            if (isUnique(tempboard[i][j+numWhiteRun])) {
                                // System.out.println("i: "+i+"j: "+(j+1));
                                ++numUnique;
                                for(int r = 0; r<9; ++r) {
                                    if (tempboard[i][j+numWhiteRun][r] == 1) numTemp = numTemp + r + 1;
                                }
                            }
                        }
                        if (numWhiteRun == numUnique + 1) {
                            // System.out.println("i: "+i+"j: "+j);
                            ++changed;
                            BlackCell b = (BlackCell) board[i][j];
                            numTemp = b.getHorizontal()-numTemp;
                            for(int r = 1; r<=numWhiteRun; ++r) {
                                if (!isUnique(tempboard[i][j+r])) {
                                    System.out.println("New unique: "+(i)+" "+(j+r));
                                    for (int rr = 0; rr<9; ++rr) {
                                        if(rr==numTemp-1) tempboard[i][r+j][rr] = 1;
                                        else tempboard[i][r+j][rr] = 0;
                                    }
                                }
                            }
                        }
                    }
                    if (i<kakuro.getRowSize()-1 && board[i+1][j].isWhite()) { //columna hacia abajo
                        int numWhiteRun = 1;
                        int numUnique = 0;
                        int numTemp = 0;
                        if (isUnique(tempboard[i+1][j])) {
                            ++numUnique;
                            //  System.out.println("i: "+(i+1)+"j: "+j);
                            for(int r = 0; r<9; ++r) {
                                if (tempboard[i+1][j][r] == 1) numTemp = numTemp + r + 1;
                            }
                        }

                        while(i+1+numWhiteRun<kakuro.getRowSize() && board[i+1+numWhiteRun][j].isWhite()) {
                            ++numWhiteRun;
                            if (isUnique(tempboard[i+numWhiteRun][j])) {
                                ++numUnique;
                                //   System.out.println("i: "+(i+1)+"j: "+j);
                                for(int r = 0; r<9; ++r) {
                                    if (tempboard[i+numWhiteRun][j][r] == 1) numTemp = numTemp + r + 1;
                                }
                            }
                        }
                        if (numWhiteRun == numUnique + 1) {
                            ++changed;
                            BlackCell b = (BlackCell) board[i][j];
                            numTemp = b.getVertical()-numTemp;
                            for(int r = 1; r<=numWhiteRun; ++r) {
                                if (!isUnique(tempboard[i+r][j])) {
                                    System.out.println("New unique: "+(i+r)+" "+j);
                                    for (int rr = 0; rr<9; ++rr) {
                                        if(rr==numTemp-1) tempboard[i+r][j][rr] = 1;
                                        else tempboard[i+r][j][rr] = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return changed;
    }

    public static void setDifficulty() {
        int maxRunLength = 0;
        int avgRunLength = 0;
        int numWhiteCell = 0;
        int numBlackCell = 0;
        int numRun = 0;
        int unique = 0;

        int temp = 0;   //guardamos la longitud de la run actual.

        Cell [][] board = kakuro.getBoard();

        for (int i=0; i< kakuro.getRowSize(); ++i) {
            for (int j=0; j< kakuro.getColumnSize(); ++j) {
                if (board[i][j].isWhite()) {
                    ++numWhiteCell;
                    ++temp;
                }
                else {
                    ++numBlackCell;
                    if (temp > maxRunLength) maxRunLength = temp;
                    if (temp!=0) {
                        ++numRun;
                        avgRunLength += temp;
                    }
                    temp = 0;
                }
            }
        }

        if (temp!=0) {
            ++numRun;
            avgRunLength += temp;
        }

        avgRunLength /= numRun;

        int [][][] tempBoard = new int[ kakuro.getRowSize()][ kakuro.getColumnSize()][9];
        for(int i=0; i< kakuro.getRowSize(); ++i) for(int j=0; j< kakuro.getColumnSize(); ++j) for (int z=0; z<9; ++z) tempBoard [i][j][z] = -1;

        for (int i=0; i< kakuro.getRowSize(); ++i) {
            for (int j=0; j< kakuro.getColumnSize(); ++j) {
                if (!board[i][j].isWhite()) {
                    if (j< kakuro.getColumnSize()-1 && board[i][j+1].isWhite()) { //fila hacia derecha
                        int numWhiteRun = 1;
                        while(j+1+numWhiteRun< kakuro.getColumnSize() && board[i][j+1+numWhiteRun].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i][j];
                        int [] posSums = computePosSums(b.getHorizontal(), numWhiteRun,0);
                        unique+= validatePosSums(tempBoard, posSums, numWhiteRun, 1, i, j);
                    }
                    if (i< kakuro.getRowSize()-1 && board[i+1][j].isWhite()) { //columna hacia abajo
                        int numWhiteRun = 1;
                        while(i+1+numWhiteRun< kakuro.getRowSize() && board[i+1+numWhiteRun][j].isWhite()) ++numWhiteRun;
                        // if (i==0 && numWhiteRun==4) System.out.println("aaaaaaaaa");
                        BlackCell b = (BlackCell) board[i][j];
                        int [] posSums = computePosSums(b.getVertical(), numWhiteRun,0);
                        unique+= validatePosSums(tempBoard, posSums, numWhiteRun, 0, i, j);
                    }
                }
            }
        }
      /*  for (int i=0; i<rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                if (board[i][j].isWhite()) {
                    for (int a = 0; a < 9; ++a) System.out.print(tempBoard[i][j][a]);
                    System.out.println();
                    System.out.println("i "+i+" j "+j);
                }
            }
        }*/
        boolean changed = true;
        int newUniques = 0;
        while (changed) {
            changed = false;
            int c = checkForNewUniques(tempBoard);
            if(c!=0) changed = true;
            newUniques += c;
        }
        System.out.println("Unico: "+unique+" NewUniques: "+newUniques);

        int implied = 0;
        /*int prueba = 0;

        for (int i=0; i<rowSize; ++i) {
            for (int j=0; j<columnSize; ++j) {
                if(isUnique(tempBoard[i][j])) ++prueba;
            }
        }

        System.out.println("prueba: "+prueba);*/

        changed = true;
        while (changed) {   //we check for implied
            changed = false;
            for (int i=0; i< kakuro.getRowSize(); ++i) {
                for (int j=0; j< kakuro.getColumnSize(); ++j) {
                  /*  int prueba = 0;

                    for (int ii=0; ii<rowSize; ++ii) {
                        for (int jj=0; jj<columnSize; ++jj) {
                            if(isUnique(tempBoard[ii][jj])) ++prueba;
                        }
                    }

                    System.out.println("prueba: "+prueba);*/

                    if(board[i][j].isWhite()) {
                        //subimos para arriba pa saber de cuanto es la run y de paso cogemos el valor de vertical
                        //idem para horizontal
                        int numWhiteRun = 1;
                        while ((i-numWhiteRun)>=0 && board[i-numWhiteRun][j].isWhite()) ++numWhiteRun;
                        BlackCell b = (BlackCell) board[i-numWhiteRun][j];
                        int vertical = b.getVertical();
                        temp = 1;   //contar fichas blancas hacia abajo

                        while ((i+temp)< kakuro.getRowSize() && board[i+temp][j].isWhite()) {
                            ++numWhiteRun;
                            ++temp;
                        }

                        //System.out.println("i: "+i+" j: "+j+" run: "+ numWhiteRun+" vertical "+vertical);

                        int arr [] = new int [9];
                        for (int a = 0; a<9; ++a) arr[a] = 0;

                        for (int a = 0; a<9; ++a) {
                            if (tempBoard[i][j][a] == 1) {
                                int p [] = computePosSums(vertical, numWhiteRun - 1, a + 1);
                                for(int y=0; y<9; ++y) if(p[y] == 1) arr[y] = 1;
                            }
                        }
                       /* for (int a = 0; a<9; ++a) System.out.print(tempBoard[i][j][a]);
                        System.out.println();
                        for (int a = 0; a<9; ++a) System.out.print(arr[a]);
                        System.out.println();*/
                        numWhiteRun = 1;
                        while ((i-numWhiteRun)>=0 && board[i-numWhiteRun][j].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i-numWhiteRun][j][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i-numWhiteRun][j][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }
                        numWhiteRun = 1;
                        while ((i+numWhiteRun)< kakuro.getRowSize() && board[i+numWhiteRun][j].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i+numWhiteRun][j][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i+numWhiteRun][j][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }
                        /////////////////// ara estudiem fila-->>
                        numWhiteRun = 1;
                        while ((j-numWhiteRun)>=0 && board[i][j-numWhiteRun].isWhite()) ++numWhiteRun;
                        b = (BlackCell) board[i][j-numWhiteRun];
                        int horizontal = b.getHorizontal();
                        temp = 1;   //contar fichas blancas hacia derecha

                        while ((j+temp)< kakuro.getColumnSize() && board[i][j+temp].isWhite()) {
                            ++numWhiteRun;
                            ++temp;
                        }

                        for (int a = 0; a<9; ++a) arr[a] = 0;

                        for (int a = 0; a<9; ++a) {
                            if (tempBoard[i][j][a] == 1) {
                                int p [] = computePosSums(horizontal, numWhiteRun - 1, a + 1);
                                for(int y=0; y<9; ++y) if(p[y] == 1) arr[y] = 1;
                            }
                        }
                        numWhiteRun = 1;
                        while ((j-numWhiteRun)>=0 && board[i][j-numWhiteRun].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i][j-numWhiteRun][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i][j-numWhiteRun][y] = 0;

                                    // System.out.print("aa");
                                }
                            }
                            ++numWhiteRun;
                        }
                        numWhiteRun = 1;
                        while ((j+numWhiteRun)< kakuro.getColumnSize() && board[i][j+numWhiteRun].isWhite()) {
                            for (int y = 0; y<9; ++y) {
                                if (tempBoard[i][j+numWhiteRun][y]==1 && arr[y]==0) {
                                    changed = true;
                                    tempBoard [i][j+numWhiteRun][y] = 0;
                                }
                            }
                            ++numWhiteRun;
                        }
                    }
                }
            }
        }
        //maximum run length
        //average run length
        //% of white cells over total

        int prueba = 0;

        for (int i=0; i< kakuro.getRowSize(); ++i) {
            for (int j=0; j< kakuro.getColumnSize(); ++j) {
                if(isUnique(tempBoard[i][j])) {
                    ++prueba;
                    //System.out.println("Prueba i:"+i+" j:"+j);
                }

            }
        }

        System.out.println("prueba: "+(prueba-unique-newUniques));



    }

    public static void validate(int r, int c, int sum, int [] vec, int [] res) {
        if (res[0]==2) return;
        if( r == kakuro.getRowSize() ) { res[0]++; } //hemos llegado al final, la solucion es correcta
        else {
            Cell[][] board = kakuro.getBoard();
            if( !board[r][c].isWhite() ) { // estamos en una casilla negra; queremos cambiar de columna o de casilla
                if (sum != 0) return;
                BlackCell b = (BlackCell) board[r][c];

                if (c != kakuro.getColumnSize() - 1) { // si no estem a la utlima columna
                    if (board[r][c + 1].isWhite()) {
                        sum = b.getHorizontal();
                    }
                }
                int [] aux = {0,0,0,0,0,0,0,0,0,0};
                if (c == kakuro.getColumnSize() - 1) validate(r + 1, 0, 0, aux, res); //cambiamos de fila, estamos en la ultima columna }
                else validate(r,c+1, sum, aux, res); //cambiamos de columna
            }
            else { // si estamos en una casilla blanca
                vec[0] = 1; //indica que s'ha modificat
                WhiteCell w = (WhiteCell) board[r][c];
                for(int i = 1; i < 10 && sum-i >= 0; ++i ) {
                    if ( vec[i] == 0) {
                        vec[i] = 1;
                        w.setCorrectValue(i);
                        boolean f = false;
                        if(r == kakuro.getRowSize() - 1 || !board[r+1][c].isWhite()) f =true;
                        if(kakuro.checkColumn(r-1, c, i, f, i)) {
                            if (c == kakuro.getColumnSize() - 1 && sum-i != 0) { }
                            else if (c == kakuro.getColumnSize() - 1 && sum-i == 0) { // estamos en la ultima casilla de la fila pero la suma es correcta
                                validate(r + 1, 0, 0, vec, res);
                            }
                            else validate(r, c + 1, sum-i, vec, res);
                        }
                        vec[i] = 0;
                    }
                }
            }
        }
        return;
    }
}
