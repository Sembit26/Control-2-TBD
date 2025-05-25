import http from "../http-common";

const baseUrl = "/api/sector";

const crearSector = (sector) => {
  return http.post(`${baseUrl}/create`, sector);
};

const obtenerTodos = () => {
  return http.get(`${baseUrl}/getAll`);
};

const obtenerPorId = (id) => {
  return http.get(`${baseUrl}/getById/${id}`);
};

const actualizarSector = (id, sectorActualizado) => {
  return http.put(`${baseUrl}/${id}`, sectorActualizado);
};

const eliminarSector = (id) => {
  return http.delete(`${baseUrl}/${id}`);
};

export default {
  crearSector,
  obtenerTodos,
  obtenerPorId,
  actualizarSector,
  eliminarSector,
};
