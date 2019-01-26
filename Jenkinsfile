node {

  emailRecipients = 'krjura@gmail.com'

  try {

    build()

    notificationBuildSuccessful()

  } catch(err) {
    notificationBuildFailed()
    throw err
  }
}

def build() {

  resolveProperties()

  stage('Initialize') {
    checkout scm
  }

  stage('Build Frontend') {
    withCredentials([
      usernamePassword(credentialsId: 'MAVEN2_KRJURA_ORG_CREDENTIALS', usernameVariable: 'REPOSITORY_USERNAME', passwordVariable: 'REPOSITORY_PASSWORD')
    ]) {
      withEnv([
        'GRADLE_USER_HOME=/opt/cache/gradle',
        'ENVIRONMENT=jenkins']) {
        withDockerRegistry([credentialsId: 'DOCKER_KRJURA_ORG_CREDENTIALS', url: "https://docker.krjura.org"]) {


          stage('Build backend') {
            docker.image('docker.krjura.org/dev-tools/java-build-env:v1').inside("-v dev-tools-gradle-cache:${GRADLE_USER_HOME}") {
              sh './gradlew --version'
              sh './gradlew --no-daemon -Dspring.profiles.active=test,jenkins build bootJar'
            }
          }

          stage('Build frontend') {
            docker.image('docker.krjura.org/dev-tools/angular-build-env:life-v1').inside("-v npm-cache:/opt/node/npm-cache") {
              sh './frontend/angular-web/build-frontend.sh'
            }
          }

          if(env.BRANCH_NAME == "master") {
            stage('dockerize') {
              withDockerRegistry([credentialsId: 'DOCKER_KRJURA_ORG_CREDENTIALS', url: "https://docker.krjura.org"]) {

                IMAGE_TAG="docker.krjura.org/dev-tools/bundle:${BUILD_NUMBER}"

                buildImage = docker.build("${IMAGE_TAG}", "${WORKSPACE}")
                buildImage.push();
                sh "docker rmi ${IMAGE_TAG}"
              }
            }
          }
        }
      }
    }
  }
}

def notificationBuildFailed() {
  if(env.BRANCH_NAME != "master") {
    return;
  }

  mail to: emailRecipients,
    subject: "Job '${JOB_NAME}' build ${BUILD_DISPLAY_NAME} has FAILED",
    body: "Please go to ${BUILD_URL} for details."
}

def notificationBuildSuccessful() {
  if(env.BRANCH_NAME != "master") {
    return;
  }

  if( currentBuild.previousBuild == null ) {
    return
  }

  if (currentBuild.previousBuild.result == 'FAILURE') {
    mail to: emailRecipients,
      subject: "Job '${JOB_NAME}' build ${BUILD_DISPLAY_NAME} has RECOVERED",
      body: "Please go to ${BUILD_URL} for details."
  }
}

def resolveProperties() {
  def config = []

  // make sure cleanup is done on a regular basis
  config.add(
    buildDiscarder(
      logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '30', numToKeepStr: '20')))

  config.add(disableConcurrentBuilds())

  properties(config)
}
