package LogicalAspects;

import DAO.UsersDAO;
import DAO.UsersInfoDAO;
import Entity.UserInfoEntity;
import Entity.UsersEntity;
import Utils.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DataOperation {

    UsersDAO usersDAO = new UsersDAO();
    UserInfoEntity userInfoEntity = new UserInfoEntity();
    UsersInfoDAO usersInfoDAO = new UsersInfoDAO();
    List<UsersEntity> listUsers = new ArrayList<>();

    public boolean userFind(String log, String pass){

        listUsers = usersDAO.getAllUsers();

        for(UsersEntity usersEntity:listUsers) {
            if(usersEntity.getLogin().trim().equals(log) && usersEntity.getPassword().trim().equals(pass)) {
                usersInfoDAO.updateActiveUser(usersEntity.getLogin().trim());
                return true;
            }
        }
        return false;
    }

    public UserInfoEntity activeUser(){

        UsersInfoDAO usersInfoDAO = new UsersInfoDAO();
        UserInfoEntity userInfoEntity = new UserInfoEntity();

        List<UserInfoEntity> list = new ArrayList<>();
        list = usersInfoDAO.getUser();
        for(UserInfoEntity entity:list){
            userInfoEntity = entity;
        }

        return userInfoEntity;
    }
}
