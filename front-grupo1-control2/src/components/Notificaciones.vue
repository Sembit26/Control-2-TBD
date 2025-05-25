<template>
  <v-container class="mt-8">
    <h1 class="text-h5 font-weight-bold mb-4">Notificaciones</h1>

    <v-row>
      <v-col cols="12" md="6" v-for="notif in notificaciones" :key="notif.id">
        <v-card :color="notif.leida ? 'grey-lighten-4' : 'blue-lighten-5'" class="mb-4">
          <v-card-title class="text-subtitle-1">
            {{ notif.mensaje }}
          </v-card-title>
          <v-card-text>
            <p><strong>Tarea:</strong> {{ notif.tarea?.nombre || 'Desconocida' }}</p>
            <p><strong>Leída:</strong> {{ notif.leida ? 'Sí' : 'No' }}</p>
          </v-card-text>
          <v-card-actions>
            <v-btn
              v-if="!notif.leida"
              color="primary"
              @click="marcarComoLeida(notif.id)"
            >
              Marcar como leída
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>

    <v-alert v-if="notificaciones.length === 0" type="info">
      No hay notificaciones disponibles.
    </v-alert>
  </v-container>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import notificacionService from '@/services/notificacion.service.js';

const usuarioId = 1; // ⚠️ reemplazar luego con el ID del usuario logueado
const notificaciones = ref([]);

const cargarNotificaciones = async () => {
  try {
    const res = await notificacionService.obtenerPorUsuario(usuarioId);
    notificaciones.value = res.data;
  } catch (err) {
    console.error('Error al cargar notificaciones:', err);
  }
};

const marcarComoLeida = async (id) => {
  try {
    await notificacionService.marcarComoLeida(id);
    await cargarNotificaciones();
  } catch (err) {
    console.error('Error al marcar como leída:', err);
  }
};

onMounted(() => {
  cargarNotificaciones();
});
</script>
