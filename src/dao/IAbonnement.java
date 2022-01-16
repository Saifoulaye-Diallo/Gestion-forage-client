package dao;



import entities.Abonnement;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAbonnement extends Remote {
    public int addAbo(Abonnement abonnement)throws RemoteException;
    public int deleteAbo(int id)throws RemoteException;
    public int updateAbo(Abonnement abonnement)throws RemoteException;
    public List<Abonnement> getlistAbonnement()throws RemoteException;
    public Abonnement abonnement(int id) throws RemoteException;
    public Abonnement Abonnements(int id) throws RemoteException;
}
