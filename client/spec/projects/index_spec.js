require("../spec_helper")

var projects = require("../../app/projects/index")
var Project = projects.Project
var Projects = projects.Projects

describe("projects", function() {
  describe("Project", function() {
    var project

    beforeEach(function() {
      project = new Project
    })

    it("has a title", function() {
      expect(project.get("name")).toEqual("new project")
    })

    it("has a description", function() {
      expect(project.get("description")).toEqual("new description")
    })
  })

  describe("Projects", function() {
    var projects

    beforeEach(function() {
      projects = new Projects
    })

    it("creates a project", function() {
      expect(projects.create({name: "New One"}))
    })
  })
})
