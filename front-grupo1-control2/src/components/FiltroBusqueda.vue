<template>
  <v-row dense class="mb-4">
    <v-col cols="12" md="4">
      <v-select
        v-model="estadoLocal"
        :items="estados"
        label="Filtrar por estado"
        clearable
        @change="emitirFiltros"
      />
    </v-col>

    <v-col cols="12" md="8">
      <v-text-field
        v-model="palabraClaveLocal"
        label="Buscar por palabra clave"
        clearable
        @keyup.enter="emitirFiltros"
        @blur="emitirFiltros"
      />
    </v-col>
  </v-row>
</template>

<script setup>
import { ref, watch } from 'vue';

const emit = defineEmits(['filtrar']);

const estadoLocal = ref(null);
const palabraClaveLocal = ref('');

const estados = [
  { title: 'Pendiente', value: false },
  { title: 'Completada', value: true },
];

const emitirFiltros = () => {
  emit('filtrar', { estado: estadoLocal.value, palabra: palabraClaveLocal.value });
};
</script>
