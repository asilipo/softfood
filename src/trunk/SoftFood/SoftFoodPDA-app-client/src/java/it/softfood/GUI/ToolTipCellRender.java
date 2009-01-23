/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.softfood.GUI;

import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author marcograsso
 */
public class ToolTipCellRender extends DefaultTableCellRenderer {

    public ToolTipCellRender(){
        super();
    }
    
    public void setValue(String aValue) {
    String result = aValue;
    if ( (aValue != null)) {
      StringBuilder tooltip = new StringBuilder(result);
      setToolTipText( tooltip.toString() );
    } 
    super.setValue(result);
  }   
}
