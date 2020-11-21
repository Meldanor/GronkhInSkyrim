<template>
  <div class="home">
    <h2>
      Statistiken und nackte Fakten
    </h2>
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
