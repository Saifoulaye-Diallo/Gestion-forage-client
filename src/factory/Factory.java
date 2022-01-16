package factory;

import dao.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Factory {
    public static IUser iUser;
    public static IRole iRole;
    public static IClient iClient;
    public static IChefVillage iChefVillage;
    public static IVillage iVillage;
    public static IFacture iFacture;
    public static IAbonnement iAbonnement;
    public static IReglement iReglement;
    public static ICompteur iCompteur;
    public static IFonction iFonction;

    public Factory() {
        String file = "./java.policy";
        System.setProperty("java.security.policy", file);
        try
        {
            Registry registry = LocateRegistry
                    .getRegistry("127.0.0.1",9099);
            iUser = (IUser) registry.lookup("user_dao");
            iRole = (IRole) registry.lookup("role_dao");
            iClient = (IClient) registry.lookup("client_dao");
            iChefVillage = (IChefVillage) registry.lookup("chefv_dao");
            iVillage = (IVillage) registry.lookup("village_dao");
            iFacture = (IFacture) registry.lookup("facture_dao");
            iAbonnement = (IAbonnement) registry.lookup("abo_dao");
            iReglement = (IReglement) registry.lookup("reglement_dao");
            iCompteur = (ICompteur) registry.lookup("compteur_dao");
            iFonction = (IFonction) registry.lookup("fonction_dao");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
