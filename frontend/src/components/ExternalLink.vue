<template>
  <a :href=link target="_blank" rel="noopener noreferrer">{{ text }}</a>
</template>

<script>

import { defineComponent } from 'vue';

export default defineComponent({
  name: 'ExternalLink',
  props: {
    link: String,
    text: String
  }
});

</script>

<style >
a {
  text-decoration: underline
}
</style>
