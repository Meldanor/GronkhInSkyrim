<template>
  <div class="home">
    <h3 class="title is-3">
      Statistiken und nackte Fakten
    </h3>
    <series-data />
    <weight-statistic style="margin-top: 2rem"/>
    <gold-statistic style="margin-top: 2.5rem"/>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import WeightStatistic from '@/components/WeightStatistic.vue';
import SeriesData from '@/components/SeriesData.vue';
import GoldStatistic from '@/components/GoldStatistic.vue';

export default defineComponent({
  name: 'Home',
  components: {
    GoldStatistic,
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
