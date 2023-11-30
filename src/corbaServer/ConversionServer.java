package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import service.ConversionImpl;

import javax.naming.Context;
import javax.naming.InitialContext;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            // Initialisation du middleware CORBA
            ORB orb = ORB.init(args, null);

            // Récupérer une référence vers le RootPOA
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

            // Activer le POAManager
            poa.the_POAManager().activate();

            // Instancier le servant (OD)
            ConversionImpl od = new ConversionImpl();

            //Configuration JNDI
            Context ctx = new InitialContext();
            // publication de l'od
            ctx.rebind("OD", poa.servant_to_reference(od));

            // Attendre les appels des clients
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}