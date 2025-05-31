<template>
  <v-container class="mt-8">
    <h1 class="text-h5 font-weight-bold mb-6">Estadísticas Generales</h1>

    <v-row dense>
      <!-- 1 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>1. ¿Cuántas tareas hay por sector?</v-card-title>
          <v-card-text>
            <ul>
              <li v-for="s in tareasPorSector" :key="s.sector">
                {{ s.sector }}: {{ s.total }} tareas
              </li>
            </ul>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 2 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>2. Tarea pendiente más cercana</v-card-title>
          <v-card-text>
            {{ tareaCercana?.nombre || 'No encontrada' }}
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 3 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>3. Sector con más tareas completadas (radio 5 km)</v-card-title>
          <v-card-text>
            {{ sectorMasEnRadio?.sector || 'No disponible' }}
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 4 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>4. Promedio de distancia tareas completadas (usuario)</v-card-title>
          <v-card-text>
            {{ promedioUsuario?.toFixed(2) || '0' }} metros
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 5 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>5. Sectores con más tareas pendientes</v-card-title>
          <v-card-text>
            <ul>
              <li v-for="s in sectoresMasPendientes" :key="s.sector">
                {{ s.sector }}: {{ s.total }} pendientes
              </li>
            </ul>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 6 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>6. Tarea pendiente más cercana (geodistancia)</v-card-title>
          <v-card-text>
            {{ tareaGeo?.nombre || 'No encontrada' }}
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 7 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>7. Tareas por usuario y sector</v-card-title>
          <v-card-text>
            <ul>
              <li v-for="t in tareasPorUsuario" :key="`${t.usuario}-${t.sector}`">
                {{ t.usuario }} en {{ t.sector }}: {{ t.total }}
              </li>
            </ul>
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 8 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>8. Sector con más tareas completadas a 5 km</v-card-title>
          <v-card-text>
            {{ sectorMasCompletadas?.sector || 'No disponible' }}
          </v-card-text>
        </v-card>
      </v-col>

      <!-- 9 -->
      <v-col cols="12" md="6">
        <v-card class="mb-4">
          <v-card-title>9. Promedio de distancia de todas las tareas completadas</v-card-title>
          <v-card-text>
            {{ promedioGlobal?.toFixed(2) || '0' }} metros
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import tareaService from '@/services/tarea.service.js';

const usuarioId = localStorage.getItem("usuarioId");

const tareasPorSector = ref([]);
const tareaCercana = ref(null);
const sectorMasEnRadio = ref(null);
const promedioUsuario = ref(null);
const sectoresMasPendientes = ref([]);
const tareaGeo = ref(null);
const tareasPorUsuario = ref([]);
const sectorMasCompletadas = ref(null);
const promedioGlobal = ref(null);
const token = localStorage.getItem("jwt"); // Obtener el token del localStorage

onMounted(async () => {
  tareasPorSector.value = await tareaService.tareasPorSector(usuarioId, token).then(res => res.data);
  tareaCercana.value = await tareaService.tareaMasCercana(usuarioId, token).then(res => res.data);
  sectorMasEnRadio.value = await tareaService.sectorMasTareasEnRadio(usuarioId, token).then(res => res.data);
  promedioUsuario.value = await tareaService.promedioDistanciaCompletadas(usuarioId, token).then(res => res.data);
  sectoresMasPendientes.value = await tareaService.sectoresMasTareasPendientes(token).then(res => res.data);
  tareaGeo.value = await tareaService.tareaPendienteCercanaUbicacion(usuarioId, token).then(res => res.data);
  tareasPorUsuario.value = await tareaService.tareasPorUsuarioYSector(token).then(res => res.data);
  sectorMasCompletadas.value = await tareaService.sectorMasCompletadas5km(usuarioId, token).then(res => res.data);
  promedioGlobal.value = await tareaService.promedioDistanciaGlobal(usuarioId, token).then(res => res.data);
});
</script>
