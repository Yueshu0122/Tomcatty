package tomcat.handler;

import tomcat.http.CattyRequest;
import tomcat.http.CattyResponse;
import tomcat.servlet.CattyHttpServelt;
import tomcat.servlet.CattyRequestServlet;
import tomcat.utils.Tomcat;

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

//            BufferedReader bufferedReader = new BufferedReader
//                    (new InputStreamReader(inputStream,"utf-8"));
//            String mes = null;
//
//            while((mes = bufferedReader.readLine())!= null){
//                if(mes.length() == 0){
//                    break;
//                }
//                System.out.println(mes);
//            }

            CattyRequest cattyRequest = new CattyRequest(inputStream);
            //System.out.println(cattyRequest.getParameter("num1"));
            //System.out.println(cattyRequest.getParameter("num2"));

            CattyResponse cattyResponse = new CattyResponse(socket.getOutputStream());

           // CattyRequestServlet cattyRequestServlet = new CattyRequestServlet();
            //cattyRequestServlet.doGet(cattyRequest,cattyResponse);

            String uri = cattyRequest.getUri();
            String servletName = Tomcat.servletUrlMapping.get(uri);
            CattyHttpServelt cattyHttpServelt = Tomcat.servletMapping.get(servletName);
            if(cattyHttpServelt!=null){
                cattyHttpServelt.service(cattyRequest,cattyResponse);
            }else {
                String resp = cattyResponse.respHeader +"<h1>404 Not Found</h1>";
                OutputStream outputStream = cattyResponse.getOutputStream();
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();
            }


//            String resp = cattyResponse.respHeader +"<h1> Response <h1/>";
//
//            OutputStream outputStream = cattyResponse.getOutputStream();
//            outputStream.write(resp.getBytes());
//            outputStream.flush();
//            outputStream.close();

//            String respHeader = "HTTP/1.1 200 OK\r\n" +
//                    "Content-Type: text/html;charset=utf-8\r\n\r\n";
//
//            String resp = respHeader + "<h1>HIII!<h1/>";

//            outputStream.write(resp.getBytes());
//
//            outputStream.flush();
//            outputStream.close();
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
