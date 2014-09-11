var Backbone = require("backbone")

var Project = Backbone.Model.extend({
  defaults: {
    name: "new project",
    description: "new description"
  }
})

var Projects = Backbone.Collection.extend({
  model: Project,
  url: "/projects"
})

module.exports = {
  Project: Project,
  Projects: Projects
}
