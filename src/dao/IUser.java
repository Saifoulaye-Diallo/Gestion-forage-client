package dao;

import entities.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUser extends Remote {
    public int add(User user) throws RemoteException;
    public int delete(int id) throws RemoteException;
    public int update(User user) throws RemoteException;
    public User get(int id) throws RemoteException;
    public List<User> getAll() throws RemoteException;
    public User login(String email, String pwd) throws RemoteException;
}

