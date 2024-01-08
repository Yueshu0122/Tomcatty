package tomcat.servlet;

import tomcat.http.CattyRequest;
import tomcat.http.CattyResponse;

import java.io.IOException;

public interface CattyServlet {

    void init() throws Exception;

    void service(CattyRequest cattyRequest, CattyResponse cattyResponse) throws IOException;

    void destroy();
}
