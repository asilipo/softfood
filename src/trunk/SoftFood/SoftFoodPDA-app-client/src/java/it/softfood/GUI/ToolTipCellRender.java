/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.softfood.GUI;

import it.softfood.entity.Variante;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author marcograsso
 */
public class ToolTipCellRender extends DefaultTableCellRenderer {

    private ArrayList<Variante> varianti;
    
    public ToolTipCellRender(ArrayList<Variante> varianti) {
        super();
        this.varianti = varianti;
    }

    public void setValue(String aValue) {
        String result = aValue;
        if ((aValue != null)) {
            StringBuilder tooltip = new StringBuilder(result);
            setToolTipText(tooltip.toString());
        }
        super.setValue(result);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object obj, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(
                table, obj, isSelected, hasFocus, row, column);
        setToolTipText("Ciao  " + row);
        if (isSelected) {
            cell.setBackground(Color.green);
            
        } else {
            if (row % 2 == 0) {
                cell.setBackground(Color.cyan);
            } else {
                cell.setBackground(Color.lightGray);
            }
        }
        return cell;
    }

}
