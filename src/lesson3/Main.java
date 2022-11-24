package lesson3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {
// Задание 1:
//        Создаем массив слов и передаем его в наш метод:

        String[] words = {"word","word1","word2","word3","word1","word4","word5","word2","word6","word4","word9","word8","word11","word","word2","word3","word",
                "word5","word12","word16","word"};

        HashMap<String, Integer> hw = new HashMap<>();
        for (String o : words){
            hw.put(o, hw.getOrDefault(o, 0) + 1);

        }
        System.out.println(hw);

        System.out.println(hw.containsKey("word"));



//        wordCounter(words);


//  Задание 2:
//  Создаем телефонный справочник в виде хэш таблицы:

        Phonebook phoneBook = new Phonebook();
        phoneBook.put("Ivanov", Integer.toString(111111));
        phoneBook.put("Sidorov", Integer.toString(222222));
        phoneBook.put("Petrov", Integer.toString(333333));
        phoneBook.put("Smith", Integer.toString(444444));
        phoneBook.put("Smirnov", Integer.toString(555555));
        phoneBook.put("Danilov", Integer.toString(666666));
        phoneBook.put("Serov", Integer.toString(777777));
        phoneBook.put("Tinkov", Integer.toString(888888));
        phoneBook.put("Potolkov", Integer.toString(111222));
        phoneBook.put("Kirkorov", Integer.toString(111333));
        phoneBook.put("Orlov", Integer.toString(111444));
        phoneBook.put("Baranov", Integer.toString(111555));
        phoneBook.put("Ciganov", Integer.toString(111666));
        phoneBook.put("Miller", Integer.toString(999999));
        phoneBook.put("Filler", Integer.toString(111777));
        phoneBook.put("Deniska", Integer.toString(5051436));

        System.out.println(phoneBook.get("Danilov"));
        phoneBook.add("Smith", Integer.toString(545454));
        phoneBook.add("Smith", "221222");
        phoneBook.add("Smith", "800800");


        System.out.println(phoneBook.get("Smith"));





    }

    public static void wordCounter(String[] words){
//        Добавляем переданный массив в список:
        ArrayList<String> wList = new ArrayList<>(Arrays.asList(words));
        System.out.println("Изначальный массив: " + Arrays.deepToString(words));

//        Сортируем массив и удаляем повторяющиеся слова:

        Collections.sort(wList);
        ArrayList<String> allWords = new ArrayList<>();
        String term = null;
        for (String o : wList){
            if (o != term) {
                allWords.add(o);
                term = o;
            }
        }
        System.out.println("Список слов: " + allWords);

//        Посчитать сколько раз встречается каждое слово в массиве words и вывести массив уникальных слов:

        ArrayList<String> howOften = new ArrayList<>();
        ArrayList<String> exclusiveWords = new ArrayList<>();
        int count;
        for (String o : allWords){
            count =  Collections.frequency(wList, o);
            howOften.add(o + " = " + count + " раз");
            if (count == 1){
                exclusiveWords.add(o);
            }
        }
        System.out.println(howOften);
        System.out.println("Уникальные слова: " + exclusiveWords);

    }
}
