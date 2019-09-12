package com.example.dell.favourite;

/**
 * Created by DELL on 12-09-2019.
 */

public class ModelList {
    int flag;
    String name;
    ModelList(int flag,String name){
        this.name = name;
        this.flag = flag;
    }
    String getname(){
        return name;
    }
    int getFlag(){
        return flag;
    }
}
