# Java Automation Template version 3

## Run tests

```bash
./mvnw clean test
```

```bash
./mvnw clean test -Dsurefire.suiteXmlFiles=src/test/resources/suites/all-test.xml -DRP_ATTRIBUTES=level:regression

```

```bash
./mvnw clean test -Dsurefire.suiteXmlFiles=src/test/resources/suites/api-test.xml -DRP_ATTRIBUTES=level:feature
```

```bash
./mvnw clean test -Dgroups=smoke 
```

## Run with Report portal

Configuration for report portal can be set in [reportportal.properties](src/test/resources/reportportal.properties)

### Run for different launches

```bash
export RP_LAUNCH="Smoke launch"
./mvnw clean test 
```

## Run TestNG suites with maven 

```bash
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/suites/api-test.xml
```

## View allure report

> Need to install allure [(documentation)](https://allurereport.org/docs/install/)

```bash
allure serve target/allure-results
```

## Deploy package

Before deployment, ensure you export the GITHUB_TOKEN by setting it to your GitHub token with package write permissions.

```bash
./mvnw --settings .github/settings.xml --batch-mode deploy -DskipTests
```

## Docker build common

```bash
docker build -t java-automation-template-v3 -f docker/common/Dockerfile .
```

## Docker build with Playwright dependencies

```bash
docker build -t java-automation-template-v3-ui -f docker/ui/Dockerfile .
```

## Docker run common image with suite specifying

```bash
docker run --rm -e SUITE_XML_FILE=src/test/resources/suites/api-test.xml -v $(pwd)/target:/app/target/ java-automation-template-v3
```