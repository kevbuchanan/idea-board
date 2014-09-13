module.exports = function(grunt) {
  grunt.initConfig({
    pkg: grunt.file.readJSON("package.json"),

    uglify: {
      options: {
        banner: "/*! <%= pkg.name %> <%= grunt.template.today('yyyy-mm-dd') %> */\n"
      },
      build: {
        src: "resources/assets/app.js",
        dest: "resources/assets/app.min.js"
      }
    },

    jasmine_node: {
      options: {
        specNameMatcher: "spec"
      },
      all: ["client/spec/"]
    },

    browserify: {
      "resources/assets/app.js": ["client/app/app.js"]
    },

    watch: {
      files: ["client/**/*.js"],
      tasks: ["default"]
    },
  })

  grunt.loadNpmTasks("grunt-browserify")
  grunt.loadNpmTasks("grunt-contrib-watch")
  grunt.loadNpmTasks("grunt-jasmine-node");
  grunt.loadNpmTasks("grunt-contrib-uglify");

  grunt.registerTask("default", ["browserify", "jasmine_node"]);
}
