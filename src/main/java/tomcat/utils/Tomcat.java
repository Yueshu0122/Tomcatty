package tomcat.utils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        while(!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();

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

            String resp = respHeader + "HIII!";

            outputStream.write(resp.getBytes());

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            socket.close();
        }

    }
}
