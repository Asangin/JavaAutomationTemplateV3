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

Configuration for report portal can be set in [ddd](src/test/resources/reportportal.properties)

### Run for different launches

```bash
export RP_LAUNCH="Smoke launch"
./mvnw clean test 
```

## View allure report

> Need to install allure

```bash
allure serve build/allure-results
```

## Deploy package

Before deployment, ensure you export the GITHUB_TOKEN by setting it to your GitHub token with package write permissions.

```bash
./mvnw --settings .github/settings.xml --batch-mode deploy -DskipTests
```