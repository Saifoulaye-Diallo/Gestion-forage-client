package dao;

import entities.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IClient  extends Remote {
    public int addClient(Client client)throws RemoteException;
    public int deleteClient(int id)throws RemoteException;
    public int updateClient(Client client)throws RemoteException;
    public List<Client> getlistClient()throws RemoteException;
}
