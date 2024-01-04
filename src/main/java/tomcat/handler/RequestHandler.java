package tomcat.handler;

import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable{
    Socket socket = null;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try{
            InputStream inputStream = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(inputStream,"utf-8"));
            String mes = null;

            while((mes = bufferedReader.readLine())!= null){
                if(mes.length() == 0){
                    break;
                }
                System.out.println(mes);
            }

            OutputStream outputStream = socket.getOutputStream();

            String respHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html;charset=utf-8\r\n\r\n";

            String resp = respHeader + "<h1>HIII!<h1/>";

            outputStream.write(resp.getBytes());

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            socket.close();

        }catch (IOException e){
                e.printStackTrace();
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
