import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    public MainFrame() {
        init();
    }

    public void init() {
        setTitle("Welcome");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a table model and set column names
        String[] columnNames = {"ID", "Name", "Date", "Location", "Duration", "Magnitude", "Potential Fall"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Fetch actual data
        List<Report> reports = fetchReports();

        // Add rows to the table model
        for (Report report : reports) {
            Object[] rowData = {
                report.getnumberID(),
                report.getName(),
                report.getDate(),
               
                report.getDuration(),
                report.getMagnitude(),
                report.getPotentialFall()
            };
            tableModel.addRow(rowData);
        }

        // Create a table with the table model
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        // Custom cell renderer
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    cell.setBackground(new Color(184, 207, 229));
                } else {
                    cell.setBackground(Color.WHITE);
                }
                return cell;
            }
        });

        // Customize table header
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(new Color(220, 220, 220));
        header.setForeground(Color.BLACK);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private List<Report> fetchReports() {
        // Replace this with actual data fetching logic
        return List.of(
            new Report(1, "Alice Johnson", "2024-09-28", "Chicago", 150, 4, true),
            new Report(2, "Bob Brown", "2024-09-27", "Houston", 100, 2, false)
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame myFrame = new MainFrame();
            myFrame.setVisible(true);
        });
    }
}
