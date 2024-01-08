package tomcat.utils;

public class WebUtils {

    public static int parseInt(String strNum,int defaultVal){
        try{
            return Integer.parseInt(strNum);
        }catch(NumberFormatException e){
            e.printStackTrace();
        }
        return defaultVal;
    }
}
