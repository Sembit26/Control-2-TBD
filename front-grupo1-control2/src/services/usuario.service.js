import http from "../http-common";

const baseUrl = "/api/usuario";

// ----------- Autenticación -----------

const login = (usuario) => {
  return http.post(`${baseUrl}/login`, usuario);
};

const register = (usuarioDTO) => {
  return http.post(`${baseUrl}/register`, usuarioDTO);
};

// ----------- CRUD -----------

const getById = (id) => {
  return http.get(`${baseUrl}/getById/${id}`);
};

const getAll = () => {
  return http.get(`${baseUrl}/getAll`);
};

// Buscar cliente por correo
const buscarPorCorreo = (correo, token) => {
  return http.get(`${baseUrl}/buscar/correo?correo=${correo}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};

const update = (id, usuarioActualizado) => {
  return http.put(`${baseUrl}/update/${id}`, usuarioActualizado);
};

const eliminar = (id) => {
  return http.delete(`${baseUrl}/delete/${id}`);
};

export default {
  login,
  register,
  getById,
  getAll,
  buscarPorCorreo,
  update,
  eliminar,
};
