package com.lingnet.util;

import java.util.Map;

public  class ThreadLocalHolder {
    @SuppressWarnings("rawtypes")
    private static ThreadLocal local = new ThreadLocal();   
    
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setMap(Map map){
        local.set(map);
    }
    @SuppressWarnings("rawtypes")
    public static Map getMap(){
        return (Map)local.get();
    }
    
    public static void remove() {   
        local.remove();   
    } 
    
    @SuppressWarnings("rawtypes")
    private static ThreadLocal entLocal = new ThreadLocal();
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void setEntMap(Map map){
        entLocal.set(map);
    }
    @SuppressWarnings("rawtypes")
    public static Map getEntMap(){
        return (Map)entLocal.get();
    }
    
    public static void removeEnt() {   
        entLocal.remove();   
    } 
}
