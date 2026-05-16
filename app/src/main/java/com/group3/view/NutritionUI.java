package com.group3.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.group3.controller.NutritionLogController;
import com.group3.model.NutritionLog;

public class NutritionUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private NutritionLogController nutritionController;
    
    private JTextField txtSearchFood;
    private JButton btnSearch, btnAddFood;
    
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private List<NutritionLog> currentListResults;

    public NutritionUI(NutritionLogController nutritionController) {
        this.nutritionController = nutritionController;
        initComponents();
        setupEvents();
    }

    private void initComponents() {
        setTitle("Tra Cứu Dinh Dưỡng Toàn Cầu");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(new JLabel("Nhập tên món ăn cần tra cứu: "), BorderLayout.NORTH);
        txtSearchFood = new JTextField();
        btnSearch = new JButton("🔍 Tìm kiếm");
        topPanel.add(txtSearchFood, BorderLayout.CENTER);
        topPanel.add(btnSearch, BorderLayout.EAST);

        String[] columns = {"Tên Sản Phẩm", "Calories (kcal)", "Protein (g)", "Carbs (g)", "Fat (g)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);

        btnAddFood = new JButton("Thêm sản phẩm đang chọn vào Nhật ký");
        btnAddFood.setBackground(new Color(0, 153, 76));
        btnAddFood.setForeground(Color.WHITE);
        btnAddFood.setFont(new Font("Arial", Font.BOLD, 13));
        btnAddFood.setEnabled(false);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultTable), BorderLayout.CENTER);
        panel.add(btnAddFood, BorderLayout.SOUTH);

        add(panel);
    }

    private void setupEvents() {
        btnSearch.addActionListener(e -> {
            String keyword = txtSearchFood.getText().trim();
            if(keyword.isEmpty()) return;
            
            tableModel.setRowCount(0);
            btnAddFood.setEnabled(false);
            
            currentListResults = nutritionController.lookupNutrition(keyword);
            
            if(currentListResults != null && !currentListResults.isEmpty()) {
                for (NutritionLog log : currentListResults) {
                    tableModel.addRow(new Object[]{
                        log.getProductName(),
                        log.getEnergy() != null ? log.getEnergy() : "0.0",
                        log.getProtein() != null ? log.getProtein() : "0.0",
                        log.getCarbohydrates() != null ? log.getCarbohydrates() : "0.0",
                        log.getFat() != null ? log.getFat() : "0.0"
                    });
                }
                btnAddFood.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu nào phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnAddFood.addActionListener(e -> {
            int selectedRow = resultTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng click chọn một sản phẩm trong bảng trước!");
                return;
            }

            NutritionLog selectedFood = currentListResults.get(selectedRow);
            
            if(nutritionController.addNutritionLog(selectedFood)) {
                JOptionPane.showMessageDialog(this, "Đã thêm " + selectedFood.getProductName() + " vào Nhật ký!");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu!");
            }
        });
    }
}