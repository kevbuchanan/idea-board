module.exports = function(grunt) {
  grunt.initConfig({
      jasmine: {
        src: 'client/src/**/*.js',
        options: {
          specs: 'client/spec/**/*spec.js'
        }
      }
    })

  grunt.loadNpmTasks('grunt-contrib-jasmine')

  grunt.registerTask('default', 'jasmine')
}
