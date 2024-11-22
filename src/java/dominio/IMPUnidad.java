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
import pojo.Mensaje;
import pojo.Unidad;

/**
 *
 * @author sebas
 */
public class IMPUnidad {

    public static Mensaje registrarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.insert("unidad.registrarUnidad", unidad);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Unidad agregada correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al agregar la unidad");
            }
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al conectarse a la base de datos");
        }
        return respuesta;
    }

    public static Mensaje editarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.update("unidad.editarUnidad", unidad);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Unidad editada correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al editar la unidad");
            }
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al conectarse a la base de datos");
        }
        return respuesta;
    }

    public static Mensaje eliminarUnidad(Integer idUnidad) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.delete("unidad.eliminarUnidad", idUnidad);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Unidad elminada correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al elminar la unidad");
            }
        }
        return respuesta;
    }
    
    public static List<Unidad> buscarUnidad(String busqueda){
         List<Unidad> unidades = null;
         SqlSession conexion = MybatisUtil.obtenerConexion();
         HashMap<String, String> parametros= new LinkedHashMap<>();                
         parametros.put("vin",busqueda);
         parametros.put("marca", busqueda);
         parametros.put("nii", busqueda);
         
         if(conexion!=null){
             unidades = conexion.selectList("unidad.buscarUnidad", parametros);
         }
        return unidades;         
    }

}
