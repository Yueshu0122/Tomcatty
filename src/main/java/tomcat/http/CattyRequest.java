package tomcat.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CattyRequest {

    private String method;
    private String uri;

    InputStream inputStream = null;

    private HashMap<String,String> parametersMapping =
            new HashMap<String, String>();

    public CattyRequest(InputStream inputStream){
        this.inputStream = inputStream;

        try{
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(inputStream,"utf-8"));

            String requestLine = bufferedReader.readLine();

            String[] requestLineArr = requestLine.split(" ");

            method = requestLineArr[0];

            int index = requestLineArr[1].indexOf("?");
            if(index == -1){
                uri = requestLineArr[1];
            }else {
                uri = requestLineArr[1].substring(0,index);

                String parameters = requestLineArr[1].substring(index+1);

                String[] parametersPair = parameters.split("&");
                if(null != parametersPair && !"".equals(parameters)){
                    for(String parameterPair : parametersPair){
                        String[] parameterVal = parameterPair.split("=");
                        if(parameterVal.length == 2){
                            parametersMapping.put(parameterVal[0],parameterVal[1]);
                        }
                    }
                }
            }


        }catch(Exception e){


        }

    }

    public String getParameter(String name){
        if(parametersMapping.containsKey(name)){
            return parametersMapping.get(name);
        }else{
            return "";
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
