/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.Mensaje;

/**
 *
 * @author sebas
 */
public class IMPCliente {

    public static Mensaje registrarCliente(Cliente cliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.insert("cliente.registrarCliente", cliente);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Cliente agregado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al agregar el cliente");
            }
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error en la conexion con la base de datos");
        }
        return respuesta;
    }

    public static Mensaje editarCliente(Cliente cliente) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.update("cliente.actualizarCliente", cliente);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Cliente editado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al editar el cliente");
            }
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error en la conexion con la base de datos");
        }
        return respuesta;
    }
    
    public static Mensaje eliminarCliente(Integer idCliente){
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if(conexion!=null){
            int resultado = conexion.delete("cliente.eliminarCliente", idCliente);
            conexion.commit();
             if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Cliente eliminado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al eliminar el cliente");
            }
        }else{
            respuesta.setError(false);
            respuesta.setMensaje("Error en la conexion con la base de datos");            
        }
        return respuesta;                
    }
    public static List<Cliente> buscarClientes(String busqueda){
        List<Cliente> clientes= null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        HashMap<String, String> parametros= new LinkedHashMap<>();                
         parametros.put("nombre",busqueda);
         parametros.put("telefono", busqueda);
         parametros.put("correo", busqueda);
        if(conexion!=null){
            clientes = conexion.selectList("cliente.buscarCliente", parametros);            
        }
        return clientes;                
    }
    
}
