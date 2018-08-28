package util;

import entity.UsersEntity;
import interfaces.impls.Authentication;
import org.apache.commons.codec.digest.DigestUtils;
import parser.ConverterJSON;
import parser.pojo.UserCache;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CacheUtil { //сохранятет пользователя на неделю

    private UserCache userCache;
    private File fileData;
    private ConverterJSON<UserCache> json = new ConverterJSON<>(UserCache.class);
    private final long MAX_TIME = 1*7*24*60*60*1000;
    private Date dateNow;

    public CacheUtil() {
        this.fileData = new File(getClass().getResource("/json/data.json").getPath());
        json.setSetting(fileData);
        dateNow = new Date();
    }

    public CacheUtil(UserCache userCache) {
        this.fileData = new File(getClass().getResource("/json/data.json").getPath());
        this.userCache = userCache;
        json.setSetting(fileData);
        dateNow = new Date();
    }



    public CacheUtil(File path) {
        this.fileData = path;
    }

    public UserCache getUserCache() {
        return userCache;
    }

    public void setUserCache(UserCache userCache) {
        this.userCache = userCache;
    }

    public void save(){
        json.toJSON(userCache);
    }

    public void remove(){
        json.toJSON(new UserCache());
    }

    private Boolean checkDate() throws IOException {
        return (dateNow.getTime()-Long.valueOf(json.toObject().getDateFrom())<MAX_TIME) ? true : false;
    }

    public Boolean checkCacheUser(){
        try {
            UserCache user = json.toObject();
            Authentication auth = new Authentication();
            if (auth.getUserById(user.getUserID()) != null)
                if (auth.getUserById(user.getUserID()).getLogin().equals(user.getLogin()) &&
                        DigestUtils.md5Hex(auth.getUserById(user.getUserID()).getPassword()).equals(user.getPassword())){
                    if (checkDate())
                        UsersEntity.user = auth.getUserById(user.getUserID());
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

}
