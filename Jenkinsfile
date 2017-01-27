stage('Source') {
  node {
    sh 'cd /opt/centreon-build && git pull && cd -'
    dir('centreon-web') {
      checkout scm
    }
    sh '/opt/centreon-build/jobs/web/pipeline/mon-web-source.sh'
    def source = readProperties file: 'source.properties'
    env.VERSION = "${source.VERSION}"
    env.RELEASE = "${source.RELEASE}"
  }
}

stage('Unit tests') {
  parallel 'centos6': {
    node {
      sh 'cd /opt/centreon-build && git pull && cd -'
      sh '/opt/centreon-build/jobs/web/pipeline/mon-web-unittest.sh centos6'
    }
  },
  'centos7': {
    node {
      sh 'cd /opt/centreon-build && git pull && cd -'
      sh '/opt/centreon-build/jobs/web/pipeline/mon-web-unittest.sh centos7'
      def ut = readProperties file: 'ut.properties'
      step([
        $class: 'hudson.plugins.checkstyle.CheckStylePublisher',
        pattern: '**/codestyle.xml',
        unstableTotalAll: '6500'
      ])
    }
  }
}