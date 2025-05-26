<template>
  <v-container>
    <h1 class="text-h4 font-weight-bold mb-4">Lista de Tareas</h1>

    <!-- Filtro -->
    <FiltroBusqueda @filtrar="recibirFiltros" />

    <!-- Lista de tareas -->
    <v-row>
      <v-col cols="12" md="6" v-for="tarea in tareas" :key="tarea.id">
      <TareaCard
        :tarea="tarea"
        @verDetalle="verDetalle"
        @onCompletar="marcarCompletada"
        @onEliminar="eliminarTarea"
      />

      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import tareaService from '@/services/tarea.service.js';
import FiltroBusqueda from './FiltroBusqueda.vue';
import TareaCard from './TareaCard.vue';

const tareas = ref([]);
const estado = ref(null);
const palabraClave = ref('');
const router = useRouter();

// Obtener tareas según filtros
const buscarTareas = async () => {
  try {
    const response = await tareaService.filtrarTareas(estado.value, palabraClave.value);
    tareas.value = response.data;
  } catch (error) {
    console.error('Error al filtrar tareas:', error);
  }
};

// Recibir filtros desde el componente hijo
const recibirFiltros = ({ estado: nuevoEstado, palabra }) => {
  estado.value = nuevoEstado;
  palabraClave.value = palabra;
  buscarTareas();
};

// Acciones
const marcarCompletada = async (id) => {
  try {
    await tareaService.marcarTareaComoCompletada(id);
    buscarTareas();
  } catch (err) {
    console.error('Error al marcar tarea:', err);
  }
};

const eliminarTarea = async (id) => {
  try {
    await tareaService.eliminarTarea(id);
    buscarTareas();
  } catch (err) {
    console.error('Error al eliminar tarea:', err);
  }
};

const verDetalle = (tarea) => {
  router.push(`/tareas/detalle/${tarea.id}`);
};

onMounted(() => {
  buscarTareas();
});
</script>
