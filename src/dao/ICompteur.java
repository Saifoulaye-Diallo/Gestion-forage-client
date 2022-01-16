package dao;

import entities.Compteur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICompteur extends Remote {
    public int addCompteur(Compteur compteur)throws RemoteException;
    public int deleteCompteur(int id)throws RemoteException;
    public int updateCompteur(Compteur compteur)throws RemoteException;
    public List<Compteur> getlistCompteur()throws RemoteException;
}
