var Backbone = require("backbone")

var MockHTTPBackend = {
  requests: []
}

var mockAjax = function() {
  this.get = function(route) {
  }

  this.post = function(route, payload) {
  }
}

Backbone.ajax = mockAjax

module.exports = MockHTTPBackend
