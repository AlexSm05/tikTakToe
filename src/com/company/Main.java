package com.company;

import java.io.*;
import java.util.ArrayList;
// (Консольное приложение)
// Крестики-нолики на двоих на поле 3х3 - игроки ходят по очереди, играют за одним компьютером. Интерфейс полностью текстовый, приложение работает в консоли.
// При запуске приложения игрок попадает в меню с тремя пунктами:
// Играть
// История
// Выход

// Игрок вводит номер пункта, которій он выбрал.
// Пункт "Играть" начинает новую игру. Сперва оба игрока вводят свои имена, затем по очереди ставят крестики/нолики в клетки поля. Как только кто-то победил - результат игры (итоговый вид игрового поля, имена игроков, победитель) записываются в файл. Все игры записываются в один файл.
// После каждой игры следует спрашивать о необходимости новой игры. При отрицательном ответе - завершать работу приложения.

// Пункт "История" выводит информацию о всех играх, что были ранее. Все данные из файла следует загрузить в память при запуске приложения и выводить их при запросе (т.е. чтение файла происходит лишь при запуске).

// Пункт "Выход" завершает работу приложения.




public class Main {

    public static void main(String[] args) {
	// write your code here
        String file="E://history.txt";
        ArrayList<String> list=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(file))) {
            String line =reader.readLine();
            while(line!=null){
                list.add(line);
                line=reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("1.Play");
        System.out.println("2.History");
        System.out.println("3.Exit");
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        int n=0;
        while (true){
            try {
                n = Integer.parseInt(reader.readLine());
                if (n>0&&n<4){
                    break;
                }else {
                System.out.println("Choose  number 1-3");}
            }catch (NumberFormatException e) {
                System.out.println("Please enter the number");
            }catch (IOException e) {
            e.printStackTrace();
            }
        }
        switch (n){

            case(1):
                String n1,n2,repeat;
                try {
                    while (true) {
                        System.out.println("1st player name");
                        n1 = reader.readLine();
                        System.out.println("2st player name");
                        n2 = reader.readLine();
                        Game game = new Game();
                        game.start(n1, n2);
                        while (true) {
                            System.out.println("Play 1 more game?(Y/N)");
                            repeat = reader.readLine();
                            if (repeat.equals("N")) {
                                System.exit(0);
                            } else if (repeat.equals("Y")) {

                                break;
                            }
                            System.out.println("write Y or N");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case(2):
                boolean b=false;
                for (String s:
                     list) {
                    Game game1=new Game();

                    if (b==false) {
                        String[] str=s.split("");
                        int[] ints=new int[str.length];
                        for (int j = 0; j <str.length; j++) {
                            ints[j]=Integer.parseInt(str[j]);
                        }
                        game1.drawCanvas(ints);
                        b=true;}
                    else {
                        System.out.println(s);
                        b=false;
                    }

                }
                break;
            case(3):
                System.exit(0);
                break;
        }
}}

