<template>
  <main class="container">
    <div class="header">
      <h1>Book meeting</h1>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Corrupti earum eum sunt odit libero officiis asperiores, incidunt quas ducimus nobis debitis nisi quod ipsum est blanditiis magnam sed unde cumque.</p>
    </div>
    <form class="booker">
      <div class="form-group">
        <h3>Bookee</h3>
        <select v-model="staff_input" name="staff" size="10">
          <option v-for="s in staff" :key="s.staff_id" :value="s.staff_id">
            {{ s.staff_name }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <h3>Room</h3>
        <select v-model="room_input" name="room" size="10">
          <option v-for="room in rooms" :key="room.room_id" :value="room.room_id">
            {{ room.room_name }}, ({{ room.facs }})
          </option>
        </select>
      </div>
      <div class="form-group">
        <h3>Date</h3>
        <select v-model="date_input" name="date" size="10">
          <option v-for="date in dates" :key="date" :value="date">
            {{ date }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <h3>Time Start</h3>
        <select v-model="time_start_input" name="start_time" size="10">
          <option v-for="time in times" :key="time" :value="time">
            {{ time }}
          </option>
        </select>
      </div>
      <div class="form-group">
        <h3>Time end</h3>
        <select v-model="time_end_input" name="end_time" size="10">
          <option v-for="time in endTimes" :key="time" :value="time">
            {{ time }}
          </option>
        </select>
      </div>
      <button v-on:click="submit($event)">Book Meeting</button>
    </form>
    <h3 v-if="booking_error">
      Room is occupied during that time!
    </h3>
    <h3 v-else-if="booked_meeting">
      Meeting booked!
    </h3>
  </main>
</template>

<script>

const dateformat = require('dateformat')

function addDays (date, days) {
  var result = new Date(date)
  result.setDate(result.getDate() + days)
  return result
}

export default {
  name: 'book-meeting',
  data () {
    return {
      staff_input: null,
      room_input: null,
      date_input: null,
      time_start_input: null,
      time_end_input: null
    }
  },
  methods: {
    submit (e) {
      e.preventDefault()
      const startTs = this.date_input + ' ' + this.time_start_input + ':00'
      const endTs = this.date_input + ' ' + this.time_end_input + ':00'
      this.$store.dispatch({
        type: 'doQuery',
        query: `INSERT INTO meeting(room,time_booked,bookee) VALUES (${this.room_input},
                                            tsrange('${startTs}','${endTs}', '[)'),
                                            ${this.staff_input})`,
        name: 'booked_meeting'
      })
      return false
    }
  },
  created () {
    this.$store.dispatch({
      type: 'doQuery',
      query: 'SELECT * FROM staff',
      name: 'staff'
    })
    this.$store.dispatch({
      type: 'doQuery',
      query: `SELECT resources.room_id, resources.room_name, string_agg(facilities.facility_name, ', ') AS facs
              FROM resources 
              LEFT OUTER JOIN facilities ON (facilities.facility_id, resources.room_id) IN (SELECT facility, room FROM resource_has_facility)
              GROUP BY resources.room_id`,
      name: 'rooms'
    })
  },
  computed: {
    booked_meeting () {
      const queryRes = this.$store.state.Database.queryResult
      return queryRes.booked_meeting || false
    },
    booking_error () {
      const err = this.$store.state.Database.err
      return err.booked_meeting || false
    },
    staff () {
      const queryRes = this.$store.state.Database.queryResult
      return queryRes.staff || []
    },
    rooms () {
      const queryRes = this.$store.state.Database.queryResult
      console.log(queryRes.rooms)
      return queryRes.rooms || []
    },
    dates () {
      const len = 20
      var acc = []
      for (var i = 0; i < len; i++) {
        const date = addDays(new Date(), i)
        const formattedDate = dateformat(date, 'yyyy-mm-dd')
        acc.push(formattedDate)
      }
      return acc
    },
    times () {
      const start = 8
      const end = 20
      var times = []
      for (var i = start; i <= end; i++) {
        const padding = i < 10 ? '0' : ''
        times.push(padding + i.toString() + ':00')
      }
      return times
    },
    endTimes () {
      var self = this
      const start = 8
      const end = 20
      var times = []
      for (var i = start; i <= end; i++) {
        const padding = i < 10 ? '0' : ''
        times.push(padding + i.toString() + ':00')
      }
      return times.filter((x) => x > self.time_start_input)
    }
  }
}
</script>

<style>
.booker {
  padding-top: 4rem;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
}

.header {
}

select {
  width: 100%;
}

</style>


