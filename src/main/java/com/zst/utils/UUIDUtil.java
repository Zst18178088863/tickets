package com.zst.utils;

import java.util.UUID;

/**
 * @Description:
 * @Auther: 张胜通
 * @Date: 2023/2/4 14:0
 */
public class UUIDUtil {

    private UUIDUtil(){}

    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }



    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(UUIDUtil.getUuid());
        System.out.println(UUIDUtil.getUuid());
        System.out.println(UUIDUtil.getUuid());
        System.out.println(UUIDUtil.getUuid());
    }

}
