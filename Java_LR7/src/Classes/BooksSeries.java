package Classes;

import Exception.*;
import Classes.Interfaces.Content;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BooksSeries implements Content {
    private String title;
    private int[] pagesSeries; //количество страниц в каждой книге серии
    private int rating; //рейтинг

    public BooksSeries() {
        title = "Unknown title";
        pagesSeries = new int[1];
        rating = 0;
    }

//    public BooksSeries(String title, int[] pagesSeries, int rating) {
//        setTitle(title);
//        setArray(pagesSeries);
//        setRating(rating);
//    }
    public BooksSeries(String title, int[] pagesSeries, int rating) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidDataException("Введено пустое название.");
        }
        this.title = title;

        if (pagesSeries == null) {
            throw new InvalidDataException("Неверно указано количество страниц.");
        }
        for (int pages : pagesSeries) {
            if (pages < 0) {
                throw new InvalidDataException("Количество страниц не может быть отрицательным.");
            }
        }
        this.pagesSeries = pagesSeries.clone();

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
    public void setArray(int[] pagesSeries) {
        if (pagesSeries == null) {
            throw new InvalidDataException("Неверно указано количество страниц в книгах серии.");
        }
        for (int pages : pagesSeries) {
            if (pages < 0) {
                throw new InvalidDataException("Количество страниц не может быть отрицательным чсислом.");
            }
        }
        this.pagesSeries = pagesSeries.clone();
    }

    @Override
    public int[] getArray() {
        //проверка на массив с длиной 0
        return pagesSeries;
    }

    @Override
    public int getElement(int idx) {
        if (idx < 0 || idx > pagesSeries.length - 1) {
            throw new InvalidDataException("Индекс вышел за границы массива.");
        }
        return pagesSeries[idx];
    }

    @Override
    public void setElement(int idx, int value){
        if (idx < 0 || idx > pagesSeries.length - 1) {
            throw new InvalidDataException("Индекс вышел за границы массива.");
        }
        else{
            pagesSeries[idx]=value;
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

//
//    @Override
//    public String toString() {
//        double avg = calculateAverage();
//        String avgString = String.format("%.1f", avg);
//
//        return String.format("""
//                        ═══════════════════════════════════
//                                     СЕРИЯ КНИГ
//                        ═══════════════════════════════════
//                          Название: %-20s
//                          Количество книг: %2d
//                          Рейтинг: %1d
//                          Среднее кол-во стр в книге: %5s
//                        ═══════════════════════════════════
//                        """,
//                title.length() > 20 ? title.substring(0, 17) + "..." : title,
//                pagesSeries.length,
//                rating,
//                avgString
//        );
//    }

    @Override
    public double calculateAverage() throws SeriesOperationException {
        if (pagesSeries.length == 0) {
            throw new SeriesOperationException("Серия не содержит книг");
        }
        double res = 0;
        int sum = 0;
        for (int pages : pagesSeries) {
            sum += pages;
        }
        return (double) sum / pagesSeries.length;
    }


    @Override
    public String toString() {
        try {
            double avgPages = calculateAverage();
            return String.format("""
                              Название: %s
                              Книг: %d
                              Рейтинг: %d/5
                              Среднее: %.1f стр.
                            """,
                    title.length() > 20 ? title.substring(0, 17) + "..." : title,
                    pagesSeries.length,
                    rating,
                    avgPages
            );
        } catch (SeriesOperationException e) {
            return String.format("""
                              Название: %s
                              Книг: %d
                              Рейтинг: %d/5
                              Среднее: ошибка расчета
                            """,
                    title.length() > 20 ? title.substring(0, 17) + "..." : title,
                    pagesSeries.length,
                    rating
            );
        }
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
                    pagesSeries.length,
                    rating,
                    business,
                    Arrays.toString(pagesSeries)
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
                    pagesSeries.length,
                    rating,
                    Arrays.toString(pagesSeries)
            );
        }
    }
*/
    @Override
    public boolean equals(Object obj) {
        // 1. Проверка на ссылочную идентичность
        if (this == obj) return true;

        // 2. Проверка на null и совпадение класса
        if (obj == null || getClass() != obj.getClass()) return false;

        // 3. Приведение типа
        BooksSeries that = (BooksSeries) obj;

        // 4. Сравнение всех значимых полей
        return rating == that.rating &&
                title.equals(that.title) &&
                Arrays.equals(pagesSeries, that.pagesSeries);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + Arrays.hashCode(pagesSeries);
        result = 31 * result + rating;
        return result;
    }
//    public void output(OutputStream out) {
//        try {
//            String data = "Title: " + getTitle() + "\n" +
//                    "Pages Series: " + Arrays.toString(getArray()) + "\n" +
//                    "Rating: " + getRating() + "\n";
//            out.write(data.getBytes(StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    //записи в байтовый поток
    @Override
    public void output(OutputStream out) throws IOException {
        DataOutputStream text = new DataOutputStream(out);
        text.writeUTF(title);
        text.writeInt(rating);
        text.writeInt(pagesSeries.length);
        for (int v : pagesSeries) text.writeInt(v);
        text.flush();
    }

    //записи в символьный поток
    @Override
    public void write(Writer out) throws IOException{
//        try {
//            out.write("Title: " + getTitle() + "\n");
//            out.write("Pages Series: " + Arrays.toString(getArray()) + "\n");
//            out.write("Rating: " + getRating() + "\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        PrintWriter pw = new PrintWriter(out);
        // Заменяем пробелы в названии на подчеркивания
        String escapedTitle = title.replace(" ", "_");
        pw.print(escapedTitle + " " + rating + " " + pagesSeries.length);
        for (int v : pagesSeries) pw.print(" " + v);
        pw.println();
        pw.flush();
    }

    @Override
    public int length(){
        return pagesSeries.length;
    }


    //Лаборатораная работа №6
    @Override
    public int compareTo(Content v2) {
        if(this.calculateAverage()>v2.calculateAverage()){
            return 1;
        }
        else if(this.calculateAverage()==v2.calculateAverage()){
            return 0;
        }
        else{return -1;}
    }

    //Лабораторная работа №6 - Задание 3
    public List<Integer> getElements() {
        List<Integer> list = new ArrayList<>();
        for (int i : pagesSeries) {
            list.add(i);
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
