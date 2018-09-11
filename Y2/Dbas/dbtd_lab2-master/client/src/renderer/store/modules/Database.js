import Vue from 'vue'
const { Client } = require('pg')
const client = new Client({
  database: 'dtb_lab2'
})

client.connect().then(() => {
  console.log('connected to database')
})

const state = {
  queryResult: {},
  err: {}
}

const mutations = {
  updateQueryResult (state, {res, name}) {
    Vue.delete(state.err, name)
    Vue.set(state.queryResult, name, res)
  },
  putError (state, {err, name}) {
    Vue.delete(state.queryResult, name)
    Vue.set(state.err, name, err)
  }
}

const actions = {
  doQuery (context, payload) {
    console.log(`got query '${payload.query}'`)
    client.query(payload.query, (err, res) => {
      if (!err) {
        console.log(payload.name)
        context.commit('updateQueryResult', {
          res: res.rows,
          name: payload.name
        })
      } else {
        console.log(err)
        context.commit('putError', {
          err: err,
          name: payload.name
        })
      }
    })
  },
  initStore (context) {
  }
}

export default {
  state,
  mutations,
  actions
}
