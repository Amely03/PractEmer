package com.emergentes.dao;

import com.emergentes.util.ConexionBD;
import com.emergentes.modelo.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOimpl extends ConexionBD implements VentaDAO {

    @Override
    public void insert(Venta venta) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO ventas (producto_id, cliente_id, fecha) VALUES (?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al insertar venta: " + e.getMessage());
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Venta venta) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE ventas SET producto_id=?, cliente_id=?, fecha=? WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, venta.getProducto_id());
            ps.setInt(2, venta.getCliente_id());
            ps.setDate(3, venta.getFecha());
            ps.setInt(4, venta.getId());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al actualizar venta: " + e.getMessage());
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE FROM ventas WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Error al eliminar venta: " + e.getMessage());
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Venta getById(int id) throws Exception {
        Venta v = new Venta();
        try {
            this.conectar();
            String sql = "SELECT * FROM ventas WHERE id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setId(rs.getInt("id"));
                v.setProducto_id(rs.getInt("producto_id"));
                v.setCliente_id(rs.getInt("cliente_id"));
                v.setFecha(rs.getDate("fecha"));
            }
        } catch (Exception e) {
            throw new Exception("Error al obtener venta por ID: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        return v;
    }

    @Override
    public List<Venta> getAll() throws Exception {
         List<Venta> lista = new ArrayList<>();
    try {
        this.conectar();
        String sql = "SELECT v.*, p.nombre AS producto, c.nombre AS cliente FROM ventas v ";
        sql += "LEFT JOIN productos p ON v.producto_id = p.id ";
        sql += "LEFT JOIN clientes c ON v.cliente_id = c.id";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getInt("id"));
            v.setProducto_id(rs.getInt("producto_id"));
            v.setCliente_id(rs.getInt("cliente_id"));
            v.setFecha(rs.getDate("fecha"));
            v.setCliente(rs.getString("cliente"));
            v.setProducto(rs.getString("producto"));
            lista.add(v);
        }
    } catch (Exception e) {
        throw new Exception("Error al obtener todas las ventas: " + e.getMessage());
    } finally {
        this.desconectar();
    }
    return lista;
    }
}

