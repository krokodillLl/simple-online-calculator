import Logics.MathOperation;
import Exception.DivisionByZero;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestServlet extends HttpServlet {

    private String strA;
    private String strB;
    private String strOperation;
    private String res;
    private double doubleA;
    private double doubleB;
    private double doubleRes;
    private boolean error;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        // Непосредственная работа с сервером
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.putIfAbsent("Result", 0);
        pageVariables.putIfAbsent("a", 0);
        pageVariables.putIfAbsent("b", 0);
        pageVariables.putIfAbsent("operation", 0);
        resp.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariables(req);
        resp.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private  Map<String, Object> createPageVariables(HttpServletRequest req){
        // Заполнение полей
        strA = req.getParameter("a");
        strB = req.getParameter("b");
        strOperation = req.getParameter("operation");

        try{
            doubleA = Double.parseDouble(strA);
            doubleB = Double.parseDouble(strB);
            error = false;
        }
        catch (Exception e) {
            error = true;
        }
        // Если все валидно, то логика
            if (!error) {
                if (strOperation.equals("+")) {
                    doubleRes = MathOperation.Sum(doubleA, doubleB);
                }
                if (strOperation.equals("-")) {
                    doubleRes = MathOperation.Sub(doubleA, doubleB);
                }
                if (strOperation.equals("*")) {
                    doubleRes = MathOperation.Mul(doubleA, doubleB);
                }
                if (strOperation.equals("/")) {
                    try {
                        doubleRes = MathOperation.Div(doubleA, doubleB);
                    } catch (ArithmeticException e) {
                        res = e.getMessage();
                        error = true;
                    }
                }
            }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("a", doubleA);
        pageVariables.put("b", doubleB);
        pageVariables.put("operation", strOperation);
        if(!error) {
            pageVariables.put("Result", doubleRes);
        }
        else {
            pageVariables.put("Result", res);
        }
        return pageVariables;
    }
}
