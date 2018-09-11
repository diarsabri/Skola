<template>
  <div id="app">
    <router-link to="/">Home</router-link>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'dbtd'
  }
</script>

<style>

  * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
  }

  body { 
    font-family: 'Open Sans', sans-serif;
    font-size: 18px;
    background: #00E676;
    min-height: 100vh;
  }

  .container {
    padding-left: 4rem;
    padding-right: 4rem;
  }
</style>
