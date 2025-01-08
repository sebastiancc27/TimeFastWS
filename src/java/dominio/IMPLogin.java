package dominio;

import cifrado.Cifrado;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.LoginColaborador;

public class IMPLogin {
    public static LoginColaborador loginColaborador(String noPersonal, String contrasena){
        LoginColaborador respuesta = new LoginColaborador();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        String contrasenaCifrada=Cifrado.ecnode(contrasena);       
        HashMap<String, String> parametros= new LinkedHashMap<>();                
         parametros.put("noPersonal",noPersonal);
         parametros.put("contrasena", contrasenaCifrada);
         System.out.println("Contraseña cifrada: "+contrasenaCifrada);
         System.out.println("CONTRASENA DESCIFRADA: "+Cifrado.deecnode("koqgakXquFsQCC7No/LbPw=="));
        if(conexion!=null){
            //ESTE COLABORADOR TIENE LA CONTRASEÑA CIFRADA
            Colaborador colaborador = conexion.selectOne("sesion.loginColaborador", parametros);
            if(colaborador!=null){
                String contrasenaDesifrada=Cifrado.deecnode(colaborador.getContrasena());                  
                colaborador.setContrasena(contrasenaDesifrada);
                String token = crearWebToken(colaborador);
                respuesta.setError(false);
                respuesta.setMensaje("Inicio de Seison Exitoso");
                respuesta.setColaborador(colaborador);
                respuesta.setToken(token);
            }else{
                respuesta.setError(true);
                respuesta.setMensaje("Error en autenticación");
            }
        }else{
                respuesta.setError(true);
                respuesta.setMensaje("Error en la conexion");
        }        
        return respuesta;
    }
    
    private static  String crearWebToken(Colaborador colaborador){
       String key = "MrChampions";
       long tiempoToken = System.currentTimeMillis();//TIEMPO DEL SISTEMA
         String jwt = Jwts.builder()
           .signWith(SignatureAlgorithm.HS256, key)
           .setSubject(colaborador.getNombre())
           .setIssuedAt(new Date(tiempoToken))
           .setExpiration(new Date(tiempoToken+100))
           .claim("correo", colaborador.getCorreo())
           .compact();
         return jwt;
    }
    
        public static LoginColaborador loginConductor(String noPersonal, String contrasena){
        LoginColaborador respuesta = new LoginColaborador();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        String contrasenaCifrada=Cifrado.ecnode(contrasena);       
        HashMap<String, String> parametros= new LinkedHashMap<>();                
         parametros.put("noPersonal",noPersonal);
         parametros.put("contrasena", contrasenaCifrada);
         System.out.println("Contraseña cifrada: "+contrasenaCifrada);
         System.out.println("CONTRASENA DESCIFRADA: "+Cifrado.deecnode("koqgakXquFsQCC7No/LbPw=="));
        if(conexion!=null){
            //ESTE COLABORADOR TIENE LA CONTRASEÑA CIFRADA
            Colaborador colaborador = conexion.selectOne("sesion.loginConductor", parametros);
            if(colaborador!=null){
                String contrasenaDesifrada=Cifrado.deecnode(colaborador.getContrasena());                  
                colaborador.setContrasena(contrasenaDesifrada);
                String token = crearWebToken(colaborador);
                respuesta.setError(false);
                respuesta.setMensaje("Inicio de Seison Exitoso");
                respuesta.setColaborador(colaborador);
                respuesta.setToken(token);
            }else{
                respuesta.setError(true);
                respuesta.setMensaje("Error en autenticación");
            }
        }else{
                respuesta.setError(true);
                respuesta.setMensaje("Error en la conexion");
        }        
        return respuesta;
    }
}
