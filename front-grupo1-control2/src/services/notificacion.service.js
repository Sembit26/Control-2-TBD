import http from "../http-common";

const baseUrl = "/api/notificaciones";

const crearNotificacion = (notificacion) => {
  return http.post(baseUrl, notificacion);
};

const obtenerTodas = () => {
  return http.get(baseUrl);
};

const obtenerPorId = (id) => {
  return http.get(`${baseUrl}/${id}`);
};

const eliminarNotificacion = (id) => {
  return http.delete(`${baseUrl}/${id}`);
};

const marcarComoLeida = (id) => {
  return http.put(`${baseUrl}/${id}/leer`);
};

const obtenerPorUsuario = (usuarioId) => {
  return http.get(`${baseUrl}/getNotificacionByUsuario/${usuarioId}`);
};

const generarNotificacionesProximas = () => {
  return http.post(`${baseUrl}/generarNotificaciones`);
};

export default {
  crearNotificacion,
  obtenerTodas,
  obtenerPorId,
  eliminarNotificacion,
  marcarComoLeida,
  obtenerPorUsuario,
  generarNotificacionesProximas
};
