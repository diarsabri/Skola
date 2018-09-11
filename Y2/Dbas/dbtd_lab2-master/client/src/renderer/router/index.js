import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'landing-page',
      component: require('@/components/LandingPage').default
    },
    {
      path: '/book_meeting',
      name: 'book-meeting',
      component: require('@/components/Booker').default
    },
    {
      path: '/unbook_meeting',
      name: 'unbook-meeting',
      component: require('@/components/UnBooker').default
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})
