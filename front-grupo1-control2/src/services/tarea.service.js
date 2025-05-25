import http from "../http-common";

const baseUrl = "/api/tareas";

// ----------- CRUD -----------

const crearTarea = (tarea) => http.post(`${baseUrl}/create`, tarea);

const editarTarea = (id, tareaActualizada) => http.put(`${baseUrl}/update/${id}`, tareaActualizada);

const eliminarTarea = (id) => http.delete(`${baseUrl}/${id}`);

const marcarTareaComoCompletada = (id) => http.patch(`${baseUrl}/marcarTareaComoCompletada/${id}`);

// ----------- Obtener tareas por relaciones -----------

const getTareasByUsuario = (usuarioId) => http.get(`${baseUrl}/getTareasByUsuario/${usuarioId}`);

const getTareasBySector = (sectorId) => http.get(`${baseUrl}/getTareasBySector/${sectorId}`);

// ----------- Filtros simples -----------

const getTareasCompletadas = () => http.get(`${baseUrl}/getTareasCompletadas`);

const getTareasPendientes = () => http.get(`${baseUrl}/getTareasPendientes`);

const buscarPorPalabraClave = (palabra) =>
  http.get(`${baseUrl}/buscarPorPalabraClave`, { params: { palabra } });

const filtrarTareas = (completada, palabraClave) =>
  http.get(`${baseUrl}/filtrar`, { params: { completada, palabraClave } });

// ----------- Filtros paginados -----------

const filtrarTareasPaginado = (completada, palabraClave, page = 0, size = 10) =>
  http.get(`${baseUrl}/filtrarPaginado`, { params: { completada, palabraClave, page, size } });

const getTareasByUsuarioPaginado = (usuarioId, page = 0, size = 10) =>
  http.get(`${baseUrl}/getTareasByUsuarioPaginado/${usuarioId}`, { params: { page, size } });

const getTareasBySectorPaginado = (sectorId, page = 0, size = 10) =>
  http.get(`${baseUrl}/getTareasBySectorPaginado/${sectorId}`, { params: { page, size } });

const getTareasCompletadasPaginado = (page = 0, size = 10) =>
  http.get(`${baseUrl}/getTareasCompletadasPaginado`, { params: { page, size } });

const getTareasPendientesPaginado = (page = 0, size = 10) =>
  http.get(`${baseUrl}/getTareasPendientesPaginado`, { params: { page, size } });

const buscarPorPalabraClavePaginado = (palabra, page = 0, size = 10) =>
  http.get(`${baseUrl}/buscarPorPalabraClavePaginado`, { params: { palabra, page, size } });

// ----------- PREGUNTAS estadísticas -----------

const tareasPorSector = (usuarioId) =>
  http.get(`${baseUrl}/tareasPorSector/${usuarioId}`);

const tareaMasCercana = (usuarioId) =>
  http.get(`${baseUrl}/tareaMasCercana/${usuarioId}`);

const sectorMasTareasEnRadio = (usuarioId) =>
  http.get(`${baseUrl}/sectorMasTareasEnRadio/${usuarioId}`);

const promedioDistanciaCompletadas = (usuarioId) =>
  http.get(`${baseUrl}/promedioDistanciaCompletadas/${usuarioId}`);

const sectoresMasTareasPendientes = () =>
  http.get(`${baseUrl}/sectoresMasTareasPendientes`);

const tareaPendienteCercanaUbicacion = (usuarioId) =>
  http.get(`${baseUrl}/tareaPendienteCercanaUbicacion/${usuarioId}`);

const tareasPorUsuarioYSector = () =>
  http.get(`${baseUrl}/tareasPorUsuarioYSector`);

const sectorMasCompletadas5km = (usuarioId) =>
  http.get(`${baseUrl}/sectorMasTareasCompletadas5km/${usuarioId}`);

const promedioDistanciaGlobal = (usuarioId) =>
  http.get(`${baseUrl}/promedioDistanciaGlobalCompletadas/${usuarioId}`);

// ----------- Exportar -----------

export default {
  crearTarea,
  editarTarea,
  eliminarTarea,
  marcarTareaComoCompletada,
  getTareasByUsuario,
  getTareasBySector,
  getTareasCompletadas,
  getTareasPendientes,
  buscarPorPalabraClave,
  filtrarTareas,
  filtrarTareasPaginado,
  getTareasByUsuarioPaginado,
  getTareasBySectorPaginado,
  getTareasCompletadasPaginado,
  getTareasPendientesPaginado,
  buscarPorPalabraClavePaginado,
  tareasPorSector,
  tareaMasCercana,
  sectorMasTareasEnRadio,
  promedioDistanciaCompletadas,
  sectoresMasTareasPendientes,
  tareaPendienteCercanaUbicacion,
  tareasPorUsuarioYSector,
  sectorMasCompletadas5km,
  promedioDistanciaGlobal
};
