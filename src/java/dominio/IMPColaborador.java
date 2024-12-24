package dominio;

import cifrado.Cifrado;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.LoginColaborador;
import pojo.Mensaje;

public class IMPColaborador {

    public static Mensaje registrarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();

        //CIFRADO DE CONTRASEÑA
        Colaborador nuevoColaborador = colaborador;
        String contrasenaColaborador = nuevoColaborador.getContrasena();
        String contrasenaCifrada = Cifrado.ecnode(contrasenaColaborador.trim());
        nuevoColaborador.setContrasena(contrasenaCifrada);

        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.insert("colaborador.colaborador-registrar", nuevoColaborador);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador agregado correctamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al registrar el colaborador");
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Error al establecer conexion en registrar colaborador");
        }
        return respuesta;
    }

    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
        //CIFRADO DE CONTRASEÑA
        Colaborador nuevoColaborador = colaborador;
        String contrasenaColaborador = nuevoColaborador.getContrasena();
        String contrasenaCifrada = Cifrado.ecnode(contrasenaColaborador.trim());
        nuevoColaborador.setContrasena(contrasenaCifrada);

        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.update("colaborador.colaboradorActualizar", nuevoColaborador);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador editado exitosamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Error al editar el colaborador");
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Error al establecer conexion la base de datos");
        }
        return respuesta;
    }

    public static Mensaje eliminarMensaje(Integer idColaborador) {
        Mensaje respuesta = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            int resultado = conexion.delete("colaborador.colaboradorEliminar", idColaborador);
            conexion.commit();
            if (resultado > 0) {
                respuesta.setError(false);
                respuesta.setMensaje("Colaborador Elminado correctamente");
            } else {
                respuesta.setError(false);
                respuesta.setMensaje("Error al elminar el colaborador");
            }
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al establecer conexion con la base de datos");
        }
        return respuesta;
    }

    public static List<Colaborador> buscarColaborador(String busqueda) {
        List<Colaborador> colaboradores = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        HashMap<String, String> parametros = new LinkedHashMap<>();
        parametros.put("nombre", busqueda);
        parametros.put("noPersonal", busqueda);
        parametros.put("idRol", busqueda);
        if (conexion != null) {
            colaboradores = conexion.selectList("colaborador.colaboradorBuscar", parametros);

        }

        return colaboradores;
    }

    public static List<Colaborador> obtenerColaboradores() {
        List<Colaborador> colaboradores = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            colaboradores = conexion.selectList("colaborador.obtenerColaboradores");
        }
        return colaboradores;
    }

    public static List<Colaborador> obtenerConductores() {
        List<Colaborador> conductores = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            conductores = conexion.selectList("colaborador.obtenerConductores");
        }
        return conductores;
    }

    public static Mensaje registrarFoto(Integer idColaborador, byte[] foto) {
        Mensaje msj = new Mensaje();
        SqlSession conexion = MybatisUtil.obtenerConexion();
        LinkedHashMap<String, Object> parametros = new LinkedHashMap<>();
        parametros.put("idColaborador", idColaborador);
        parametros.put("foto", foto);

        if (conexion != null) {
            try {
                int filasAfectadas = conexion.update("colaborador.guardarFoto", parametros);
                conexion.commit();
                if (filasAfectadas > 0) {
                    msj.setError(false);
                    msj.setMensaje("Imagen del cliente guardada con éxito");
                } else {
                    msj.setError(true);
                    msj.setMensaje("Error al guardar la imagen del cliente");
                }
            } catch (Exception e) {
                msj.setError(false);
                msj.setMensaje("Error excepcion : " + e.toString());
            }
        } else {
            msj.setError(false);
            msj.setMensaje("Error en la conexion con la base de datos");
        }
        return msj;
    }

    public static Colaborador obtenerFoto(Integer idColaborador) {
        Colaborador cliente = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                cliente = conexion.selectOne("colaborador.obtenerFoto", idColaborador);
            } catch (Exception e) {
                e.printStackTrace();
                cliente = null;
            }

        }
        return cliente;
    }

    public static Colaborador obtenerColaboradorID(Integer idColaborador) {
        Colaborador colaborador = null;
        SqlSession conexion = MybatisUtil.obtenerConexion();
        if (conexion != null) {
            try {
                colaborador = conexion.selectOne("colaborador.obtenerColaboradorID", idColaborador);
                String contrasenaColaborador = colaborador.getContrasena();
                String contrasenaDescifrada = Cifrado.deecnode(contrasenaColaborador);
                colaborador.setContrasena(contrasenaDescifrada);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return colaborador;
    }
}
