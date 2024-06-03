package com.emergentes.controlador;

import com.emergentes.util.ConexionBD;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String correo = request.getParameter("correo");
            String password = request.getParameter("password");
            ResultSet rs;
            String sql = "select *from usuarios where correo=? and password=?";

            ConexionBD canal = new ConexionBD();
            Connection cn = canal.conectar();

            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);

            rs = ps.executeQuery();
            if (rs.next()) {
                HttpSession ses = request.getSession();

                ses.setAttribute("logueado", "OK");
                ses.setAttribute("usuario", rs.getString("nombres") + " " + rs.getString("apellidos"));
                response.sendRedirect("ClienteControlador");

            } else {
                response.sendRedirect("Login.jsp");

            }
        } catch (SQLException ex) {
            System.out.println("error al conectar" + ex.getMessage());
        }

    }

}
