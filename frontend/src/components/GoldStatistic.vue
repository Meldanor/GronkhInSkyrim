<template>
  <div id="player-gold-stats" style="width: 100%; height: 40rem">
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import embed, { VisualizationSpec } from 'vega-embed';

function spec(): VisualizationSpec {
  return {
    config: {
      background: '#333',
      title: { color: '#fff' },
      style: { 'guide-label': { fill: '#fff' }, 'guide-title': { fill: '#fff' } },
      axis: { domainColor: '#fff', gridColor: '#888', tickColor: '#fff' }
    },
    $schema: 'https://vega.github.io/schema/vega-lite/v4.json',
    width: 'container',
    height: 'container',
    title: 'Gold',
    selection: {
      grid: {
        type: 'interval',
        bind: 'scales',
        encodings: ['x']
      }
    },
    data: {
      url: '/gold.json'
    },
    transform: [
      { sample: 1000 },
      { calculate: 'datum.timestamp/3600', as: 'hour' }
    ],
    mark: { type: 'line', interpolate: 'step', color: 'goldenrod' },
    encoding: {
      x: {
        field: 'hour',
        type: 'quantitative',
        axis: {
          title: 'min. Spielzeit in Stunden',
          titleFontSize: 16,
          labelFontSize: 12
        },
        scale: {
          domainMin: 0
        }
      },
      y: {
        field: 'value',
        type: 'quantitative',
        axis: {
          title: 'Gold',
          titleFontSize: 16,
          labelFontSize: 12
        }
      }
    }
  };
}

export default defineComponent({
  name: 'GoldStatistics',
  setup() {
    embed('#player-gold-stats', spec(), { actions: true });
  },
});

</script>
