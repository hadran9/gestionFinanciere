package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DepenseDao;
import model.Depense;

public class DepenseController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/depense.jsp";
    private static String LIST_DEPENSE = "/listDepense.jsp";
    private DepenseDao dao;

    public DepenseController() {
        super();
        dao = new DepenseDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        session.setAttribute("token", "wax654");

        if (action.equalsIgnoreCase("delete")) {
            int depenseid = Integer.parseInt(request.getParameter("depenseid"));
            dao.deleteDepense(depenseid);
            forward = LIST_DEPENSE;
            request.setAttribute("depenses", dao.getAllDepenses());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int depenseid = Integer.parseInt(request.getParameter("depenseid"));
            Depense depense = dao.getDepenseById(depenseid);
            request.setAttribute("depense", depense);
            request.setAttribute("title", "Edit");
        } else if (action.equalsIgnoreCase("listDepense")) {
            forward = LIST_DEPENSE;
            request.setAttribute("depenses", dao.getAllDepenses());
        } else {
            forward = INSERT_OR_EDIT;
            request.setAttribute("title", "Add");
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Depense depense = new Depense();
        depense.setDepense(Double.parseDouble(request.getParameter("depense")));
        depense.setDescription(request.getParameter("description"));
        try {
            Date date_depense = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("date_depense"));
            depense.setDate_depense(date_depense);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String depenseid = request.getParameter("depenseid");

        HttpSession session = request.getSession();
        String tokenFromSession = (String) session.getAttribute("token");
        String tokenFromRequest = (String) request.getParameter("token");

        if (tokenFromRequest.equals(tokenFromSession)) {
            if (depenseid == null || depenseid.isEmpty()) {
                dao.addDepense(depense);
            } else {
                depense.setId_depense(Integer.parseInt(depenseid));
                dao.updateDepense(depense);
            }
            session.setAttribute("token", null);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_DEPENSE);
        request.setAttribute("depenses", dao.getAllDepenses());
        view.forward(request, response);
    }
}
