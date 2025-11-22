package Classes.LR_07;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import Classes.Helper;
import Classes.Interfaces.*;
import Classes.LR_07.AppContent;
import Classes.Serial;
import Classes.BooksSeries;


public class ContentList extends JPanel {
    private AppContent parent;
    private JPanel contentPanel;
    private List<Content> contentDatabase;


    public ContentList(AppContent parent, List<Content> contentDatabase) {
        this.parent = parent;
        this.contentDatabase = contentDatabase;

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Массив объектов типа Content:");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(label, BorderLayout.NORTH);

        // Панель с содержимым
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(contentPanel);
        add(scroll, BorderLayout.CENTER);

        // ОТОБРАЖАЕМ объекты
        displayContent();
    }

    // все объекты Content
    void displayContent() {
        contentPanel.removeAll(); // Очищаем панель

        if (contentDatabase.isEmpty()) {
            JLabel emptyLabel = new JLabel("Список объектов пуст.");
            emptyLabel.setForeground(Color.GRAY);
            contentPanel.add(emptyLabel);
        } else {
            // Создаем панель для объекта Content
            for (int i = 0; i < contentDatabase.size(); i++) {
                final Content content = contentDatabase.get(i);
                final int index = i;

                JPanel itemPanel = createContentItem(content, index);
                contentPanel.add(itemPanel);

                // Добавляем отступ между элементами
                if (i < contentDatabase.size() - 1) {
                    contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            }
        }
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // один объект Content
    private JPanel createContentItem(Content content, int index) {
        JPanel panel = new JPanel(new BorderLayout());

        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        // panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setBackground(Color.WHITE);
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Информация об объекте
        JLabel titleLabel = new JLabel((index + 1) + ". " + content.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
//
//        JLabel infoLabel = new JLabel(String.format(
//                "Рейтинг: %d/5 | Элементов: %d | Тип: %s",
//                content.getRating(),
//                content.length(),
//                content.getClass().getSimpleName()
//        ));
//        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(titleLabel, BorderLayout.NORTH);
        //infoPanel.add(infoLabel, BorderLayout.CENTER);
        infoPanel.setOpaque(false);  // панель прозрачная

        panel.add(infoPanel, BorderLayout.CENTER);

        // Обработчик клика
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.showContentDetails(content, index);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                panel.setBackground(new Color(164, 229, 198));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(Color.WHITE);
            }
        });
        return panel;
    }
}