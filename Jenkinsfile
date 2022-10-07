pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Deploy Backend') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', 
                path: '', url: 'http://localhost:8001')], 
                contextPath: 'tasks-backend', 
                war: 'target\\tasks-backend.war'
            }
        }
        stage ('API Tests') {
            steps {
                dir ("APi_Tests") {
                    git branch: 'main', 
                    url: 'git@github.com:NandoHessel/Testes-de-API---RestAssured.git'

                    bat 'mvn test'
                } 
            }
        }
        stage ('Deploy Frontend') {
            steps {
                dir ('Frontend') {
                    git branch: 'master', 
                    url: 'git@github.com:NandoHessel/tasks-frontend.git'

                    bat 'mvn clean package'

                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', 
                    path: '', url: 'http://localhost:8001')], 
                    contextPath: 'tasks', 
                    war: 'target\\tasks.war'
                }
            }
        }
        stage ('Testes Funcionais') {
            steps {
                dir ("Funcional_testings") {
                    git branch: 'main', 
                    url: 'git@github.com:NandoHessel/TestesFuncionais-Selenium.git'

                    bat 'mvn test'
                } 
            }
        }
        stage ('Deploy Prod') {
            steps {
                bat 'docker-compose build'
                bat 'docker-compose up -d'
            }
        }
        stage ('HealthCheck') {
            steps {
                dir ("Health_Check") {
                    git branch: 'main', 
                    url: 'git@github.com:NandoHessel/HealthCheckTest.git'

                    bat 'mvn test'
                } 
            }
        }
    }
    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml, APi_Tests/target/surefire-reports/*.xml, Funcional_testings/target/surefire-reports/*.xml, Health_Check/target/*.xml'
        }
    }
}


