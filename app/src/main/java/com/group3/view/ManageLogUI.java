package com.group3.view;

import javax.swing.*;
import java.awt.*;

public class ManageLogUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public ManageLogUI() {
        setTitle("Lịch Sử Tập Luyện & Dinh Dưỡng");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel lblInfo = new JLabel("Chức năng đang phát triển (Hiển thị Bảng Logs tại đây)", SwingConstants.CENTER);
        panel.add(lblInfo, BorderLayout.CENTER);
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}