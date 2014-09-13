var $ = require("jquery")
var Backbone = require("backbone")

Backbone.$ = $

var AppRouter = Backbone.Router.extend({
  routes: {
    "/projects" : "projects#index",
    "*" : "notFound"
  },

  notFound: function() {
    alert("Not found")
  }
})

var router = new AppRouter

Backbone.history.start()

var Index = Backbone.View.extend({
  templage = _.template("index.html")
})
