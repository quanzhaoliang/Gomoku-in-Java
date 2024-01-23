package com.mainpackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryPanel extends Panel {
    private static HistoryPanel singleton = new HistoryPanel();
    int count = 0;
    private JLabel label;

    private static String[] columNames;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane pane;
//    private PlacementHistory placementHistory;
    private HistoryPanel() {}

    public static HistoryPanel getInstance() {
        singleton.setBackground(Color.lightGray);
        singleton.setBounds(540, 140, 240, 360);
        String[] name = {"Turn", "Player", "Column", "Row"};
        singleton.setColumNames(name);
        singleton.createLabel();
//        singleton.cretePlacementHistory();
        singleton.createHistoryTable();
        singleton.createPane();
        return singleton;
    }

    private void setColumNames(String[] columnNames) {
        columNames = columnNames;
    }

    @Override
    protected void createLabel() {
        singleton.label = new JLabel("Placement History");
        singleton.label.setBounds(540, 140, 240, 40);
        singleton.label.setBackground(Color.LIGHT_GRAY);
        singleton.label.setOpaque(false);
        singleton.add(label);
    }
//    private void cretePlacementHistory() {
//        singleton.placementHistory = new PlacementHistory();
//    }

    private void createHistoryTable() {
        singleton.tableModel= new DefaultTableModel(columNames, 0);
        singleton.table = new JTable(tableModel);
        singleton.table.setEnabled(false);
        MyTableCellRenderer tableCellRenderer = new MyTableCellRenderer();
        tableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        singleton.table.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
        singleton.table.getColumnModel().getColumn(1).setCellRenderer(tableCellRenderer);
        singleton.table.getColumnModel().getColumn(2).setCellRenderer(tableCellRenderer);
        singleton.table.getColumnModel().getColumn(3).setCellRenderer(tableCellRenderer);
    }

    private void createPane() {
        singleton.pane = new JScrollPane(table);
        singleton.pane.setPreferredSize(new Dimension(200, 310));
        singleton.add(pane);
    }

//    public void setPlacementHistory(PlacementHistory placementHistory) {
//        this.placementHistory = placementHistory;
//    }

    public void addMovement(int xCoordinate, int yCoordinate) {
//        singleton.placementHistory.add(xCoordinate, yCoordinate);
        singleton.tableModel.addRow(add(xCoordinate, yCoordinate));
        count++;
    }

    public String[] add(int xCoordinate, int yCoordinate) {
        String n = String.valueOf(this.count + 1);
        String p;
        if(this.count % 2 == 0) {
            p = "BLACK";
        }
        else {
            p = "WHITE";
        }
        String c = String.valueOf(xCoordinate);
        String r = String.valueOf(14 - yCoordinate);
        return new String[]{n, p, c, r};
    }

    @Override
    public void resetPanel(){
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0); // Remove the first row (index 0) repeatedly
        }
        count = 0;
//        singleton.placementHistory.resetMovements();
    }

    @Override
    public void undoPanel(){
        int rowCount = singleton.tableModel.getRowCount();
        if (rowCount >= 2) {
            singleton.tableModel.removeRow(rowCount - 1); // Remove the last row
            singleton.tableModel.removeRow(rowCount - 2); // Remove the second-to-last row (after the last row is removed)
            count -= 2;
//            singleton.placementHistory.removeLast();
////            singleton.placementHistory.countDec();
//            singleton.placementHistory.removeLast();
////            singleton.placementHistory.countDec();
        }
    }
    public void undoPanelMultiPlay(){
        int rowCount = singleton.tableModel.getRowCount();
        if (rowCount >= 1) {
            singleton.tableModel.removeRow(rowCount - 1); // Remove the last row
            count--;
//            singleton.placementHistory.removeLast();
//            singleton.placementHistory.countDec();
        }
    }
}
