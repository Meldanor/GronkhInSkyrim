<template>
  <div class="home">
    <h3 class="title is-3">
      Statistiken und nackte Fakten
    </h3>
    <h4 class="subtitle is-4">Gewicht</h4>
    <weight-statistic/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import WeightStatistic from '@/components/WeightStatistic.vue';

export default defineComponent({
  name: 'Home',
  components: {
    WeightStatistic
  },
});
</script>

<style lang="scss">
.home {
  margin-left: 2rem;
  margin-right: 2rem;
}
</style>
