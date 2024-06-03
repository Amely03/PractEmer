package com.emergentes.dao;

import com.emergentes.util.ConexionBD;
import com.emergentes.modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProductoDAOimpl extends ConexionBD implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {

        try {
            this.conectar();
            String sql = "INSERT INTO productos(nombre,descripcion,pecio)values(?,?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());

            ps.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();

        }

    }

    @Override
    public void update(Producto producto) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE productos SET nombre=?,descripcion=?,pecio=? WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getId());
            ps.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();

        }

    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE  FROM productos WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();

        }

    }

    public Producto getById(int id) throws Exception {
        Producto pro = new Producto();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM productos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setNombre(rs.getString("nombre"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setPrecio(rs.getFloat("precio"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> lista = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Producto>();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getFloat("precio"));
                lista.add(p);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
}
