package interfaces;

import entity.UsersEntity;

import java.util.List;

public interface UserDoInf {

    void add(UsersEntity user);

    List<UsersEntity> findByLogin(String login);

}
