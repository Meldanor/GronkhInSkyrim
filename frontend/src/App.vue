<template>
  <h1>
    <a href="https://www.twitch.tv/gronkh">Erik/Gronkh</a> in <a href="https://grnk.yt/skyrim">Skyrim</a>
  </h1>
  <div id="nav">
    <router-link to="/">Statistiken</router-link> |
    <router-link to="/about">Warum?</router-link>
  </div>
  <router-view/>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;

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
}
</style>
