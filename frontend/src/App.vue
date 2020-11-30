<template>
  <h1 class="title is-1">
    <external-link link="https://www.twitch.tv/gronkh" text="Erik/Gronkh"/>
    in
    <external-link link="https://grnk.yt/skyrim" text="Skyrim"/>
  </h1>
  <div id="nav">
    <router-link to="/">Statistiken</router-link> |
    <router-link to="/about">Wat?/Warum?</router-link>
  </div>
  <router-view/>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import ExternalLink from '@/components/ExternalLink.vue';

export default defineComponent({
  name: 'App',
  components: {
    ExternalLink
  },
});
</script>

<style lang="scss">

h1 {
  margin-top: 2rem !important;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #1c1e10;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #1c1e10;
    text-decoration: underline;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
a:visited {
  color: unset;
}
a {
  text-decoration: underline;
  color: #2c2e20;
}
</style>
