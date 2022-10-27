/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa.Datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import Capa.Datos.Conexion;
import Capa.Dominio.AfiliadosDAO;



/**
 *
 * @author TagoKG
 */
public class Afiliados extends Conexion {
      private static final String sql_select = "SELECT Numero_CuentaBancaria, Moneda_Cuenta, CuentaHabiente_Cuenta,Banco_Cuenta,Saldo_Cuenta FROM CuentaBancaria";
    private static final String sql_insert = "INSERT INTO CuentaBancaria(Numero_CuentaBancaria, Moneda_Cuenta, CuentaHabiente_Cuenta,Banco_Cuenta,Saldo_Cuenta) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE CuentaBancaria SET Numero_CuentaBancaria=?, Moneda_Cuenta=?, CuentaHabiente_Cuenta=?,Banco_Cuenta=?,Saldo_Cuenta=?, WHERE Numero_CuentaBancaria = ?";
    private static final String sql_delete = "DELETE FROM CuentaBancaria WHERE Numero_CuentaBancaria=?";
    private static final String sql_query = "SELECT Numero_CuentaBancaria, Moneda_Cuenta, CuentaHabiente_Cuenta,Banco_Cuenta FROM TipoTransaccion WHERE CuentaBancaria=?";
 
    public boolean registrar(AfiliadosDAO usr) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO afiliados (id, nombre, dpi, direccion, telefono) VALUES(?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getId());
            ps.setString(2, usr.getNombre());
            ps.setString(3, usr.getDpi());
            ps.setString(4, usr.getDireccion());
            ps.setString(5, usr.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean login(Usuarios usr) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT ID, usuario, password, nombre, idTipo FROM usuarios WHERE usuario = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getPassword().equals(rs.getString(3))) {
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setIdTipo(rs.getInt(5));
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public int existeUsuario(String usuario) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();

    }

}
