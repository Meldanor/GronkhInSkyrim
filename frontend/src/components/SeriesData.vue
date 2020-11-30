<template>
  <div class="columns">
    <div class="column is-3 is-offset-1">
      <h4 class="subtitle is-4 has-text-centered">Spielzeit</h4>
      <h5 class="subtitle is-5">{{ playTime }}+ Stunden</h5>
    </div>
    <div class="column is-4">
      <h4 class="subtitle is-4 has-text-centered">Datengröße</h4>
      <h5 class="subtitle is-5">{{ fileSize }} GB</h5>
    </div>
    <div class="column is-3">
      <h4 class="subtitle is-4 has-text-centered">Letzte Folge</h4>
      <h5 class="subtitle is-5">{{ lastEpisode }}</h5>
    </div>
  </div>
</template>

<script>

import { defineComponent } from 'vue';
import metaData from '@/../public/series_meta.json';

export default defineComponent({
  name: 'SeriesData',
  data() {
    return {
      fileSize: 367
    };
  },
  setup() {
    const lastEpisodeMeta = metaData[metaData.length - 1];
    const lastEpisode = `${metaData.length} - ${lastEpisodeMeta.episodeName}`;

    let playTime = lastEpisodeMeta.timestamp;
    playTime = (playTime / 60 / 60).toFixed(0);

    return {
      lastEpisode,
      playTime
    };
  },
});

</script>

<style lang="scss">
</style>
