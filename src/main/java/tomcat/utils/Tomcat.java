package tomcat.utils;

import tomcat.handler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Tomcat {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);

        while(!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();

            RequestHandler handler = new RequestHandler(socket);

            new Thread(handler).start();

        }

    }
}
