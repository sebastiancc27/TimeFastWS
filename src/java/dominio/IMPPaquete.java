package dominio;

import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Paquete;

public class IMPPaquete {
    public static Mensaje registrarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        
        try {
            if(conexion != null){
                int resultado = conexion.insert("paquete.registrarPaquete",paquete);
                conexion.commit();
                if(resultado > 0){
                    msj.setError(false);
                    msj.setMensaje("Paquete registrado correctamente");
                }else{
                    msj.setError(true);
                    msj.setMensaje("No se pudo registrar el paquete");
                }
            }else{
                msj.setError(true);
                msj.setMensaje("No se pudo establecer conexion");
            }
        }catch (Exception e) {
            msj.setError(true);
            msj.setMensaje("Hubo un error al agregar el paquete. Intente más tarde"+e.toString());
        }
        
        return msj;
    }
    
    public static Mensaje actualizarPaquete(Paquete paquete){
        Mensaje msj = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        
        try {
            if(conexion != null){
                int resultado = conexion.update("paquete.actualizarPaquete", paquete);
                conexion.commit();
                if(resultado > 0){
                    msj.setError(false);
                    msj.setMensaje("Paquete actualizado correctamente");
                }else{
                    msj.setError(true);
                    msj.setMensaje("No se pudo actualizar el paquete");
                }
            }else{
                msj.setError(true);
                msj.setMensaje("No se pudo establecer conexión. Intente más tarde");
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje("Ocurrió un error, intente más tarde. "+ e.toString());
        }
        
        return msj;
    }
    
    public static List<Paquete> obtenerPaquetePorEnvio(Integer envio){
        List<Paquete> paquetes = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        
        try {
            if(conexion != null){
                paquetes = conexion.selectList("paquete.seleccionarPorEnvio", envio);
            }else{
                System.out.println("Error al obtener la conexión");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los paquetes del envío "+envio+". "+e.toString());
        }
        
        return paquetes;
    }
    
    public static Mensaje eliminarPaquete(Integer idPaquete){
        Mensaje msj = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        
        try {
            if(conexion != null){
                int resultado = conexion.delete("paquete.eliminarPaquete", idPaquete);
                conexion.commit();
                if(resultado > 0){
                    msj.setError(false);
                    msj.setMensaje("Paquete eliminado con éxito");
                }else{
                    msj.setError(true);
                    msj.setMensaje("No se pudo eliminar el paquete");
                }
            }else{
                msj.setError(true);
                msj.setMensaje("No se pudo establecer conexión. Intente más tarde");
            }
        } catch (Exception e) {
            msj.setError(true);
            msj.setMensaje("Error al eliminar paquete. Intente más tarde. "+ e.toString());
        }
        
        return msj;
    }
}
