<template>
  <div id="wrapper">
    <main>

      <header class="container">
        <h2>Super mega advanced booking system</h2>
        <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Provident cum veniam sunt architecto exercitationem dolorum nobis, obcaecati est possimus culpa a doloremque vel quia mollitia saepe asperiores tenetur fugit doloribus!</p>
      </header>
      <div class="content container">
        <h3>Meetings</h3>
        <router-link to="/book_meeting">Book meeting</router-link>
        <router-link to="/unbook_meeting">Unbook meeting</router-link>
      </div>

    </main>
  </div>
</template>

<script>
  import SystemInformation from './LandingPage/SystemInformation'

  export default {
    name: 'landing-page',
    components: { SystemInformation },
    methods: {
      open (link) {
        this.$electron.shell.openExternal(link)
      },
      testCall () {
        this.$store.dispatch({
          type: 'doQuery',
          query: 'SELECT * FROM teams',
          name: 'teams'
        })
      }
    },
    computed: {
      teamName () {
        const queryRes = this.$store.state.Database.queryResult
        return queryRes.teams
          ? queryRes.teams[0].team_name
          : 'No team'
      }
    }
  }
</script>

<style>
header {
  text-align: center;
  background: #2979FF;
  color: #fff;
  padding-top: 6rem;
  padding-bottom: 4rem;
}
div.content {
  color: #333;
  padding-top: 2rem;
}
div.content h3 {
  font-weight: 900;
}
</style>
