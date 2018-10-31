package com.rifu.string;

/**
 * @author Rifu
 * @create 2018-06-11 23:07
 * 去除空格，多个空格之间只保留一个
 */
public class TrimSpaceDemo {

    public static void main(String [] args){
        String str="  i  am english;";
        String after=trim(str);
        System.out.println(" before: "+str+"-------length:"+str.length());
        System.out.println(" after: "+after+"-------length:"+after.length());
    }

    public static String trim(String src){
        if(src==null|| src.length()<=0){
            return null;
        }
        StringBuffer temp =new StringBuffer();
        for (int i=0;i<src.length();i++){
            char c = src.charAt(i);
            if(c==' '){
                if(i>1&&src.charAt(i-1)!=' '){
                    temp.append(c);
                }
            }else{
                temp.append(c);
            }
        }
        return temp.toString();
    }
}
