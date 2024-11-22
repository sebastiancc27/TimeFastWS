/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import cifrado.Cifrado;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.LoginColaborador;
import pojo.Mensaje;

/**
 *
 * @author sebas
 */
public class IMPColaborador {
    public static Mensaje registrarColaborador (Colaborador colaborador){       
        Mensaje respuesta = new Mensaje();
        
        //CIFRADO DE CONTRASEÑA
        Colaborador nuevoColaborador= colaborador;        
        String contrasenaColaborador = nuevoColaborador.getContrasena();
        String contrasenaCifrada = Cifrado.ecnode(contrasenaColaborador.trim());
        nuevoColaborador.setContrasena(contrasenaCifrada);
        
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if(conexion!=null){
            int resultado = conexion.insert("colaborador.colaborador-registrar",nuevoColaborador);
            conexion.commit();
            if(resultado>0){
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador agregado correctamente");
            }else{
                respuesta.setError(true);
               respuesta.setMensaje("Error al registrar el colaborador");   
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Error al establecer conexion en registrar colaborador");
        }
        return respuesta;
    }
    public static Mensaje editarColaborador(Colaborador colaborador){
        Mensaje respuesta = new Mensaje();
        //CIFRADO DE CONTRASEÑA
        Colaborador nuevoColaborador= colaborador;        
        String contrasenaColaborador = nuevoColaborador.getContrasena();
        String contrasenaCifrada = Cifrado.ecnode(contrasenaColaborador.trim());
        nuevoColaborador.setContrasena(contrasenaCifrada);
        
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if(conexion!=null){
            int resultado = conexion.update("colaborador.colaboradorActualizar", nuevoColaborador);
            conexion.commit();
            if(resultado > 0){
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador editado exitosamente");
            }else{
                respuesta.setError(true);
                respuesta.setMensaje("Error al editar el colaborador");                
            }
        }else{
                respuesta.setError(true);
                respuesta.setMensaje("Error al establecer conexion la base de datos");            
        }
        return respuesta;
    }
    
    public static Mensaje eliminarMensaje(Integer idColaborador){
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if(conexion !=null){
            int resultado = conexion.delete("colaborador.colaboradorEliminar", idColaborador);
            conexion.commit();
            if(resultado > 0){
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador Elminado correctamente");
            }else{
                respuesta.setError(false);
                respuesta.setMensaje("Error al elminar el colaborador");                
            }
        }else{
                respuesta.setError(false);
                respuesta.setMensaje("Error al establecer conexion con la base de datos");            
        }
        return respuesta;
    }
    public static List<Colaborador> buscarColaborador(String busqueda){
        List<Colaborador> colaboradores = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();    
        HashMap<String, String> parametros= new LinkedHashMap<>();                
         parametros.put("nombre",busqueda);
         parametros.put("noPersonal", busqueda);
         parametros.put("idRol", busqueda);
        if(conexion!=null){
            colaboradores = conexion.selectList("colaborador.colaboradorBuscar",parametros);
 
        }             
        
        return colaboradores;
    }
}
