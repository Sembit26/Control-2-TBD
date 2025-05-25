<template>
  <v-container class="fill-height d-flex justify-center align-center">
    <v-card class="pa-6" max-width="400" elevation="6">
      <v-card-title class="text-h5 text-center mb-4">Iniciar Sesión</v-card-title>

      <v-form @submit.prevent="login">
        <v-text-field
          v-model="correo"
          label="Correo"
          type="email"
          required
        />

        <v-text-field
          v-model="contrasena"
          label="Contraseña"
          type="password"
          required
        />

        <v-btn
          class="mt-4"
          color="primary"
          block
          type="submit"
          :loading="cargando"
        >
          Ingresar
        </v-btn>
      </v-form>

      <v-alert v-if="error" type="error" class="mt-4">
        {{ error }}
      </v-alert>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import usuarioService from '@/services/usuario.service.js';

const correo = ref('');
const contrasena = ref('');
const cargando = ref(false);
const error = ref(null);
const router = useRouter();

const login = async () => {
  try {
    cargando.value = true;
    error.value = null;

    const res = await usuarioService.login({ correo: correo.value, contrasena: contrasena.value });

    // Guardar datos si lo necesitas (ej: en localStorage o store)
    // localStorage.setItem('usuario', JSON.stringify(res.data));

    router.push('/tareas'); // Redirigir a la vista principal
  } catch (err) {
    error.value = 'Correo o contraseña incorrectos';
  } finally {
    cargando.value = false;
  }
};
</script>
