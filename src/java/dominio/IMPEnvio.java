/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.Mensaje;

/**
 *
 * @author sebas
 */
public class IMPEnvio {
    public static Mensaje registrarEnvio (Envio envio){
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if(conexion!=null){
            int resultado = conexion.insert("envio.agregarEnvio", envio);
            conexion.commit();
            if(resultado>0){
                respuesta.setError(false);
                respuesta.setMensaje("envio agregado correctamente");
            }else{
                respuesta.setError(true);
               respuesta.setMensaje("Error al registrar el envio");   
            }
        }else{
                respuesta.setError(true);
               respuesta.setMensaje("Error al conectarse con la base de datos");             
        }
        return respuesta;
    }
    
    public static Mensaje editarEnvio(Envio envio){
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if(conexion!=null){
            int resultado = conexion.update("envio.editarEnvio",envio);
            conexion.commit();
            if(resultado>0){
                respuesta.setError(false);
                respuesta.setMensaje("envio editado correctamente");
            }else{
                respuesta.setError(true);
               respuesta.setMensaje("Error al editar el envio");   
            }            
        }else{
                respuesta.setError(true);
               respuesta.setMensaje("Error al conectarse con la base de datos");               
        }
        return respuesta;
    }
}
