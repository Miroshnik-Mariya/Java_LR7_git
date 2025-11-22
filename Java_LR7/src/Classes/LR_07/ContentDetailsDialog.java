package Classes.LR_07;

import javax.swing.*;
import java.awt.*;
import Classes.Interfaces.*;

public class ContentDetailsDialog extends JDialog {
    public ContentDetailsDialog(JFrame parent, Content c, int idx) {
        super(parent, "Детали объекта", true);
        setSize(500, 400);
        setLocationRelativeTo(parent); //центр относительно родителя
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        JTextArea text = new JTextArea(buildContentDetails(c, idx));
        text.setEditable(false);
        text.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(new JScrollPane(text), BorderLayout.CENTER);
    }

    private String buildContentDetails(Content content, int index) {
        StringBuilder sb = new StringBuilder();

        sb.append(index + 1).append(") ").append(content.getTitle()).append("\n");
        sb.append("Тип объекта: ").append(content.getClass().getSimpleName()).append("\n");
        sb.append("Рейтинг: ").append(content.getRating()).append("\n");
        sb.append("Количество элементов: ").append(content.length()).append("\n");

        try {
            double businessValue = content.calculateAverage();
            sb.append("Бизнес-метод: ").append(businessValue).append("\n");
        } catch (Exception e) {
            sb.append("Результат бизнес-метода: ошибка расчета: ").append(e.getMessage()).append("\n");
        }

        // Детальный вывод массива
        sb.append("\nЭлементы массива:\n");
        if (content.getArray() != null && content.length() > 0) {
            sb.append(java.util.Arrays.toString(content.getArray()));
        } else {
            sb.append("  Массив пуст или null\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
