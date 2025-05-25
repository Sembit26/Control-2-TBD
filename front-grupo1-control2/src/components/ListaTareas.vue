<template>
  <v-container>
    <h1 class="text-h4 font-weight-bold mb-4">Lista de Tareas</h1>

    <!-- Componente de Filtros -->
    <FiltroBusqueda @filtrar="recibirFiltros" />

    <!-- Lista de tareas -->
    <v-row>
      <v-col cols="12" md="6" v-for="tarea in tareas" :key="tarea.id">
        <!-- <TareaCard :tarea="tarea" /> -->
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import tareaService from '@/services/tarea.service.js';
import FiltroBusqueda from './FiltroBusqueda.vue';
//import TareaCard from './TareaCard.vue';

const tareas = ref([]);
const estado = ref(null);
const palabraClave = ref('');

// Llamado al backend
const buscarTareas = async () => {
  try {
    const response = await tareaService.filtrar(estado.value, palabraClave.value);
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

onMounted(() => {
  buscarTareas();
});
</script>
