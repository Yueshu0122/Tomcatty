package tomcat.servlet;

import tomcat.http.CattyRequest;
import tomcat.http.CattyResponse;

import java.io.IOException;

public abstract class CattyHttpServelt implements CattyServlet {

    @Override
    public void service(CattyRequest cattyRequest, CattyResponse cattyResponse) throws IOException {
        if("GET".equalsIgnoreCase(cattyRequest.getMethod())){
            this.doGet(cattyRequest,cattyResponse);
        }else if("POST".equalsIgnoreCase(cattyRequest.getMethod())){
            this.doPost(cattyRequest,cattyResponse);
        }
    }

    public abstract void doGet(CattyRequest cattyRequest,CattyResponse cattyResponse);

    public abstract void doPost(CattyRequest cattyRequest,CattyResponse cattyResponse);

}
