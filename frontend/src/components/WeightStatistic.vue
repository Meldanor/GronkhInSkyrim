<template>
  <div id="player-weight-stats" style="width: 100%; height: 40rem">
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
    title: 'Gewicht',
    selection: {
      grid: {
        type: 'interval',
        bind: 'scales',
        encodings: ['x']
      }
    },
    data: {
      url: '/weights.json'
    },
    transform: [
      { sample: 1000 }
    ],
    mark: { type: 'line', interpolate: 'step' },
    encoding: {
      x: {
        field: 'timestamp',
        type: 'quantitative',
        axis: {
          title: 'Zeitstempel in Sekunden',
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
          title: 'Gewicht',
          titleFontSize: 16,
          labelFontSize: 12
        }
      },
      color: {
        field: 'type',
        legend: {
          labelExpr: "datum.value == 'cur' ? 'Aktuell' : 'Maximual'",
          title: '',
          labelFontSize: 16
        },
        scale: { range: ['#1f77b4', '#d62728'] }
      }
    }
  };
}

export default defineComponent({
  name: 'WeightStatistics',
  setup() {
    embed('#player-weight-stats', spec(), { actions: true });
  },
});

</script>
