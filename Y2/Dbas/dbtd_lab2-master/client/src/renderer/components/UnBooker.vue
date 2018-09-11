<template>
  <main class="container">
    <h1>Unbook meeting</h1>
    <form class="unbooker">
      <div class="form-group">
        <h3>Unbookee</h3>
        <select v-model="staff_input" name="staff" size="10">
          <option v-for="s in staff" :key="s.staff_id" :value="s">
            {{ s.staff_name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <h3>Meeting</h3>
        <select v-model="meeting_input" name="meeting" size="10">
          <option v-for="meeting in meetings" :key="meeting.meeting_id" :value="meeting">
            {{ meeting.meeting_id }}, {{ meeting.room_name }}, {{ meeting.staff_name }}
          </option>
        </select>
      </div>
      <button v-on:click="submit($event)">Unbook meeting</button>
      <div>

        <h3 v-if="unbook_error">
          err: {{unbook_error}}
        </h3>
        <h3 v-else-if="unbook">
          Meeting Unbooked!
        </h3>
      </div>
    </form>
  </main>
</template>

<script>
export default {
  name: 'unbook-meeting',
  data () {
    return {
      staff_input: null,
      meeting_input: null,
      err: false
    }
  },
  methods: {
    submit (e) {
      e.preventDefault()
      if (this.meeting_input.staff_id !== this.staff_input.staff_id) {
        this.err = 'Not authorized to delete'
        return false
      }
      this.err = false
      this.$store.dispatch({
        type: 'doQuery',
        query: `DELETE FROM meeting WHERE meeting.meeting_id = ${this.meeting_input.meeting_id}
                AND LOWER(meeting.time_booked) > now()::date`,
        name: 'unbook'
      })
      setTimeout(function () {
        this.$forceUpdate()
      }.bind(this), 100)
      return false
    }
  },
  created () {
    this.$store.dispatch({
      type: 'doQuery',
      query: `SELECT * FROM meeting 
      INNER JOIN resources ON resources.room_id = meeting.room 
      INNER JOIN staff ON staff.staff_id = meeting.bookee WHERE
      LOWER(meeting.time_booked) > now()::date`,
      name: 'meetings'
    })
    this.$store.dispatch({
      type: 'doQuery',
      query: 'SELECT * FROM staff',
      name: 'staff'
    })
  },
  computed: {
    meetings () {
      const queryRes = this.$store.state.Database.queryResult
      return queryRes.meetings || []
    },
    staff () {
      const queryRes = this.$store.state.Database.queryResult
      return queryRes.staff || []
    },
    unbook_error () {
      const err = this.$store.state.Database.err
      return this.err || err.unbook || false
    },
    unbook () {
      const queryRes = this.$store.state.Database.queryResult
      return queryRes.unbook || false
    }
  }
}
</script>

<style>
.unbooker {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
select {
  width: 100%;
}
.form-group {
  padding: 1rem;
}
</style>
