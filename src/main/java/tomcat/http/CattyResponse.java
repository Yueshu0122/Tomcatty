package tomcat.http;

import java.io.OutputStream;

public class CattyResponse {

    private OutputStream outputStream = null;

    public static final String respHeader = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html;charset=utf-8\r\n\r\n";


    public CattyResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream(){
        return outputStream;
    }
}
