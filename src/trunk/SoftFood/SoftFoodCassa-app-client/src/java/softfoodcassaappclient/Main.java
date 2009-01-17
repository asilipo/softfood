package softfoodcassaappclient;

import it.softfood.facade.ristorante.RistoranteFacade;
import it.softfood.facade.ristorante.RistoranteFacadeRemote;
import it.softfood.session.ristorante.RistoranteSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Maria Rosaria Paone
 * @author Marco Grasso
 * @author Francesco Pacilio
 */

public class Main {

    public static void main(String[] args) {
        try {
            InitialContext initialContext = new InitialContext();
            RistoranteFacadeRemote remote = (RistoranteFacadeRemote) initialContext.lookup("it.softfood.facade.ristorante.RistoranteFacade");

            System.out.println(remote.selezionaRistorantePerPartitaIva("0").getRagioneSociale());
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
