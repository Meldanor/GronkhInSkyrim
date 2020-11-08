<template>
  <div id="player-weight-stats" style="width: 100%; height: 40rem">
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import embed, { VisualizationSpec } from 'vega-embed';
import weightData from './output.json';

function spec(data: any): VisualizationSpec {
  return {
    $schema: 'https://vega.github.io/schema/vega-lite/v4.json',
    width: 'container',
    height: 'container',
    data: {
      values: data,
    },
    mark: { type: 'line', interpolate: 'step' },
    encoding: {
      x: {
        field: 'timestamp',
        type: 'quantitative',
        axis: {
          title: 'Zeit',
          labelAngle: 0,
          labelFontSize: 16,
        },
      },
      y: {
        field: 'value',
        type: 'quantitative',
        axis: {
          title: 'Gewicht',
          labelAngle: 0,
        },
        scale: {
          type: 'sqrt',
        },
      },
      color: { field: 'type', type: 'nominal' },
    },
  };
}

export default defineComponent({
  name: 'WeightStatistics',
  setup(props) {
    embed('#player-weight-stats', spec(weightData as any), { actions: true });
  },
});

</script>
