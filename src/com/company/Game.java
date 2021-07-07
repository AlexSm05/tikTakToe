package com.company;

import java.io.*;

public class Game {
    public int [] canvas = {0,0,0,
            0,0,0,
            0,0,0};
    String file="E://history.txt";
    public void start(String name1,String name2){
        boolean b;
        boolean isCurrentX = false;
        canvas= new int[] {0, 0, 0,
                0, 0, 0,
                0, 0, 0};

        do {
            isCurrentX = !isCurrentX;
            drawCanvas();
            System.out.println("mark " + (isCurrentX ? "X" : "O"));
            int n = getNumber();
            canvas[n] = isCurrentX ? 1 : 2;
            b = !isGameOver(n);
            if (isDraw()){
                resultWriter(name1,name2);
                System.out.println("Draw");
                return;
            }
        } while (b);
        drawCanvas();
        System.out.println();
        resultWriter(name1,name2,isCurrentX);
        System.out.println("Players "+name1+" & "+name2+", winner is " + (isCurrentX ? "X "+name1 : "O "+name2) + "!");
    }

    public void drawCanvas(){
        System.out.println("     |     |     ");
        for (int i = 0; i < canvas.length; i++) {
            if (i!=0){
                if (i%3==0) {
                    System.out.println();
                    System.out.println("_____|_____|_____");
                    System.out.println("     |     |     ");
                }
                else
                    System.out.print("|");
            }

            if (canvas[i]==0) System.out.print("  " + i + "  ");
            if (canvas[i]==1) System.out.print("  X  ");
            if (canvas[i]==2) System.out.print("  O  ");
        }
        System.out.println();
        System.out.println("     |     |     ");
    }

    int getNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n >= 0 && n < canvas.length && canvas[n] == 0) {
                    return n;
                }
                System.out.println("Choose free cell and enter its number");
            } catch (NumberFormatException e) {
                System.out.println("Please enter the number");
            } catch (IOException e) {
            }
        }
    }

    public  boolean isDraw() {
        for (int n : canvas) if (n==0) return false;
        return true;
    }
    boolean isGameOver(int n){
        // 0 1 2
        // 3 4 5
        // 6 7 8
        //поиск совпадений по горизонтали
        int row = n-n%3; //номер строки - проверяем только её
        if (canvas[row]==canvas[row+1] &&
                canvas[row]==canvas[row+2]) return true;
        //поиск совпадений по вертикали
        int column = n%3; //номер столбца - проверяем только его
        if (canvas[column]==canvas[column+3])
            if (canvas[column]==canvas[column+6]) return true;
        //мы здесь, значит, первый поиск не положительного результата
        //если значение n находится на одной из граней - возвращаем false
        if (n%2!=0) return false;
        //проверяем принадлежит ли к левой диагонали значение
        if (n%4==0){
            //проверяем есть ли совпадения на левой диагонали
            if (canvas[0] == canvas[4] &&
                    canvas[0] == canvas[8]) return true;
            if (n!=4) return false;
        }
        return canvas[2] == canvas[4] &&
                canvas[2] == canvas[6];
    }
    public void resultWriter(String name1,String name2,boolean b){
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(file,true))) {
            String line="";
            for (int num:canvas) {
                line=line.concat(String.valueOf(num));
            }
            writer.write(line+System.getProperty("line.separator"));
            writer.write("Players "+name1+" & "+name2+", winner is " + (b ? "X "+name1 : "O "+name2)
                    + "!"+System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void resultWriter(String name1,String name2){
        try(BufferedWriter writer= new BufferedWriter(new FileWriter(file,true))) {
            String line="";
            for (int num:canvas) {
                line=line.concat(String.valueOf(num));
            }
            writer.write(line+System.getProperty("line.separator"));
            writer.write("Players "+name1+" & "+name2+" draw"+System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawCanvas(int[] print){
        System.out.println("     |     |     ");
        for (int i = 0; i < print.length; i++) {
            if (i!=0){
                if (i%3==0) {
                    System.out.println();
                    System.out.println("_____|_____|_____");
                    System.out.println("     |     |     ");
                }
                else
                    System.out.print("|");
            }

            if (print[i]==0) System.out.print("  " + i + "  ");
            if (print[i]==1) System.out.print("  X  ");
            if (print[i]==2) System.out.print("  O  ");
        }
        System.out.println();
        System.out.println("     |     |     ");
    }
}
