/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Rol;

/**
 *
 * @author sebas
 */
public class IMPRol {
    public static List<Rol> obteneRoles(){
     List<Rol> roles = null;
     SqlSession conexion = MybatisUtil.obtenerConexion();
     if(conexion!=null){
         roles = conexion.selectList("rol.obtenerRoles");
     }
     return roles;
    }
}
