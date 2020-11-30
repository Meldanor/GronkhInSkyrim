<template>
  <div class="home">
    <h3 class="title is-3">
      Statistiken und nackte Fakten
    </h3>
    <series-data/>
    <weight-statistic/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import WeightStatistic from '@/components/WeightStatistic.vue';
import SeriesData from '@/components/SeriesData.vue';

export default defineComponent({
  name: 'Home',
  components: {
    SeriesData,
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
