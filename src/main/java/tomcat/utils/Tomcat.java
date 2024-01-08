package tomcat.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import tomcat.handler.RequestHandler;
import tomcat.servlet.CattyHttpServelt;

import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Tomcat {

    public static final HashMap<String, CattyHttpServelt> servletMapping =
            new HashMap<String, CattyHttpServelt>();

    public static final HashMap<String,String> servletUrlMapping =
            new HashMap<String, String>();

    public void init(){
        String path = Tomcat.class.getResource("/").getPath();
        SAXReader saxReader = new SAXReader();
        try{
            Document document = saxReader.read(new File(path+"web.xml"));
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();
            for(Element element : elements){
                if("servlet".equalsIgnoreCase(element.getName())){
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    servletMapping.put(servletName.getText(),(CattyHttpServelt) Class.forName(servletClass.getText().trim()).newInstance());
                }else if("servlet-mapping".equalsIgnoreCase(element.getName())){
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUrlMapping.put(urlPattern.getText(),servletName.getText());

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) throws IOException {
//
//        ServerSocket serverSocket = new ServerSocket(8080);
//
//        while(!serverSocket.isClosed()){
//            Socket socket = serverSocket.accept();
//
//            RequestHandler handler = new RequestHandler(socket);
//
//            new Thread(handler).start();
//
//        }
//
//    }
    public static void main(String[] args) {
        Tomcat tomcat = new Tomcat();
        tomcat.init();
        tomcat.runTomcat();
    }

    public void runTomcat(){
        try{
            ServerSocket serverSocket = new ServerSocket(8080);

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();

                RequestHandler handler = new RequestHandler(socket);

                new Thread(handler).start();

            }
        }catch (Exception e){

        }

    }
}
