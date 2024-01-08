package tomcat.servlet;

import tomcat.http.CattyRequest;
import tomcat.http.CattyResponse;
import tomcat.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;

public class CattyRequestServlet extends CattyHttpServelt{

    @Override
    public void doGet(CattyRequest cattyRequest, CattyResponse cattyResponse) {

        int num1 = WebUtils.parseInt(cattyRequest.getParameter("num1"),0);
        int num2 = WebUtils.parseInt(cattyRequest.getParameter("num2"),0);

        int sum = num1 + num2;

        OutputStream outputStream = cattyResponse.getOutputStream();
        String respMes = cattyResponse.respHeader + "<h1>"+num1+"+"+
                num2+"="+sum+"</h1>";
        try{
            outputStream.write(respMes.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(CattyRequest cattyRequest, CattyResponse cattyResponse) {

    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }
}
