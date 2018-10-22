package AplanaCollectionsLesson;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> text = fileToString("C:/Aplana.txt");
        HashMap<String, Integer> statistics = statistics(text);
        Map<String, Integer> sortMap = hashMapToTreeMap(statistics);

        printMap(statistics);
        printMap(sortMap);
        maxInMap(sortMap);
    }
    //метод считывает в ArrayList<String> весь файл посимвольно
    public static ArrayList<String> fileToString(String fileName) {
        ArrayList<String> listWord = new ArrayList<>();
        int s = 0;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                while (((s = br.read()) != -1)) {
                    listWord.add(String.valueOf((char) s));
                }
            }
        } catch (IOException exc) {
            System.out.println("Ошибка ввода-вывода" + exc);
        }
        return listWord;
    }
    //метод "проходит" ArrayList<String> и собирает все слова в MAP подсчитываю количество повторений
    public static HashMap<String, Integer> statistics(ArrayList<String> text) {
        HashMap<String, Integer> statistics = new HashMap<>();
        String word = "";
        for (int i = 0; i < text.size(); i++) {
            char a = text.get(i).charAt(0);
            if (a != 32) {
                word += text.get(i);
            } else {
                Integer count = statistics.get(word);
                if (count == null) {
                    count = 0;
                }
                statistics.put(word, ++count);
                word = "";
            }
        }
        return statistics;
    }
    //метод переводит hashMap в treeMap преобразуя регистр всех строк в нижний. Соответсвенно коллекция
    //таким образом самосортируется
    public static Map<String, Integer> hashMapToTreeMap(HashMap<String, Integer> hashMap) {
        Map<String, Integer> sortMap = new TreeMap<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {

            sortMap.put(stringIntegerEntry.getKey().toLowerCase(), stringIntegerEntry.getValue());
        }
        return sortMap;
    }
    //печатает Map.
    public static void printMap(Map<String, Integer> map) {
        System.out.println(map);
    }
    //ищет и выводит на печать слово встречающееся максимальное кол-во раз
    //если есть слова с одинаковым кол-вом повторений в тексте, выведется только то, которое встретилось первым
    public static void maxInMap(Map<String, Integer> map) {
        int max = 0;
        String word = "";
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            if (stringIntegerEntry.getValue() > max) {
                word = stringIntegerEntry.getKey();
                max = stringIntegerEntry.getValue();
            }
        }
        System.out.println("Слово " + word + " встречается в тексте " + max + " раз");
    }
}

