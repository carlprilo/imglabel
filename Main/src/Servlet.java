
import src.HdfsOpreate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "Servlet",urlPatterns = {"/hello"})
public class Servlet extends javax.servlet.http.HttpServlet {

   // static String ip = "hdfs://192.168.1.203:9000";
   // static String path = "/Output";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.print("got Post request");
        String type_s = request.getParameter("type");
        handlePost(type_s, request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.print("got Get request!\n");
        String type_s = request.getParameter("type");
        handleGet(type_s, request, response);
    }

    private boolean handleGet(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean result = false;
        String ip_path = request.getParameter("path");
        String ip = "hdfs://" + ip_path.split(":")[0] + ":9000";
        String path = ip_path.split(":")[1];
        HdfsOpreate hdfsOpreate = new HdfsOpreate(ip);
        switch (type) {
            case "readImage":
                hdfsOpreate.readFile(path, response);
                result = true;
                break;
            case "saveXml":
                hdfsOpreate.createFile(path, request.getParameter("xml_content"), response);
                result = true;
                break;
            case "readDir":
                hdfsOpreate.listFiles(path, response);
                result = true;
                break;
            case "readXml":
                hdfsOpreate.readXml(path, response);
                break;
            default:
                System.out.print("no match!");
                break;
        }

        return result;
    }

    private boolean handlePost(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return handleGet(type, request, response);
    }
}


