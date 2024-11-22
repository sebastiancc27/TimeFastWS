/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybatis;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * org.apache
 * @author Hp
 */
public class MybatisUtil {
    public static String RESOURCE="mybatis/mybatis-config.xml";  
    public static String ENVIRONMENT="development";
    
    public static SqlSession obtenerConexion(){
        SqlSession conexion = null;
        try {
            Reader reader = Resources.getResourceAsReader(RESOURCE); //SE LE ESPECIFICA EL RECURSO DE CONFIGURACION
            SqlSessionFactory abrirSession=new SqlSessionFactoryBuilder().build(reader);
            
            conexion = abrirSession.openSession();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }
}
