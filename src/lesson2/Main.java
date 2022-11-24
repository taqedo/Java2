package lesson2;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int chislo = 1;

    String[][] numbers = new String[4][4];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                numbers[i][j] = String.valueOf(chislo);
                chislo++;
                System.out.print(numbers[i][j] + " ");
            }
            System.out.println();
        }
        numbers[1][2] = null;
        meanOfAnyArr(numbers);
    }

  public static void meanOfAnyArr(String[][] arr){
        int mean = 0;

            if (arr.length != 4) throw new MyArraySizeException("Wrong array size!");

            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length != 4) throw new MyArraySizeException("Wrong array size!");
                for (int j = 0; j < arr[i].length; j++) {
                  try {
                      mean += Integer.parseInt(arr[i][j]);
                  }catch (NumberFormatException e){
                      throw new MyArrayDataException("Ячейка переданного массива [" + i + "][" + j + "], не является числом ");

                  }
                  }
                }
      System.out.println("Сумма всех элементов переданного массива = " + mean);
    }
  }

