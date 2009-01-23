/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.softfood.GUI;

import it.softfood.entity.LineaOrdinazione;
import it.softfood.entity.Ordinazione;
import it.softfood.entity.Variante;
import it.softfood.facade.ordinazione.OrdinazioneFacadeRemote;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author marcograsso
 */
public class ToolTipCellRender extends DefaultTableCellRenderer {

    private ArrayList<Variante> varianti;
    private XTableColumnModel id_antipasto;
    private OrdinazioneFacadeRemote ordinazioneFacade;
    private TableColumn id;

    public ToolTipCellRender(OrdinazioneFacadeRemote ordinazioneFacade, TableColumn id, XTableColumnModel id_antipasto) {
        super();
        this.id = id;
        this.id_antipasto = id_antipasto;
        this.ordinazioneFacade = ordinazioneFacade;
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

        id_antipasto.setColumnVisible(id, true);


        
        Long id_linea = (Long) table.getValueAt(row, 0);

        
        LineaOrdinazione lineaOrdinazione = ordinazioneFacade.selezionaLineaOrdinazionePerId(id_linea);
        
        varianti = (ArrayList<Variante>) ordinazioneFacade.selezionaVariantiPerLineaOrdinazione(lineaOrdinazione);

        String tool="";

        if (varianti != null) {


            for (Variante var : varianti) {
                tool=tool + var.getTipoVariazione().toString() + " " + var.getIngrediente().getNome() + "\n";

            }
            
            setBackground(Color.LIGHT_GRAY);
            setToolTipText(tool);
            tool="";

        } else {
            setBackground(Color.WHITE);
        }

        id_antipasto.setColumnVisible(id, false);

        return cell;
    }
}
