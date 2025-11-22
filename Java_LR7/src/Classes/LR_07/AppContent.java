package Classes.LR_07;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes.BooksSeries;
import Classes.Helper;
import Classes.LR_07.ContentDetailsDialog;
import Classes.Interfaces.*;
import Classes.LR_07.*;
import Classes.Serial;

public class AppContent extends JFrame {
    private ContentList listPanel;
    private List<Content> contentDatabase = new ArrayList<>();

    public AppContent() {
        super("Лабораторная работа №7. Мирошник М.Л.");
        setBounds(50, 50, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listPanel = new ContentList(this, contentDatabase);
        createMenu();
        setContentPane(listPanel);
    }

    // МЕНЮ С PLaF
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Меню");
        JMenu themeMenu = new JMenu("Тема оформления"); //PLaF

        // Основные пункты меню
        JMenuItem autoFillItem = new JMenuItem("Автоматическое заполнение базы");
        JMenuItem createTxtItem = new JMenuItem("Текстовый файл (.txt)");
        JMenuItem createBinItem = new JMenuItem("Бинарный файл (.bin)");
        JMenuItem createSerItem = new JMenuItem("Сериализованный файл (.ser)");
        JMenuItem createCsvItem = new JMenuItem("CSV файл (.csv)");
        JMenuItem createAllItem = new JMenuItem("Все форматы");
        JMenuItem loadItem = new JMenuItem("Заполнение базы из файла");

        // Обработчики событий
        autoFillItem.addActionListener(e -> autoFillDatabase());
        createTxtItem.addActionListener(e -> createTxtFile());
        createBinItem.addActionListener(e -> createBinFile());
        createSerItem.addActionListener(e -> createSerFile());
        createCsvItem.addActionListener(e -> createCsvFile());
        createAllItem.addActionListener(e -> createAllFiles());
        loadItem.addActionListener(e -> loadFromFile());

        // Добавляем основные пункты в меню
        fileMenu.add(autoFillItem);
        fileMenu.addSeparator();
        fileMenu.add(createTxtItem);
        fileMenu.add(createBinItem);
        fileMenu.add(createSerItem);
        fileMenu.add(createCsvItem);
        fileMenu.add(createAllItem);
        fileMenu.addSeparator();
        fileMenu.add(loadItem);

        // СОЗДАЕМ РАДИОКНОПКИ ДЛЯ PLaF
        ButtonGroup themeGroup = new ButtonGroup();

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels(); // Доступные Look and Feel
        String currentLook = UIManager.getLookAndFeel().getClass().getName(); //опр текущей темы

        for (UIManager.LookAndFeelInfo look : looks) {
            JRadioButtonMenuItem themeItem = new JRadioButtonMenuItem(look.getName());
            themeItem.setActionCommand(look.getClassName());

            // Отмечаем текущую тему
            if (look.getClassName().equals(currentLook)) {
                themeItem.setSelected(true);
            }

            themeItem.addActionListener(e -> changeLookAndFeel(look.getClassName())); //смена темы при выборе
            themeGroup.add(themeItem);
            themeMenu.add(themeItem);
        }

        // Добавляем оба меню в панель меню
        menuBar.add(fileMenu);
        menuBar.add(themeMenu);
        setJMenuBar(menuBar);
    }

    // смена темы
    private void changeLookAndFeel(String lookAndFeelClass) {
        try {
            UIManager.setLookAndFeel(lookAndFeelClass); //установка новой темы

            // Обновляем тему для всех компонентов
            SwingUtilities.updateComponentTreeUI(this);

            // Перерисовываем окно
            revalidate(); //пересчитывает расположениее и объекты
            repaint(); //перерисовать объекты


            //JOptionPane - окно-сообщение
            JOptionPane.showMessageDialog(this,
                    "Тема оформления изменена",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка смены темы: " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    // МЕТОДЫ ДЛЯ СОЗДАНИЯ ФАЙЛОВ
    // Текстовый файл
    private void createTxtFile() {
        try {
            List<Content> sampleData = createSampleContent();
            File samplesDir = new File("test_files");
            if (!samplesDir.exists()) samplesDir.mkdir(); //проверка на наличие папки + создание

            createTextSample(sampleData, new File(samplesDir, "test.txt"));
            JOptionPane.showMessageDialog(this,
                    "Текстовый файл создан: test_files/test.txt",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка создания текстового файла: " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Бинарный файл
    private void createBinFile() {
        try {
            List<Content> sampleData = createSampleContent();
            File samplesDir = new File("test_files");
            if (!samplesDir.exists()) samplesDir.mkdir();

            createBinarySample(sampleData, new File(samplesDir, "test.bin"));
            JOptionPane.showMessageDialog(this,
                    "Бинарный файл создан: test_files/test.bin",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка создания бинарного файла: " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Сериализованный файл
    private void createSerFile() {
        try {
            List<Content> sampleData = createSampleContent();
            File samplesDir = new File("test_files");
            if (!samplesDir.exists()) samplesDir.mkdir();

            createSerializedSample(sampleData, new File(samplesDir, "test.ser"));
            JOptionPane.showMessageDialog(this,
                    "Сериализованный файл создан: test_files/test.ser",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка создания сериализованного файла: " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // CSV файл
    private void createCsvFile() {
        try {
            List<Content> sampleData = createSampleContent();
            File samplesDir = new File("test_files");
            if (!samplesDir.exists()) samplesDir.mkdir();

            createCSVSample(sampleData, new File(samplesDir, "test.csv"));
            JOptionPane.showMessageDialog(this,
                    "CSV файл создан: test_files/test.csv",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка создания CSV файла: " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Все файлы
    private void createAllFiles() {
        try {
            List<Content> sampleData = createSampleContent();
            File samplesDir = new File("test_files");
            if (!samplesDir.exists()) samplesDir.mkdir();

            createTextSample(sampleData, new File(samplesDir, "test.txt"));
            createBinarySample(sampleData, new File(samplesDir, "test.bin"));
            createSerializedSample(sampleData, new File(samplesDir, "test.ser"));
            createCSVSample(sampleData, new File(samplesDir, "test.csv"));

            JOptionPane.showMessageDialog(this,
                    "Все файлы созданы в папке: test_files/\n" +
                            "• test.txt - текстовый\n" +
                            "• test.bin - бинарный\n" +
                            "• test.ser - сериализованный\n" +
                            "• test.csv - CSV",
                    "Успех", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка создания файлов: " + ex.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Текстовый формат
    private void createTextSample(List<Content> data, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8")) {

            for (Content content : data) {
                Helper.writeContent(content, writer);
                writer.write("\n");
            }
        }
    }

    // Бинарный формат
    private void createBinarySample(List<Content> data, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {  // ← ТОЛЬКО FileOutputStream

            for (Content content : data) {
                Helper.outputContent(content, fos);  // ← Прямая запись в FileOutputStream
            }
        }
    }

    // Сериализованные объекты
    private void createSerializedSample(List<Content> data, File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Content content : data) {
                Helper.serializeContent(content, oos);
            }
        }
    }

    // CSV формат
    private void createCSVSample(List<Content> data, File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            for (Content content : data) {
                Helper.writeFormatContent(content, writer);
            }
        }
    }

    // Создает тестовые данные
    private List<Content> createSampleContent() {
        List<Content> sampleData = new ArrayList<>();
        sampleData.add(Helper.createInstance("Сериал_1", new int[]{45, 50, 55}, 4));
        sampleData.add(new BooksSeries("Книги_1", new int[]{300, 320, 280}, 5));
        sampleData.add(Helper.createInstance("Фильм_1", new int[]{120, 115, 125}, 3));
        return sampleData;
    }


    // ЗАГРУЗКА ИЗ ФАЙЛА
    private void loadFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Выберите файл для загрузки");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                contentDatabase.clear();
                List<Content> loadedContent = loadContentFromFile(selectedFile);
                contentDatabase.addAll(loadedContent);
                listPanel.displayContent();

                JOptionPane.showMessageDialog(this,
                        "Успешно загружено " + loadedContent.size() + " объектов из файла: " + selectedFile.getName(),
                        "Успех", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Ошибка загрузки: " + ex.getMessage(),
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    // ЗАГРУЗКА ИЗ РАЗНЫХ ФОРМАТОВ
    private List<Content> loadContentFromFile(File file) throws IOException, ClassNotFoundException {
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".bin")) {
            return loadBinaryFormat(file);
        } else if (fileName.endsWith(".txt")) {
            return loadTextFormat(file);
        } else if (fileName.endsWith(".ser")) {
            return loadSerializedFormat(file);
        } else if (fileName.endsWith(".csv")) {
            return loadCSVFormat(file);
        } else {
            throw new IOException("Неподдерживаемый формат файла. Используйте: .bin, .txt, .ser, .csv");
        }
    }

    // БИНАРНЫЙ ФОРМАТ
    private List<Content> loadBinaryFormat(File file) throws IOException {
        List<Content> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file)) {

            while (fis.available() > 0) {
                try {
                    Content content = Helper.inputContent(fis);  //чтение
                    if (content != null) {
                        result.add(content);
                    }
                } catch (EOFException e) {
                    break;
                } catch (IOException e) {
                    if (result.isEmpty()) {
                        throw e;
                    }
                    break;
                }
            }
        }
        return result;
    }



    // ТЕКСТОВЫЙ ФОРМАТ
    private List<Content> loadTextFormat(File file) throws IOException {
        List<Content> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file)) {
            Content content = Helper.readContent(fis);
            if (content != null) {
                result.add(content);
            }
        }
        return result;
    }

    // СЕРИАЛИЗОВАННЫЕ ОБЪЕКТЫ
    private List<Content> loadSerializedFormat(File file) throws IOException, ClassNotFoundException {
        List<Content> result = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file)) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (true) {
                    try {
                        Content content = Helper.deserializeContent(ois);//чтение
                        result.add(content);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (StreamCorruptedException e) {
                try (FileInputStream fis2 = new FileInputStream(file)) {
                    Content content = Helper.deserializeContent(fis2);
                    if (content != null) {
                        result.add(content);
                    }
                }
            }
        }
        return result;
    }

    // CSV ФОРМАТ
    private List<Content> loadCSVFormat(File file) throws IOException {
        List<Content> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                try {
                    Content content = Helper.readFormatContent(scanner);
                    if (content != null) {
                        result.add(content);
                    }
                } catch (Exception e) {
                    System.err.println("Ошибка чтения строки: " + e.getMessage());
                }
            }
        }
        return result;
    }

    // АВТОМАТИЧЕСКОЕ ЗАПОЛНЕНИЕ
    private void autoFillDatabase() {
        contentDatabase.clear();

        // Создаем полный набор тестовых данных
        Content v1 = Helper.createInstance();
        Content v2 = Helper.createInstance("Тестирование", new int[]{10, 20, 30}, 4);
        Content v3 = new Serial("Test", new int[]{1,2,3}, 4);
        Content v4 = new BooksSeries("Books", new int[]{4,5,6}, 5);

        Content r1 = Helper.unmodifiable(v1);
        Content r2 = Helper.unmodifiable(v2);

        int[] array1 = new int[]{1,2,3,4,5,6};
        Content c = new Serial("Comparable1", array1, 5);

        int[] array2 = new int[]{1,2,3,4,5,6};
        Content c1 = new Serial("Comparable1", array2, 5);

        Content c2 = new Serial("Comparable2", array2, 4);
        Content c3 = new Serial("Comparable3", array2, 3);

        int[] array4 = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        Content c4 = new Serial("Comparable4", array4, 2);

        int[] array5 = new int[]{2,4,1,5,12,7,2,8,9,11,21,11,13};
        Content c5 = new Serial("Comparable5", array5, 1);

        int[] array6 = new int[]{3,3,3};
        Content c6 = new Serial("Comparable6", array6, 5);

        contentDatabase.add(v1);
        contentDatabase.add(v2);
        contentDatabase.add(v3);
        contentDatabase.add(v4);
        contentDatabase.add(r1);
        contentDatabase.add(r2);
        contentDatabase.add(c);
        contentDatabase.add(c1);
        contentDatabase.add(c2);
        contentDatabase.add(c3);
        contentDatabase.add(c4);
        contentDatabase.add(c5);
        contentDatabase.add(c6);

        listPanel.displayContent();

        JOptionPane.showMessageDialog(this,
                "База автоматически заполнена: " + contentDatabase.size() + " объектов",
                "Успех", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showContentDetails(Content content, int index) {
        ContentDetailsDialog detailsDialog = new ContentDetailsDialog(this, content, index);
        detailsDialog.setVisible(true);
    }
}