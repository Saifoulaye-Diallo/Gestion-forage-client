package dao;



import entities.Fonction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IFonction extends Remote {
    public int add(Fonction fonction) throws RemoteException;
    public int delete(int id) throws RemoteException;
    public int update(Fonction fonction) throws RemoteException;
    public Fonction get(int id) throws RemoteException;
    public List<Fonction> getAll() throws RemoteException;
}
