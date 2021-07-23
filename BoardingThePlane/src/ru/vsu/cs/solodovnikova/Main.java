package ru.vsu.cs.solodovnikova;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader inputFile = new FileReader("input.txt");
        Scanner scanner = new Scanner(inputFile);

        FileWriter outputFile = new FileWriter("output.txt");

        List<String> boarding = new ArrayList<>();
        List<String> groups = new ArrayList<>();
        int temp;

        temp = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < temp; i++) {
            String m = scanner.nextLine();
            boarding.add(m);
        }

        temp = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < temp; i++) {
            groups.add(scanner.nextLine());
        }
        inputFile.close();


        char[] seats;
        int seatWA;
        int count;
        char row;
        char vector;
        boolean can = false;
        int already_sitting;
        int[][] tickets = new int[2][3];
        String letter = " ";
        int number;


        for (String times : groups) {
            String[] group = times.split(" ");

            if (group[1].compareTo("left") == 0)
                row = 'l';
            else
                row = 'r';

            if (group[2].compareTo("window") == 0) {
                if (row == 'l') {
                    seatWA = 0;
                    vector = 'r';
                } else {
                    seatWA = 2;
                    vector = 'l';
                }
            } else {
                if (row == 'l') {
                    seatWA = 2;
                    vector = 'l';
                } else {
                    seatWA = 0;
                    vector = 'r';
                }
            }

            count = Integer.parseInt(group[0]);


            for (int i = 0; i < boarding.size(); i++) {
                already_sitting = 0;

                String[] rows = boarding.get(i).split("_");
                if (row == 'l') {
                    seats = rows[0].toCharArray();
                } else {
                    seats = rows[1].toCharArray();
                }

                if (seats[seatWA] == '.') {
                    if (vector == 'l') {
                        for (int j = seatWA; j > seatWA - count; j--) {
                            if (seats[j] != '.')
                                break;
                            else
                                already_sitting++;
                        }
                    } else {
                        for (int j = seatWA; j < count; j++) {
                            if (seats[j] != '.')
                                break;
                            else
                                already_sitting++;
                        }
                    }
                    if (already_sitting == count) {
                        for (int l = 0; l < count; l++) {
                            if (vector == 'l') {
                                number = seatWA - count + 1 + l;
                            } else {
                                number = seatWA + l;
                            }
                            if (row == 'r')
                                number += 3;
                            tickets[0][l] = i + 1;
                            tickets[1][l] = number;
                        }
                        can = true;
                    }
                    if (can) {
                        break;
                    }
                }
            }

            if (can) {
                outputFile.write("Passengers can take seats:");

                for (int i = 0; i < count; i++) {
                    switch (tickets[1][i]) {
                        case 0:
                            letter = "A";
                            break;
                        case 1:
                            letter = "B";
                            break;
                        case 2:
                            letter = "C";
                            break;
                        case 3:
                            letter = "D";
                            break;
                        case 4:
                            letter = "E";
                            break;
                        case 5:
                            letter = "F";
                            break;
                    }
                    outputFile.write(" " + tickets[0][i]);
                    outputFile.write(letter);
                }
                outputFile.append('\n');
                for (int i = 0; i < boarding.size(); i++) {

                    if (i == tickets[0][0] - 1) {
                        char[] scheme = boarding.get(i).toCharArray();
                        for (int t = 0; t < count; t++) {
                            if (row == 'l') {
                                scheme[tickets[1][t]] = 'X';
                            } else {
                                scheme[tickets[1][t] + 1] = 'X';
                            }
                        }
                        outputFile.write(String.valueOf(scheme));
                        outputFile.append('\n');
                        for (int t = 0; t < count; t++) {
                            if (row == 'l') {
                                scheme[tickets[1][t]] = '#';
                            } else {
                                scheme[tickets[1][t] + 1] = '#';
                            }
                        }
                        boarding.set(i, String.valueOf(scheme));
                    } else {
                        outputFile.write(boarding.get(i));
                        outputFile.append('\n');
                    }
                }

            } else {
                outputFile.write("Cannot fulfill passengers requirements");
                outputFile.append('\n');
            }
            can = false;
        }

        outputFile.close();
    }
}

