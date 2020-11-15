<template>
  <div id="player-weight-stats" style="width: 100vw; height: 40rem">
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import embed, { VisualizationSpec } from 'vega-embed';

function spec(): VisualizationSpec {
  return {
    $schema: 'https://vega.github.io/schema/vega-lite/v4.json',
    width: 'container',
    height: 'container',
    data: {
      url: 'https://gist.githubusercontent.com/Meldanor/c7ffa26de69c5854f2b580e639ecc9b1/raw/3e7eaf1da853e6bdc3c584fe393c52e096d10d37/output.json'
    },
    selection: {
      grid: {
        type: 'interval',
        bind: 'scales',
        encodings: ['x']
      }
    },
    transform: [
      { sample: 2000 }
    ],
    mark: { type: 'line', interpolate: 'step' },
    encoding: {
      x: {
        field: 'time',
        type: 'quantitative',
        axis: {
          title: 'Zeit',
          labelAngle: 0,
          labelFontSize: 16
        },
        scale: {
          domainMin: 0
        }

      },
      y: {
        field: 'value',
        type: 'quantitative'
      },
      color: {
        field: 'type'
      },
      tooltip: [
        { field: 'value', type: 'quantitative', title: 'Wert' },
        { field: 'type', type: 'nominal', title: 'Typ' }
      ]
    }
  };
}

export default defineComponent({
  name: 'WeightStatistics',
  setup(props) {
    embed('#player-weight-stats', spec(), { actions: true });
  },
});

</script>
