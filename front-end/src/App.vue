<template>
  <div id="app">
    <div id="nav">
      <a href="https://www.linkedin.com/in/evandro-souza/">Linkedin</a> |
      <a href="https://github.com/evandrosouza89 ">Github</a> |
      <a href="mailto:evandro.souza89@gmail.com">Email</a>
      <h6>© 2024 Evandro Souza</h6>
    </div>
    <p></p>
    <img src="./assets/vue.png" alt="Vue logo" />
    <img src="./assets/spring.png" alt="Spring boot logo" />
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 30px;
}

p {
  margin-bottom: 20px;
}
</style>
