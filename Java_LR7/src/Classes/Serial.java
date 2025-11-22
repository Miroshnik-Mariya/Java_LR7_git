package Classes;

import Exception.*;
import Classes.Interfaces.Content;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Serial implements Content {
    private String title;
    private int[] episodesSeason; //количество серий в каждом сезоне
    private int rating; //рейтинг


    public Serial() {
        title = "Unknown title";
        episodesSeason = new int[1];
        rating = 0;
    }

    public Serial(String title, int[] episodesSeason, int rating) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidDataException("Введено пустое название.");
        }
        this.title = title;

        if (episodesSeason == null) {
            throw new InvalidDataException("Неверно указано количество страниц.");
        }
        for (int pages : episodesSeason) {
            if (pages < 0) {
                throw new InvalidDataException("Количество страниц не может быть отрицательным.");
            }
        }
        this.episodesSeason = episodesSeason.clone();

        if (rating < 0 || rating > 5) {
            throw new InvalidDataException("Рейтинг должен быть от 0 до 5.");
        }
        this.rating = rating;
    }

    @Override
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidDataException("Введено пустое название.");
        }
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setArray(int[] episodesSeason) {
        if (episodesSeason == null) {
            throw new InvalidDataException("Неверно указано количество серий в каждом сезоне.");
        }
        for (int series : episodesSeason) {
            if (series < 0) {
                throw new InvalidDataException("Количество серий не может быть отрицательным числом.");
            }
        }
        this.episodesSeason = episodesSeason.clone();
    }

    @Override
    public int[] getArray() {
        //проверка на массив с длиной 0
        return episodesSeason;
    }

    @Override
    public int getElement(int idx) {
        if (idx < 0 || idx > episodesSeason.length - 1) {
            throw new InvalidDataException("Индекс вышел за границы массива.");
        }
        return episodesSeason[idx];
    }

    @Override
    public void setElement(int idx, int value){
        if (idx < 0 || idx > episodesSeason.length - 1) {
            throw new InvalidDataException("Индекс вышел за границы массива.");
        }
        else{
            episodesSeason[idx]=value;
        }
    }

    @Override
    public void setRating(int rating) {
        if (rating >= 0 && rating < 6) {
            this.rating = rating;
        } else {
            throw new InvalidDataException("Введена неверная оценка. Рейтинг может быть целым числом от 0 до 5.");
        }
    }

    @Override
    public int getRating() {
        return rating;
    }

//    @Override
//    public String toString() {
//        double avg = calculateAverage();
//        String avgString = String.format("%.1f", avg);
//
//        return String.format("""
//                        ═══════════════════════════════════
//                                       СЕРИАЛ
//                        ═══════════════════════════════════
//                          Название: %-20s
//                          Количество сезонов: %2d
//                          Рейтинг: %1d
//                          Среднее кол-во серий в сезоне: %3s
//                        ═══════════════════════════════════
//                        """,
//                title.length() > 20 ? title.substring(0, 17) + "..." : title,
//                episodesSeason.length,
//                rating,
//                avgString
//        );
 //   }

    @Override
    public double calculateAverage() throws SeriesOperationException {
        if (episodesSeason.length == 0) {
            throw new SeriesOperationException("Сезон не содержит эпизодов.");
        }
        double res = 0;
        int sum = 0;
        for (int pages : episodesSeason) {
            sum += pages;
        }
        return sum/episodesSeason.length;
    }

    /*
    @Override
    public String printInfo(int idx){
        try {
            double business = calculateAverage();
            return String.format("""
                          %s (№ %2d)
                          Длина массива: %2d
                          Рейтинг: %d/5
                          Результат бизнес-метода: %.1f 
                          Массив: %s
                        """,
                    title.length() > 20 ? title.substring(0, 17) + "..." : title,
                    idx,
                    episodesSeason.length,
                    rating,
                    business,
                    Arrays.toString(episodesSeason)
            );
        } catch (SeriesOperationException e) {
            return String.format("""
                          %s (№ %2d)
                          Длина массива: %2d
                          Рейтинг: %d/5
                          Результат бизнес-метода: ошибка расчета
                          Массив: %s
                        """,
                    title.length() > 20 ? title.substring(0, 17) + "..." : title,
                    idx,
                    episodesSeason.length,
                    rating,
                    Arrays.toString(episodesSeason)
            );
        }
    }
*/
    @Override
    public String toString() {
        try {
            double avgEpisodes = calculateAverage();
            return String.format("""
                              Название: %s
                              Сезонов: %2d
                              Рейтинг: %d/5
                              Среднее: %.1f серий/сезон
                            """,
                    title.length() > 20 ? title.substring(0, 17) + "..." : title,
                    episodesSeason.length,
                    rating,
                    avgEpisodes
            );
        } catch (SeriesOperationException e) {
            return String.format("""
                              Название: %s
                              Сезонов: %2d
                              Рейтинг: %d/5
                              Среднее: ошибка расчета
                            """,
                    title.length() > 20 ? title.substring(0, 17) + "..." : title,
                    episodesSeason.length,
                    rating
            );
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Serial that = (Serial) obj;

        return rating == that.rating &&
                title.equals(that.title) &&
                Arrays.equals(episodesSeason, that.episodesSeason);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + Arrays.hashCode(episodesSeason);
        result = 31 * result + rating;
        return result;
    }


    //записи в байтовый поток
    @Override
    public void output(OutputStream out) throws IOException {
        DataOutputStream text = new DataOutputStream(out);
        text.writeUTF(title);
        text.writeInt(rating);
        text.writeInt(episodesSeason.length);
        for (int v : episodesSeason) text.writeInt(v);
        text.flush();
    }


    //записи в символьный поток
    @Override
    public void write(Writer out) throws IOException{
        PrintWriter pw = new PrintWriter(out);
        // Заменяем пробелы в названии на подчеркивания
        String escapedTitle = title.replace(" ", "_");
        pw.print(escapedTitle + " " + rating + " " + episodesSeason.length);
        for (int v : episodesSeason) pw.print(" " + v);
        pw.println();
        pw.flush();
    }


    @Override
    public int length(){
        return episodesSeason.length;
    }

    //Лаборатораная работа №6
    @Override
    public int compareTo(Content v2) {
        if (this.calculateAverage() > v2.calculateAverage()) {
            return 1;
        } else if (this.calculateAverage() == v2.calculateAverage()) {
            return 0;
        } else {
            return -1;
        }
    }


//    //Лабораторная работа №6
    public List<Integer> getElements() {
        List<Integer> list = new ArrayList<>();
        for (int episode : episodesSeason) {
            list.add(episode);
        }
        return list;
    }
//    @Override
//    public Iterator<Integer> iterator() {
//        return new IteratorClasses(this.episodesSeason);
//    }
    @Override
    public Iterator<Integer> iterator() {
        return getElements().iterator(); // базовая реализация на основе списка
    }

}
