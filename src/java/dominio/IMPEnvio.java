package dominio;

import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Envio;
import pojo.HistorialEnvio;
import pojo.Mensaje;

public class IMPEnvio {

    public static Mensaje registrarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.insert("envio.agregarEnvio", envio);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("envio agregado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al registrar el envio");
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Error al conectarse con la base de datos");
        }
        return respuesta;
    }

    public static Mensaje editarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.update("envio.editarEnvio", envio);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("envio editado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al editar el envio");
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Error al conectarse con la base de datos");
        }
        return respuesta;
    }

    public static Envio obtenerEnvio(Integer noGuia) {
        Envio envio = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            envio = conexion.selectOne("envio.obtenerEnvioGuia", noGuia);
        }
        return envio;
    }

    public static List<HistorialEnvio> obtenerHistorialStatus(Integer idEnvio) {
        List<HistorialEnvio> listaCambios = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            listaCambios = conexion.selectList("envio.obtenerHistorial", idEnvio);
        }
        return listaCambios;
    }

    public static List<Envio> obtenerEnvios() {
        List<Envio> envios = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            envios = conexion.selectList("envio.obtenerEnvios");
        }
        return envios;
    }

    public static List<Envio> obtenerEnviosColaborador(Integer noPersonal) {
        List<Envio> envios = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            envios = conexion.selectList("envio.obtenerEnvioColaborador", noPersonal);
        }
        return envios;
    }
}
