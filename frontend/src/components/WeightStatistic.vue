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
    layer: [{
      mark: { type: 'line', color: 'red', interpolate: 'step' },
      encoding: {
        y: { field: 'max', type: 'quantitative' },
      },
    }, {
      mark: { type: 'line', interpolate: 'step' },
      encoding: {
        y: { field: 'cur', type: 'quantitative' },
      },
    }, {
      mark: 'rule',
      selection: {
        hover: { type: 'single', on: 'mouseover', empty: 'none' },
      },
      encoding: {
        color: {
          condition: {
            selection: { not: 'hover' }, value: 'transparent',
          },
        },
      },
    }],
    mark: { type: 'line', interpolate: 'step' },
    encoding: {
      x: {
        field: 'time',
        type: 'quantitative',
        axis: {
          title: 'Zeit',
          labelAngle: 0,
          labelFontSize: 16,
        },
      },
      tooltip: [
        { field: 'time' },
        { field: 'cur', type: 'quantitative' },
        { field: 'max', type: 'quantitative' },
      ],
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
